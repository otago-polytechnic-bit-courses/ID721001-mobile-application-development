# **UI Testing**

## Overview
**UI testing** ensures your application meets its functional requirements & achieves a high standard of quality & assurance so that it is more likely to be consumed by users.

One approach to **UI testing** is to have a person perform a set of tasks on your application & verify that it is behaving correctly. However, **what is the problem with this approach?** This is a manual approach that can be time consuming, tedious & error-prone. So, **what is a more efficient approach?** Write your **UI tests** so that user actions are performed in an automated way. This approach allow you to run your tests quickly, reliably & repetitively. **Android Studio** provides the necessary setup for writing & running your **UI tests**.

To automate **UI tests**, you write your test code in **app > java > package name (androidTest)**. The [Android Gradle plugin](https://developer.android.com/studio/releases/gradle-plugin) builds a test application based on your test code & loads the test application on the same device (**Emulator** or **connect device**) as the target application. In your test code, you can use UI testing frameworks such as **Espresso** to simulate user interactions on the target application.

## Set up Espresso
Go to **build.gradle (Module)** & make sure you have the **Espresso** dependency:

```
androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
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

In this directory, open the `login` directory provided to you in **Android Studio**. Go to **app > java > package name (androidTest)** & create a new file called `LoginBehaviourTest.kt`. In this file, add the following code:

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
- What is happening?
    - This is an example of how to create a new **Espresso** test in the **JUnit 4** style.
    - `ActivityScenarioRule` is used to reduce the amount of boilerplate code.
    - By using `ActivityScenarioRule`, the testing framework launches the activity, i.e., `MainActivity` under test before each method annotated with `@Before` & `@Test`.
    - The activity is terminated after the test is completed & all methods annotated with `@After` are run.
    - In the `check_login_output()` method:
        - The `onView()` method is called & passed a view matcher, i.e., `withId(R.id)` which specifies the targeted `View`.
        - A `ViewInteraction` object is returned which allows your test to interact with the targeted `View`. 
        - The `perform()` method is called & passed a `ViewAction` method, i.e., `click()` which simulates a user interaction, i.e, clicking on the `View`.
    

**Note:** please avoid copying & pasting. However, if you choose to do so, you will need add the appropriate import statements in order to make the code work. 

To run the `LoginBehaviourTest` class, right-click **LoginBehaviourTest > Run 'LoginBehaviourTest'**.

<hr />
