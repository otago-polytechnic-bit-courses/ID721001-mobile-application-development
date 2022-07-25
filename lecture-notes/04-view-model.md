# **04: ViewModel**

## Overview

The **Android Framework** manages the lifecycle of a **UI controller**, i.e., **activity** or **fragment**. The **framework** may also decide to destroy or recreate a **UI controller** in response to certain user actions or device events that are out of your control.

If the **system** destroys or recreates a **UI controller**, any transient UI-related data you store is lost. For example, your application has a list of students fetched from an **API** in a **UI controller**. When the **fragment** is recreated due to a configuration change, i.e., rotating your phone from portrait to landscape & vice-versa, the **UI controller** has to refetch the list of students. For small amounts of data, the **UI controller** can use the `onSaveInstanceState()` method to restore its data from the bundle in `onCreate()`. This is not a practical solution for large amounts of data, i.e., lists of students or bitmaps.

**UI controllers** frequently need to make asynchronous calls which may take some time to return data, i.e., list of students. The **UI controller** needs to manage these calls & ensure the **system** cleans up after it is destroyed to avoid potential **memory leaks**. Unfortunately, this requires a lot of maintenance & it the case where the object is recreated for a configuration change, it is a waste of resources.

**UI controllers** are intended to display UI-related data, react to user actions or handle os communication. Relying on a **UI controller** to be responsible for fetching data from an **API** adds bloat to the class. Assigning excessive responsibility to **UI controller** results in a class handling all the work by itself where this work should be delegated to other classes. It is easier or more efficient to separate the UI-related data from the **UI controller** logic.

### HomeViewModel

Create a **ViewModel** by right-clicking on **java > op.mobile.app.dev.username.travelling.ui.home > New > Kotlin Class/File**. Again, you will be you will be presented with a popup window. Call this new class **HomeViewModel**, then hit the **Enter** key. Once created, add the following code:

```kotlin
...

class HomeViewModel : ViewModel() {
    var count: Int = 0

    fun plusOne() {
        count++ // Increment count by 1
    }

    fun reset() {
        count = 0 // Reset count to 0
    }
}
```

### HomeFragment Layout

In `fragment_home.xml`, replace the existing **XML** with the following:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ui.home.HomeFragment">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginStart="32dp"
        android:layout_marginEnd="32dp"
        android:orientation="vertical"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <TextView
            android:id="@+id/tv_count"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            android:textAlignment="center"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btn_plus_one"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Plus One" />

        <Button
            android:id="@+id/btn_reset"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Reset" />

    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
```

### HomeFragment

In `HomeFragment`, replace the code in the `onCreateView()` function block with the following:

```kotlin
...

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        val tvOutput: TextView = view.findViewById(R.id.tv_count)
        val btnPlusOne: Button = view.findViewById(R.id.btn_plus_one)
        val btnReset: Button = view.findViewById(R.id.btn_reset)

        // Create ViewModel and retain it in a store of the
        // given ViewModelStoreOwner, i.e., HomeFragment
        val viewModel = ViewModelProvider(this)
            // Return HomeViewModel
            .get(HomeViewModel::class.java)

        tvOutput.text = viewModel.count.toString()

        btnPlusOne.setOnClickListener {
            viewModel.plusOne() // Call the plusOne() function in HomeViewModel
            tvOutput.text = viewModel.count.toString()
        }

        btnReset.setOnClickListener {
            viewModel.reset() // Call the reset() function in HomeViewModel
            tvOutput.text = viewModel.count.toString()
        }

        return view
    }
}
```

## ViewModel Lifecycle

`ViewModel` objects are scoped to the `Lifecycle` passed to the `ViewModelProvider` when getting the `ViewModel`. The `ViewModel` remains in memory until either an activity finishes or a fragment detaches.

The following image demonstrates the various lifecycle states an **activity** when it undergoes a configuration change, then finishes. Also, this image demonstrates the lifetime of the `ViewModel` next to the **activity** lifecycle.

<img src="../resources/img/07-view-model/view-model-lifecycle.png" width="375" height="550" />

A `ViewModel` is usually requested the first time the **system** calls the `onCreate()` or `onCreateView()` method. The system may call these methods several times throughout the life of a **UI controller** such as rotating a device's screen. The `ViewModel` exists from when you first request a `ViewModel` until the activity is finished & destroyed.

## Formative Assessment

No formative assessment today. However, I encourage you to try out the examples above.
