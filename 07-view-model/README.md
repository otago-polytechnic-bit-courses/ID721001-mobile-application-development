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

### DashboardFragment

### MainActivity

```kotlin

```

## ViewModel Lifecycle
ViewModel objects are scoped to the Lifecycle passed to the ViewModelProvider when getting the ViewModel. The ViewModel remains in memory until the Lifecycle it's scoped to goes away permanently: in the case of an activity, when it finishes, while in the case of a fragment, when it's detached.

Figure 1 illustrates the various lifecycle states of an activity as it undergoes a rotation and then is finished. The illustration also shows the lifetime of the ViewModel next to the associated activity lifecycle. This particular diagram illustrates the states of an activity. The same basic states apply to the lifecycle of a fragment.

You usually request a ViewModel the first time the system calls an activity object's onCreate() method. The system may call onCreate() several times throughout the life of an activity, such as when a device screen is rotated. The ViewModel exists from when you first request a ViewModel until the activity is finished and destroyed.