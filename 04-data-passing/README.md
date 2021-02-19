# **Data Passing**
## Activity ✏️
Create a new **Android** project with the following configurations:
- Template: **Empty Activity**
- Name: **Login**
- Package name: `op.mobile.app.dev.login`
- Save location: `/<PATH TO GITHUB REPOSITORY>/04-login`
- Language: **Kotlin**
- Minimum SDK: **API 28: Android 9.0 (Pie)**

In **activity_main.xml**, add the following `Views`:

| EditText Attribute        | Value           |
| ------------- |:-------------:|
| android:id      | "edt_txt_email_address" |
| android:layout_width     | "0dp" |
| android:layout_height    | "wrap_content" |
| android:layout_marginStart      | "32dp" |
| android:layout_marginTop     | "16dp" |
| android:layout_marginEnd     | "32dp" |
| android:hint     | "Email Address" |
| android:ems     | "10" |
| android:inputType     | "textEmailAddress" |
| app:layout_constraintEnd_toEndOf     | "parent" |
| app:layout_constraintStart_toStartOf     | "parent" |
| app:layout_constraintTop_toTopOf     | "parent" |

| EditText Attribute        | Value           |
| ------------- |:-------------:|
| android:id      | "edt_txt_password" |
| android:layout_width     | "0dp" |
| android:layout_height    | "wrap_content" |
| android:layout_marginStart      | "32dp" |
| android:layout_marginTop     | "16dp" |
| android:layout_marginEnd     | "32dp" |
| android:hint     | "Email Address" |
| android:ems     | "10" |
| android:inputType     | "textPassword" |
| app:layout_constraintEnd_toEndOf     | "parent" |
| app:layout_constraintStart_toStartOf     | "parent" |
| app:layout_constraintTop_toBottomOf     | "edt_text_email_address" |

| Button Attribute        | Value           |
| ------------- |:-------------:|
| android:id      | "btn_login" |
| android:layout_width     | "0dp" |
| android:layout_height    | "wrap_content" |
| android:layout_marginStart      | "32dp" |
| android:layout_marginTop     | "16dp" |
| android:layout_marginEnd     | "32dp" |
| android:text     | "Login" |
| app:layout_constraintEnd_toEndOf     | "parent" |
| app:layout_constraintStart_toStartOf     | "parent" |
|  app:layout_constraintTop_toBottomOf     | "edt_text_password" |

The UI should look like the following:

<img src="./resources/readme/android-studio-activity-ui.png" alt="Android Studio Activity UI" width="275" height="400" />

## Intent
An **Intent** is a messaging object you can use to request an action from another app component. There are three fundamental use cases which intents facilitate communication between components:
- Starting an activity - an **Activity** represents a single screen in an application. You can start a new instance of an **Activity** by passing an **Intent** to `startActivity()`. 
- Starting a service - a **Service** performs operations in the background without a UI. With **API 21: Android 5.0 (Lollipop)** & later, you can start a service with **JobScheduler**.
- Delivering a broadcast - a message that any application can receive. The system delivers various broadcasts for system events, i.e., when the system boots up or the device starts charging. You can deliver a broadcast to other applications by passing an **Intent** to `sendBroadcast()` or `sendOrderedBroadcast()`.

**Resources:**
- https://developer.android.com/reference/android/content/Intent
- https://developer.android.com/reference/android/app/Service
- https://developer.android.com/reference/android/app/job/JobScheduler
- https://developer.android.com/guide/components/broadcasts

## Activity ✏️
In **MainActivity.kt**, create a `Button` on-click listener & when clicked, output both `EditText` values to a `Toast`.

### Intent Types
There are two types of intents:
- Explicit - specifies which application will satisfy the intent by either supplying application's package name or a component class name. You will use an explicit intent to start a component in your own application because you know the activity's name or service you want to start.
- Implicit - do not name a specific component. Instead, declares a general action to perform, which allows a component from another application to handle it.

## Activity ✏️
- Create a new `Activity` called **SecondActivity**. To generate a new `Activity` & **XML** layout, right-click **package > New > Activity > New Activity**. If you go to **AndroidManifest.xml**, you should see a new `activity` **XML** tag.
- In **activity_second.xml**, add a `TextView` with the `android:id` `txt_view_output`.

## Practical
The practical for this topic is available [here]().
