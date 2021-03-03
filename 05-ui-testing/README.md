# **UI Testing**

## Overview
**UI testing** ensures your application meets its functional requirements & achieves a high standard of quality & assurance so that it is more likely to be consumed by users.

One approach to **UI testing** is to have a person perform a set of tasks on your application & verify that it is behaving correctly. However, **what is the problem with this approach?** This is a manual approach that can be time consuming, tedious & error-prone. So, **what is a more efficient approach?** Write your **UI tests** so that user actions are performed in an automated way. This approach allow you to run your tests quickly, reliably & repetitively. **Android Studio** provides the necessary setup for writing & running your **UI tests**.

To automate **UI tests**, you write your test code in **app > java > package name (androidTest)**. The [Android Gradle plugin](https://developer.android.com/studio/releases/gradle-plugin) builds a test application based on your test code & loads the test application on the same device as the target application. In your test code, you can use UI testing frameworks such as **Espresso** to simulate user interactions on the target application.

## Set up Espresso
Go to **build.gradle (Module)** & make sure you have the **Espresso** dependency:

```
androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0
```

## Create an Espresso Test Class
To create an **Espresso** test:
1. Find the UI component you want to test in an `Activity`, i.e., a login `Button`, by calling the `onView()` method.
2. Simulate a user interaction to perform on the UI component, by calling the `ViewInteraction.perform()` or `DataInteraction.perform()` method & passing in a user action.
3. If necessary, repeat step one & two to simulate a flow across multiple activities in the target application. 
4. Use a `ViewAssertions` method to check your UI is behaving as expected after a user interactions has been performed.

**Resources:** 
- https://developer.android.com/training/testing/ui-testing
- https://developer.android.com/training/testing/espresso

<hr />

### Activity ✏️

In this directory, open `login` in **Android Studio**. Go to **app > java > package name (androidTest)** & create a new file called `LoginTest.kt`. In `LoginTest.kt` add the following code:

```kotlin
@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginBehaviourTest {
    private lateinit var emailAddressInput: String
    private lateinit var passwordAddressInput: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        emailAddressInput = "john.doe@gmail.com"
        passwordAddressInput = "Passw0rd123"
    }

    @Test
    fun check_login_output() {
        onView(withId(R.id.et_email_address))
            .perform(typeText(emailAddressInput), closeSoftKeyboard())
        onView(withId(R.id.et_password))
            .perform(typeText(passwordAddressInput), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())
        onView(withId(R.id.tv_output))
            .check(matches(withText(emailAddressInput)))
    }
}
```

**Note:** please avoid copy & paste. However, if you choose to do so, you will need add some import statements in order to make the code work.

To run the test class, 

<hr />
