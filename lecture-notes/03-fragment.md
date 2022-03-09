# **03: Fragments**

## Overview

A `Fragment` is a reusable portion of your application's UI. A **fragment** defines & manages its own layout, has its own lifecycle & can handle events. However, **fragments** cannot live on their own meaning they must be hosted by either an **activity** or another **fragment**. The **fragment's** view hierarchy attaches to the host's view hierarchy, i.e., an **activity's'** or another **fragment's** view hierarchy.

## Modularity

**Fragments** are extremely useful for modularity & reusability in your **activity's** UI & it does this by allowing you divide the UI into chunks. **Activities** are an ideal place to put global UI elements such as a **navigation**. **Fragments** are better suited to define & manage the UI of a single screen or portion of a screen.

Here is an example...consider an application that responds to various screen sizes, i.e., a mobile and tablet device. On a tablet device, the application should display a static `NavigationDrawer` & a list in a `GridLayout`. On a mobile device, the application should display a `BottomNavigationView` & a list in a `LinearLayout`. Managing all the variations in the activity can be tedious...trust me. Separating navigation elements from the content can be much more manageable. The **activity** is responsible for displaying the correct UI navigation & the **fragment** is responsible for displaying the list in a layout.

<img src="../resources/img/04-fragments/fragment-large-small-screen.png" width="750" height="450" />

While your **activity** is in the `onStart` lifecycle state or higher, **fragments** can be added, replaced or removed from an **activity**.

## Code Example

### build.grade

Go to **Gradle Scripts > build.gradle (Module: Travelling.app)**. You should see the following in the **dependencies** block:

```xml
implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
```

You installed these dependencies in the previous session. Without these dependencies, you can not use **fragments** with navigation.

### HomeFragment

Create a new package by right-clicking on **java > op.mobile.app.dev.username.travelling > New > Package**. You will be presented with a popup window. Add **ui.home** to the end of the main package, i.e., **op.mobile.app.dev.username.travelling.ui.home**. Create a **Fragment** by right-clicking on **java > op.mobile.app.dev.username.travelling.ui.home > New > Kotlin Class/File**. Again, you will be you will be presented with a popup window. Call this new class **HomeFragment**, then hit the **Enter** key. Once created, add the following code:

```kotlin
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
```

**Note:** Make sure you import the required APIs. Also, `fragment_home` will be red because you have not created the `fragment_home.xml` file.

- `Fragment()` - constructor used by the default `FragmentFactory`.
- `onCreateView(LayoutInflater, ViewGroup?, Bundle?)` - the **fragment** instantiates its UI view. This is optional & non-graphical **fragments** can return **null**.
- `inflate(int, ViewGroup, boolean)` - inflate a new view hierarchy from a specified **XML** resource.

**Resources:**

- https://developer.android.com/reference/androidx/fragment/app/Fragment
- https://developer.android.com/guide/fragments/lifecycle

### Fragment Layout

Create a new **fragment** layout file by right-clicking on **res > layout > New > Layout Resource File**. You will be presented with a popup window. You only need to give it a **File name** of `fragment_home`. Do not worry about the rest of the fields, i.e., **Root element**, **Source set**, **Directory name** and **Available qualifiers**. Once you have named it, click the **OK** button.

In `fragment_home.xml`, add the following code:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ui.home.HomeFragment">

    <TextView
        android:id="@+id/tv_home"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginEnd="32dp"
        android:text="Home"
        android:textAlignment="center"
        android:textSize="24sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### MainActivity Layout

A `fragment` has two important attributes/values:

- `android:name="androidx.navigation.fragment.NavHostFragment"`
- `app:navGraph="@navigation/mobile_navigation"`

A **NavHostFragment** provides an area within your layout for self-contained navigation to occur. Each **NavHostFragment** has a `NavController` which defines the navigation within the navigation host. Also, this includes the **navigation graph** & navigation state.

**Resources:**

- https://developer.android.com/reference/androidx/navigation/fragment/NavHostFragment
- https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="?attr/colorPrimary"
        android:minHeight="?attr/actionBarSize"
        android:theme="?attr/actionBarTheme"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:defaultNavHost="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"
        app:navGraph="@navigation/mobile_navigation" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Navigation

