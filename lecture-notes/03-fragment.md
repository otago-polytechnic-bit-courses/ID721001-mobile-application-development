# **03: Fragments**

## Overview

A `Fragment` is a reusable portion of your application's UI. A **fragment** defines & manages its own layout, has its own lifecycle & can handle events. However, **fragments** cannot live on their own meaning they must be hosted by either an **activity** or another **fragment**. The **fragment's** view hierarchy attaches to the host's view hierarchy, i.e., an **activity's'** or another **fragment's** view hierarchy.

## Modularity

**Fragments** are extremely useful for modularity & reusability in your **activity's** UI & it does this by allowing you divide the UI into chunks. **Activities** are an ideal place to put global UI elements such as a **navigation**. **Fragments** are better suited to define & manage the UI of a single screen or portion of a screen.

Here is an example...consider an application that responds to various screen sizes, i.e., a mobile and tablet device. On a tablet device, the application should display a static `NavigationDrawer` & a list in a `GridLayout`. On a mobile device, the application should display a `BottomNavigationView` & a list in a `LinearLayout`. Managing all the variations in the activity can be tedious...trust me. Separating navigation elements from the content can be much more manageable. The **activity** is responsible for displaying the correct UI navigation & the **fragment** is responsible for displaying the list in a layout.

<img src="../resources/img/04-fragments/fragment-large-small-screen.png" width="750" height="450" />

While your **activity** is in the `onStart` lifecycle state or higher, **fragments** can be added, replaced or removed from an **activity**.

## Code Example

Open the `bottom-navigation-fragments` directory provided to you in **Android Studio**.

Lets take a look at what is happening...

### build.grade

Go to **Gradle Scripts > build.grade (Module: BottomNavigation.app)**. You should see the following in the **dependencies** block:

```xml
implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
```

Without these dependencies, you can not use **fragments** with navigation.

### HomeFragment, DashboardFragment & NotificationsFragment

- `Fragment()` - constructor used by the default `FragmentFactory`.
- `onCreateView(LayoutInflater, ViewGroup?, Bundle?)` - the **fragment** instantiates its UI view. This is optional & non-graphical **fragments** can return **null**.
- `inflate(int, ViewGroup, boolean)` - inflate a new view hierarchy from a specified **XML** resource.

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

**Resources:**

- https://developer.android.com/reference/androidx/fragment/app/Fragment
- https://developer.android.com/guide/fragments/lifecycle

### MainActivity

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val btmNavView: BottomNavigationView = findViewById(R.id.btm_nav_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        btmNavView.setupWithNavController(navController)
    }
}
```

### Menu

A **menu** resource file containing three items.

You will use this file in `activity_main.xml` later.

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/navigation_home"
        android:icon="@drawable/ic_home_black_24dp"
        android:title="Home" />

    <item
        android:id="@+id/navigation_dashboard"
        android:icon="@drawable/ic_dashboard_black_24dp"
        android:title="Dashboard" />

    <item
        android:id="@+id/navigation_notifications"
        android:icon="@drawable/ic_notifications_black_24dp"
        android:title="Notifications" />

</menu>
```

### Fragment Layout

A **fragment** layout file is very similar to an **activity** layout file.

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

A `BottomNavigationView` is a standard bottom navigation bar for your application. It makes it easy for users to switch between different top-level destinations in a single tap. Its recommended that you use a `BottomNavigation` if your application has between three and five top-level destinations. One key attribute/value is `app:menu="@menu/bottom_nav_menu"` which populates the `BottomNavigationView` with the contents in a specified **menu** resource file, i.e., `bottom_nav_menu.xml`.

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

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/btm_nav_view"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="0dp"
        android:layout_marginEnd="0dp"
        android:background="?android:attr/windowBackground"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:menu="@menu/bottom_nav_menu" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Navigation

The **navigation graph** is used to manage your application's navigation. A **navigation graph** is a resource file which contains all your application's destinations & actions that the user can take when navigating between destinations.

**Note:** the example below does use actions.

**Design view:**

