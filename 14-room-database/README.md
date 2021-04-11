# **Room Database**

## Overview

## Code Example
Open the `login-room-database` directory provided to you in **Android Studio**. 

Lets take a look at what is happening...

### build.grade

Go to **Gradle Scripts > build.grade (Module: Login.app)**. You should see the following in the **dependencies** block:

```xml
...
implementation 'androidx.room:room-runtime:2.2.6'
implementation 'androidx.room:room-ktx:2.2.6'
kapt 'androidx.room:room-compiler:2.2.6'
...
```

Without this dependencies, you can **not** use **Retrofit**.

### Login
```kotlin
...

@Entity(tableName = "login")
data class Login(
    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "password")
    var password: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
```

### ILoginDao
```kotlin
...

@Dao
interface LoginDao {
    @Query("SELECT * FROM login")
    fun getAll(): Flow<List<Login>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(login: Login)

    @Query("DELETE FROM login")
    suspend fun deleteAll()
}
```

### LoginRepository
```kotlin
...

class LoginRepository (private val loginDao: ILoginDao) {
    val allLoginDetails: Flow<List<Login>> = loginDao.getAll()

    @WorkerThread
    suspend fun insertLoginDetail(login: Login) {
        loginDao.insert(login)
    }
}
```

### LoginViewModel
```kotlin
...

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    val allLoginDetails: LiveData<List<Login>> = repository.allLoginDetails.asLiveData()

    fun insertLoginDetail(login: Login) = viewModelScope.launch {
        repository.insertLoginDetail(login)
    }
}
```

### LoginFragmentFactory

```kotlin
...

@Suppress("UNCHECKED_CAST")
class LoginModelFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
```

### LoginFragment
```kotlin
...

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        val viewModelFactory =
            LoginModelFactory((activity?.applicationContext as LoginApplication).repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.loginViewModel = viewModel

        binding.rvLoginDetails.adapter = LoginAdapter()

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            when {
                username.isEmpty() -> binding.etUsername.error = "Please enter a username"
                password.isEmpty() -> binding.etPassword.error = "Please enter a password"
                else -> viewModel.insertLoginDetail(Login(username, password))
            }
        }

        return binding.root
    }
}
```

### LoginApplication
```kotlin
...

class LoginApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { LoginDb.getDatabase(this, applicationScope) }
    val repository by lazy { LoginRepository(database.loginDao()) }
}
```

### AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="op.mobile.app.dev.login">

    <application
        android:name=".login.LoginApplication"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Login">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```