The **navigation graph** is used to manage your application's navigation. A **navigation graph** is a resource file which contains all your application's destinations & actions that the user can take when navigating between destinations. 

**Code view:**

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/mobile_navigation"
    app:startDestination="@+id/navigation_login">

    <fragment
        android:id="@+id/navigation_login"
        android:name="op.mobile.app.dev.username.travelling.ui.login.LoginFragment"
        android:label="Login"
        tools:layout="@layout/fragment_login" />
   
    <fragment
        android:id="@+id/navigation_home"
        android:name="op.mobile.app.dev.travelling.username.ui.home.HomeFragment"
        android:label="Home"
        tools:layout="@layout/fragment_home" />

</navigation>
```

Also, have a look at the **Design view**.

## Formative assessment

In this **in-class activity**, you will extend your **Travelling** application's functionality for the **Project** assessment.

### Fragments

Create the following screens uisng **Fragments**:

- Splash
- Login
- Register
- Home
- Translator and text to speech
- Quiz
- Settings

### Creating the UI

For most of these screens, you can start creating the UI. These will most likely have no functionality.

### Navigation

Here you will look into how to navigate between the login and home screens. The functionality is simple...when you click the login button on the login screen, you want to navigate to the home screen.

In `mobile_navigation.xml`, add the following under the `navigation_login`'s opening tag:

```xml
 <action
     android:id="@+id/action_login_fragment_to_home_fragment"
     app:destination="@id/navigation_home" />
```

`navigation_login` should look like this:

```xml
<fragment
    android:id="@+id/navigation_login"
    android:name="op.mobile.app.dev.grayson.travelling.ui.login.LoginFragment"
    android:label="Login"
    tools:layout="@layout/fragment_login">
    <action
        android:id="@+id/action_login_fragment_to_home_fragment"
        app:destination="@id/navigation_home" />
</fragment>
```

Here we are defining an action. Essentially saying that we want to navigate from the login screen to the home screen. We apply this action to the login button in `LoginFragment.kt`.

In your `build.gradle (Project)`, add the following above the `plugins` block:

```md
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.4.1" // Without this, you can not navigate between screens
    }
}
```

`build.gradle (Project)` should now look something like this:

```md
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.4.1"
    }
}

plugins {
    id 'com.android.application' version '7.1.1' apply false
    id 'com.android.library' version '7.1.1' apply false
    id 'org.jetbrains.kotlin.android' version '1.5.30' apply false
}

...
```

In your `build.gradle (Module)`, add the following in the `plugins` block:

```xml
id 'androidx.navigation.safeargs' // Like above, without this, you can not navigate between screens
```

`build.gradle (Module)` should now look something like this:

```md
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'androidx.navigation.safeargs'
}

...
```

In `LoginFragment.kt`, replace the `onCreateView()` function with:

```kotlin
...
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val btnLogin: Button = view.findViewById(R.id.btn_login) // Get a reference to button in fragment_login.xml

        btnLogin.setOnClickListener { // Bind a click event listener to the button
            val action = LoginFragmentDirections
                .actionLoginFragmentToHomeFragment() // Get a reference to the action in Login fragment in mobile_navigation.xml
            view?.findNavController()?.navigate(action) // Navigate based on the action, i.e., login to home
        }

        return view
    }
```

Once you have finished, run your application. Click on the login button. You should be on the home screen.

### Adding a Splash Screen

The start destination of your application should be the splash screen. The process is much the same where you create a `Fragment` and its **XML** layout file. In `SplashFragment.kt`, implement splash functionality using **Lottie** and this link - https://medium.com/learntocodewithragini/android-animations-using-lottie-kotlin-b4fe14dece00. In `SplashFragment.kt`, add the following code to the `onCreateView()` function:

```kt
...
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                // Navigate from the splash screen to the login screen, i.e., create an action in mobile_navigation.xml
            },
            3000
        )

        return view
    }
```

Once you have finished, run your application. You see the splash screen and after three seconds the login screen.
