# **Fragments**

## Overview
A `Fragment` is a reusable portion of your application's UI. A **fragment** defines & manages its own layout, has its own lifecycle & can handle events. However, **fragments** cannot live on their own meaning they must be hosted by either an **activity** or another **fragment**. The **fragment's** view hierarchy attaches to the host's view hierarchy, i.e., an **activity's'** or another **fragment's** view hierarchy.

## Modularity
**Fragments** are extremely useful for modularity & reusability in your **activity's** UI & it does this by allowing you divide the UI into chunks. **Activities** are an ideal place to put global UI elements such as a **navigation**. **Fragments** are better suited to define & manage the UI of a single screen or portion of a screen.

Here is an example...consider an application that responds to various screen sizes, i.e., a mobile and tablet device. On a tablet device, the application should display a static `NavigationDrawer` & a list in a `GridLayout`. On a mobile device, the application should display a `BottomNavigationView` & a list in a `LinearLayout`. Managing all the variations in the activity can be tedious...trust me. Separating navigation elements from the content can be much more manageable. The **activity** is responsible for displaying the correct UI navigation & the **fragment** is responsible for displaying the list in a layout.

<img src="../tex/img/06-fragments/readme/fragment-large-small-screen.png" width="750" height="450" />

While your **activity** is in the `onStart` lifecycle state or higher, **fragments** can be added, replaced or removed from an **activity**.

## Code Example
Open the `bottom-navigation-fragments` directory provided to you in **Android Studio**. 

Lets take a look at what is happening...

### build.grade

Go to **Gradle Scripts > build.grade (Module: BottomNavigation.app)**. You should see the following in the **dependencies**:

```
implementation 'androidx.navigation:navigation-fragment-ktx:2.3.4'
implementation 'androidx.navigation:navigation-ui-ktx:2.3.4'
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

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
```

### Menu

A **menu** resource file containing three items. You will use this file in `activity_main.xml` later.

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
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingTop="?attr/actionBarSize">

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:defaultNavHost="true"
        app:layout_constraintBottom_toTopOf="@id/nav_view"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:navGraph="@navigation/mobile_navigation" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/nav_view"
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

<img src="../tex/img/06-fragments/readme/navigation-design-graph.JPG" width="350" height="550" />

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

Run the project's application on both the **Android Emulator** & a **connected device**. 

<img src="../tex/img/06-fragments/readme/fragment-home.png" width="250" height="450" style="margin-right:10px" /><img src="../tex/img/06-fragments/readme/fragment-dashboard.png" width="250" height="450" style="margin-right:10px" /><img src="../tex/img/06-fragments/readme/fragment-notifications.png" width="250" height="450" />
