# **07: Data Binding**

## Overview

**Data binding** allows you to bind UI components in your **XML** layouts to data sources in your application using a declarative format.

## Code Example

Open the `bottom-navigation-data-binding` directory provided to you in **Android Studio**. The directory can be found in **code-resources**.

Lets take a look at what is happening...

### build.grade

Go to **Gradle Scripts > build.grade (Module: BottomNavigation.app)**. You should see the following in the **plugin** block:

```xml
id 'kotlin-kapt'
```

& the following in the **android** block:

```xml
buildFeatures {
    dataBinding true
}
```

**Resource:** https://kotlinlang.org/docs/kapt.html

### HomeFragment Layout

The following demonstrates how to use **data binding** to assign `count` to a `TextView`, the `plusOne()` method to a `Button` & the `reset()` method to a `Button` directly in the layout **XML** file. This removes the need to call the code as seen in the last session. **Note:** pay particular attention to the use of `@{}` when assigning.

The **data binding** library generates the classes required to bind the `Views` in the **XML** layout files with your data objects.

Binding variables that can be used in expressions are defined in the `data` element. The `data` element is a sibling of the **XML** layout file's root element, i.e., `ConstraintLayout`. Both elements are wrapped in a `layout` element as shown in the following example:

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context=".ui.home.HomeFragment">

    <data>

        <variable
            name="homeViewModel"
            type="op.mobile.app.dev.bottom.navigation.ui.home.HomeViewModel" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

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
                android:text="@{homeViewModel.count.toString()}"
                android:textAlignment="center"
                android:textSize="24sp" />

            <Button
                android:id="@+id/btn_plus_one"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:onClick="@{() -> homeViewModel.plusOne()}"
                android:text="Plus One" />

            <Button
                android:id="@+id/btn_reset"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:onClick="@{() -> homeViewModel.reset()}"
                android:text="Reset" />

        </LinearLayout>

    </androidx.constraintlayout.widget.ConstraintLayout>

</layout>
```

Binding components in your **XML** layout files lets you remove many UI framework calls in your **UI controllers**, making them easier to maintain. Also, this improves the performance of your application & prevent memory leaks.

### HomeFragment

The **data binding** library provides classes & methods to easily observe data changes. You do not have to worry about refreshing the UI when a data source changes. You can make your variables or their properties **observable**. 

```kotlin
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.homeViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root // Get a reference to the root view
    }
}
```

## Formative Assessment

No formative assessment today. However, I encourage you to try out the examples above.