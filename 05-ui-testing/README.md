# **UI Testing**

## Overview
**UI testing** ensures your application meets its functional requirements & achieves a high standard of quality & assurance so that it is more likely to be consumed by users.

One approach to **UI testing** is to have a person perform a set of tasks on your application & verify that it is behaving correctly. However, **what is the problem with this approach?** This is a manual approach that can be time consuming, tedious & error-prone. So, **what is a more efficient approach?** Write your **UI tests** so that user actions are performed in an automated way. This approach allow you to run your tests quickly, reliably & repetitively. **Android Studio** provides the necessary setup for writing & running your **UI tests**.

To automate **UI tests**, you write your test code in **app > java > package name (androidTest)**. The Android Plug-in for Gradle builds a test application based on your test code & loads the test application on the same device as the target application. In your test code, you can use UI testing frameworks such as Espresso to simulate user interactions on the target application.

## Set up Espresso
Go to **build.gradle (Module)** & make sure you have the **Espresso** dependency:

```
androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0
```

## Create an Espresso Test Class
To create an Espresso test:
1. Find the UI component you want to test in an `Activity`, i.e., a login `Button`, by calling the `onView()` method.
2. Simulate a user interaction to perform on the UI component, by calling the `ViewInteraction.perform()` or `DataInteraction.perform()` method & passing in a user action.
3. If necessary, repeat step 1 & 2 to simulate a flow across multiple activities in the target application. 
4. Use a `ViewAssertions` method to check your UI is behaving as expected after a user interactions has been performed.

https://developer.android.com/training/testing/ui-testing/espresso-testing#accessing-ui-components