<img src="../resources/img/04-fragments/navigation-design-graph.JPG" width="350" height="550" />

**Code view:**

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/mobile_navigation"
    app:startDestination="@+id/navigation_home">

    <fragment
        android:id="@+id/navigation_home"
        android:name="op.mobile.app.dev.bottom.navigation.ui.home.HomeFragment"
        android:label="Home"
        tools:layout="@layout/fragment_home" />

    <fragment
        android:id="@+id/navigation_dashboard"
        android:name="op.mobile.app.dev.bottom.navigation.ui.dashboard.DashboardFragment"
        android:label="Dashboard"
        tools:layout="@layout/fragment_dashboard" />

    <fragment
        android:id="@+id/navigation_notifications"
        android:name="op.mobile.app.dev.bottom.navigation.ui.notifications.NotificationsFragment"
        android:label="Notifications"
        tools:layout="@layout/fragment_notifications" />

</navigation>
```

Run the project's application on either an **Android Emulator** or a **connected device**.

<img src="../resources/img/04-fragments/fragment-home.png" width="250" height="450" /><img src="../resources/img/04-fragments/fragment-dashboard.png" width="250" height="450" /><img src="../resources/img/04-fragments/fragment-notifications.png" width="250" height="450" />

## Formative assessment

In this **in-class activity**, you will start developing your **Travelling** application for the **Project** assessment.

### Submission

You must submit all program files via **GitHub Classroom**. Here is the URL to the repository you will use for this **in-class activity** – <https://classroom.github.com/a/IIlgqZV5>. If you wish to have your code reviewed, message the course lecturer on **Microsoft Teams**.

### Fragments

Using **Fragment**, create the following screens:

- Splash
- Login
- Register
- Home
- Translator and text to speech
- Quiz
- Settings

### Creating the UI

For most of these screens, you can start creating the UI. These will most likely have no functionality.

### Splash Screen

The start destination of your application should be the splash screen. The process is much the same where you create a `Fragment` and its **XML** layout file. There are some extra things you will need to do such as navigating between `Fragments`, i.e., the splash screen and login screen. So, how do you do this?

- In `mobile_navigation.xml`, you will add a new `fragment` element for the `SplashFragment`.
- Set this `fragment` to the start destination.
- In the `fragment` element, nest the following `action`:

```xml
<action
    android:id="@+id/action_splash_fragment_to_login_fragment"
    app:destination="@id/navigation_login" />
```

You are saying, I want to navigate from the splash screen to the login screen. **Note:** you can hard code this in the `Fragment` class, but is not a standard practice.

- In your `build.gradle (Project)`, add the following in the `dependencies` block:

```xml
classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5" // Without this, you can not navigate between screens
```

- In your `build.gradle (Module)`, add the following in the `plugins` block:

```xml
id 'androidx.navigation.safeargs' // Like above, without this, you can not navigate between screens
```

- In `SplashFragment.kt`, implement splash functionality using **Lottie** and this link - https://medium.com/learntocodewithragini/android-animations-using-lottie-kotlin-b4fe14dece00

- If you are stuck on how to implement a splash screen, use this link - <http://www.kotlincodes.com/kotlin/android-splash-screen-with-kotlin>. Go to the **Create Splash Screen Activity Class** (you can ignore the first few sections) and refer to the code snippet provided.
- Replace `Handler().postDelayed()` with `Handler(Looper.getMainLooper()).postDelayed()`.
- Inside of the `Handler(Looper.getMainLooper()).postDelayed()` method remove:

```kotlin
// This is only useful if you want to navigate between activities. We only want to navigate between fragments
startActivity(Intent(this, MainActivity::class.java))
finish()
```

- Replace with:

```kotlin
val action = SplashScreenFragmentDirections
    .actionSplashScreenFragmentToLoginFragment()
view?.findNavController()?.navigate(action) // Calling the navigation action declared in mobile_navigation.xml
```

- If you can not called `SplashScreenFragmentDirections`, then rebuild the project by clicking **Build > Rebuild Project**.

- Run your application. What happens?
