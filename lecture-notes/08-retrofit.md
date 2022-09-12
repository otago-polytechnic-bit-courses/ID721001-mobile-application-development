# **08: Retrofit**

## Overview

**Retrofit** is a **REST** client for Java & Kotlin. It makes it easy to request data via a **REST** based **web service**.

### build.gradle

Go to **Gradle Scripts > build.gradle (Module: Travelling.app)**. You should see the following in the **dependencies** block:

```xml
...
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.2.0'
...
```

Without this dependencies, you can **not** use **Retrofit**.

### AndroidManifest

To make a request to an **API**, you need declare the internet permission in `AndroidManifest.xml`.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="op.mobile.app.dev.grayson.travelling">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Travelling">
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:theme="@style/Theme.Travelling.NoActionBar">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

### Country

You can not directly use the contents in `data.json`, rather you need to represent the contents in programtic way or in a way that **Kotlin** understands. Look at the contents in `data.json`...each country object (the thing wrapped in curly braces) has an `id` property, `name` property and much more. You will need to add the other properties to this data class. Think about the data types you will use to represent the contents. **Note:** The name of the arguments, i.e., `id` and `name` must match the name of the properties in `data.json`.
 
```kotlin
data class Country(val id: String, val name: String)
```

### ICountry

**Retrofit** converts your **API** into a **Kotlin** interface. Annotations on an interface method & its parameters indicate how a request will be handled. There are eight different annotations: `HTTP`, `GET`, `POST`, `PUT`, `PATCH`, `DELETE`, `OPTIONS` & `HEAD`. The **URL** of the resource is specified in the annotation. Also, you can specify query parameters in the **URL**.

```kotlin
...

interface ICountry {
    @GET("raw")
    suspend fun getResponse(): List<Country>
}
```

What is a `suspend` function?

Have a look at this [video](https://www.youtube.com/watch?v=BOHK_w09pVA&t=300s) from **Google I/O`19**.

**Resource:** https://square.github.io/retrofit

### ServiceInstance

- `Retrofit.Builder()` - build a new `Retrofit`.
- `baseUrl(String baseUrl)` - set the **API** base URL.
- `addConverterFactory(Converter.Factory factory)` - converter factory for serializing & deserializing objects.
- `build()` - create the **Retrofit** instance using the configured values.

**Note:** calling the `baseUrl()` method is required before calling `build()` method. 

`Retrofit` class converts your **API** interface, i.e., `ICountry` into a callable object, i.e., `retrofitService`. 

```kotlin
...

private const val BASE_URL = "https://gist.githubusercontent.com/Grayson-Orr/49223bcae755ef9479b3150182dc125e/"

object ServiceInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitService: ICountry by lazy {
        retrofit.create(ICountry::class.java)
    }
}
```
An `object` does not have a constructor. The instance of the `object` will be created the first time you use it, i.e., **lazy initialisation**.

**Resources:** 
- https://square.github.io/retrofit/2.x/retrofit
- https://github.com/google/gson

### ServiceStatus

```kotlin
enum class ServiceStatus {
    LOADING,
    ERROR,
    COMPLETE
}
```

### BindingAdapter

**Binding adapters** are responsible for making calls to set values. For example, setting value to a `TextView` by calling the `setText()` method or setting a click event listener to a `Button` by calling the `setOnClickListener()` method. By using **data binding**, you can create an attribute for any setter, i.e., `apiServiceStatus`. You will see how to use `apiServiceStatus` later.

```kotlin
...

@BindingAdapter("service_status")
fun bindServiceStatus(tvStatus: TextView, status: ServiceStatus?) {
    when (status) {
        ServiceStatus.LOADING -> {
            tvStatus.visibility = View.VISIBLE
            tvStatus.text = "Loading..."
        }
        ServiceStatus.ERROR -> {
            tvStatus.visibility = View.VISIBLE
            tvStatus.text = "Connection Error"
        }
        ServiceStatus.COMPLETE -> tvStatus.visibility = View.GONE
    }
}
```

**Resource:** https://developer.android.com/topic/libraries/data-binding/binding-adapters

### HomeViewModel

**Coroutines** provide an API that enables you to write asynchronous code. You can define a `CoroutineScope`, i.e., `viewModelScope.launch`, which manages when your **coroutines** should run. 

```kotlin
...

class HomeViewModel : ViewModel() {
    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<List<Country>>()
    val response: LiveData<List<Country>> get() = _response

    init {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = retrofitService.getResponse()
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _response.value = ArrayList()
                _status.value = ServiceStatus.ERROR
            }
        }
    }
}
```

**Resource:** https://developer.android.com/topic/libraries/architecture/coroutines

### Home Layout

Note how `service_status` binding adapter is being used. 

Replace the existing **XML** code with the following:

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context=".ui.home.HomeFragment">

    <data>

        <variable
            name="homeViewModel"
            type="op.mobile.app.dev.grayson.travelling.ui.home.HomeViewModel" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/tv_status"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginStart="32dp"
            android:layout_marginTop="16dp"
            android:layout_marginEnd="32dp"
            android:layout_marginBottom="16dp"
            android:gravity="center"
            android:textSize="24sp"
            app:service_status="@{homeViewModel.status}"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <TextView
            android:id="@+id/tv_name"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginStart="32dp"
            android:layout_marginTop="16dp"
            android:layout_marginEnd="32dp"
            android:layout_marginBottom="16dp"
            android:gravity="center"
            android:text="@{homeViewModel.response.get(0).name}"
            android:textSize="24sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

    </androidx.constraintlayout.widget.ConstraintLayout>

</layout>
```

### HomeFragment

This is similar to previous code examples.

```kotlin
...

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeViewModel = viewModel
            return root
        }
    }
}
```
