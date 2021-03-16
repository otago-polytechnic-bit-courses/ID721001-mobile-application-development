# **ViewModel**

## Overview

The **Android Framework** manages the lifecycle of a **UI controller**, i.e., **activity** or **fragment**. The **framework** may also decide to destroy or recreate a **UI controller** in response to certain user actions or device events that are out of your control.

If the **system** destroys or recreates a **UI controller**, any transient UI-related data you store is lost. For example, your application has a list of students fetched from an **API** in a **UI controller**. When the **fragment** is recreated for a configuration changes, i.e., rotating your phone from portrait to landscape & vice-versa, the **UI controller** has to refetch the list of students. For small amounts of data, the **UI controller** can use the `onSaveInstanceState()` method to restore its data from the bundle in `onCreate()`. This is not a practical solution for large amounts of data, i.e., lists of students or bitmaps.

**UI controllers** frequently need to make asynchronous calls which may take some time to return data, i.e., list of students. The **UI controller** needs to manage these calls & ensure the **system** cleans up after it is destroyed to avoid potential **memory leaks**. Unfortunately, this requires a lot of maintenance & it the case where the object is recreated for a configuration change, it is a waste of resources.

**UI controllers** are intended to display UI-related data, react to user actions or handle os communication. Relying on a **UI controller** to be responsible for fetching data from an **API** adds bloat to the class. Assigning excessive responsibility to **UI controller** results in a class handling all the work by itself where this work should be delegated to other classes . It is easier or more efficient to separate the UI-related data from the **UI controller** logic.

## Code Example
Open the `07-bottom-navigation` directory provided to you in **Android Studio**. 

Lets take a look at what is happening...

### build.grade

Go to **Gradle Scripts > build.grade (Module: BottomNavigation.app)**. You should see the following in the **dependencies**:

```
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0'
```

Without this dependencies, you can **not** use `ViewModel`.

### DashboardViewModel
```kotlin
class DashboardViewModel : ViewModel() {
    var count: Int = 0

    fun plusOne() {
        count++
    }
}
```

### DashboardFragment
```
class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val tvOutput: TextView = view.findViewById(R.id.tv_count)
        val btnPlusOne: Button = view.findViewById(R.id.btn_plus_one)

        val viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

       btnPlusOne.setOnClickListener {
           viewModel.plusOne()
           tvOutput.text = viewModel.count.toString()
       }

        return view
    }
}
```

## ViewModel Lifecycle
`ViewModel` objects are scoped to the `Lifecycle` passed to the `ViewModelProvider` when getting the `ViewModel`. The `ViewModel` remains in memory until either an activity finishes or a fragment detaches.

The following image demonstrates the various lifecycle states an **activity** when it undergoes a configuration change, then finishes. Also, this image demonstrates the lifetime of the `ViewModel` next to the **activity** lifecycle. 

A `ViewModel` is usually requested the first time the **system** calls the `onCreate()` or `onCreateView()` method. The system may call these methods several times throughout the life of a **UI controller** such as rotation a device's screen. The `ViewModel` exists from when you first request a `ViewModel` until the activity is finished & destroyed.
