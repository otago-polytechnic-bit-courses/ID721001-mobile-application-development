# **10: Room Database**

## Overview

Lets look at the following diagram & discuss how they work together:

<img src="https://raw.githubusercontent.com/otago-polytechnic-bit-courses/IN721-mobile-application-development/master/resources/img/12-room-database/12-architecture-components.png" width="500" height="350" />

- LiveData - a data holder class that can be observed. Always holds the latest version of data & notifies its observers when the data has changed.
- ViewModel - acts as the bridge between the **Repository** & the **UI controller**. The **UI controller** no longer needs to worry about the origin of the data.
- Repository - primarily used to manage multiple data sources.
- Entity - an annotated class that describes a database table when working with **Room**.
- Room database - serves as the access point to a **SQLite** database. This uses the **DAO** to issue queries to a **SQLite** database.
- SQLite database - on device storage. **Room's** persistence library creates & maintains this database for you.
- DAO - maps **SQL** queries to method calls.

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

Without this dependencies, you can **not** use **Room database**.

### Login

To make the following **data** class meaningful to a **Room database**, you need to make the association between the class, i.e., `Login` & the database using **Kotlin** annotations.

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

The compiler checks the **SQL** & generates queries from convenience annotations for common queries, i.e., `@Insert`. The **DAO** must be an interface or an abstract class. **Note:** all queries must be executed on a separate thread.

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

A `Flow` can emit multiple values sequentially whereas `suspend` functions can only return a single value. For example, you can use `Flow` to receive live updates from a database.

### LoginDb

- Define a **singleton**, i.e., `LoginDb` to prevent having multiple instances of the database opened at the same time.
- The database class must be `abstract` & extend `RoomDatabase`.
- Annotate the database class with `@Database` & use the annotation parameters to declare the `entities`, `version` & `exportSchema`.
- The database class exposes a **DAO** through an `abstract` getter method.
- `getDatabase()` returns the **singleton**. It will create the database the first time it is accessed.

```kotlin
...

@Database(entities = [Login::class], version = 1, exportSchema = true)
abstract class LoginDb : RoomDatabase() {
    abstract fun loginDao(): ILoginDao

    companion object {
        @Volatile
        private var INSTANCE: LoginDb? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): LoginDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoginDb::class.java,
                    "login_database"
                )
                    .addCallback(LoginDbCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class LoginDbCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDb(database.loginDao())
                }
            }
        }

        suspend fun populateDb(loginDao: ILoginDao) {
            loginDao.deleteAll()
            var login = Login("john.doe", "P@ssw0rd123")
            loginDao.insert(login)
            login = Login("jane.doe", "P@ssw0rd123")
            loginDao.insert(login)
        }
    }
}
```

### LoginRepository

A **repository** class abstracts access to multiple data sources. This is a suggested best practice for code separation & architecture.

**Why use a repository class?**
It manages your queries & allows multiple data sources, i.e., an API or database.

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

The **DAO** is passed into the **repository** constructor & not the entire database. The **repository** only needs access to the **DAO** because the the **DAO** contains the read/write methods for the database.

The list of login details is a public property & initialised by getting the `Flow` list of login details from **Room**. **Room** executes all queries on a separate thread, but executes `suspend` queries off the main thread.

### LoginViewModel

- Initialise `LiveData` with `allLoginDetails` `Flow` from the `LoginRepository` then convert the `Flow` to `LiveData` by calling `asLiveData()`.
- Create a wrapper method called `insertLoginDetail()` that calls the `LoginRepository's` `insertLoginDetail()` method. This encapsulates the implementation of the wrapper method from the UI.

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
class LoginViewModelFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
```

### LoginApplication
You want to have one instance of the database & repository in your application. A way to achieve this is by creating them as members of an `Application` class. They will be retrieve when needed & not constructed every time.

```kotlin
...

class LoginApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { LoginDb.getDatabase(this, applicationScope) }
    val repository by lazy { LoginRepository(database.loginDao()) }
}
```

### AndroidManifest

Set the `name` attribute in the `application` element to the `Application` class.

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
            LoginViewModelFactory((activity?.applicationContext as LoginApplication).repository)
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
