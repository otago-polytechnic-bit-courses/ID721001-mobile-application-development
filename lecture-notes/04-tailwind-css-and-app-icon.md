# 04: Tailwind CSS and App Icon

## Getting Started

Create a new project using the following command:

```bash
npx create-expo-app 05-playground --template blank
```

## Tailwind CSS

**TailWind CSS** is a utility-first **CSS** framework that allows you to rapidly build custom user interfaces. It provides a set of pre-defined **CSS** classes that can be used to style your components. We will specifically use **NativeWind** which is built on top of **TailWind CSS** and is designed to work with **React Native**.

### Getting Started

Complete steps 1–6 in the setup guide here: <https://www.nativewind.dev/docs/getting-started/installation>

### App.jsx

In the `App.jsx` file, add the following code:

```jsx
import "./global.css"

import { StatusBar } from "expo-status-bar";
import { Text, View } from "react-native";

const App = () => {
  return (
    <View className="flex-1 items-center justify-center bg-white">
      <Text className="text-lg text-pink-500 font-bold">
        Open up App.js to start working on your app!
      </Text>
      <StatusBar style="auto" />
    </View>
  );
};

export default App;
```

Reload your application. You should see the following:

<img src="../resources%20(ignore)/img/05/phone-1.png" width="250" height="444" />

## App Icon

In the `assets` directory, you should see two icon files:

- `adaptive-icon.png` - this is the icon that will be used on Android devices
- `icon.png` - this is the icon that will be used on iOS devices

### Getting Started

Navigate to <https://icon.kitchen>. This is a free online tool that allows you to generate icons for your application.

You have a variety of options to choose from and preview how your icon will look like on different devices.

![](<../resources%20(ignore)/img/05/web-1.png>)

Here is example of an icon using clip art:

![](<../resources%20(ignore)/img/05/web-2.png>)

Once you are happy with your icon, click on the **Download** button. This will download a zip file containing the icon files. Unzip the file and go to the `ios` directory. Rename the `AppIcon~ios-marketing.png` file to `icon.png`. Create a copy of the `icon.png` file and rename it to `adaptive-icon.png`. Copy both files to the `assets` directory.

Reload your application. You should see the following on the **Expo Go** home screen:

<img src="../resources%20(ignore)/img/05/phone-2.png" width="250" height="444" />

## Research Tasks

1. Using **Tailwind CSS**, style the `HomeScreen.jsx` file. It should look like the following:

<img src="../resources%20(ignore)/img/05/research-task-1.png" width="250" height="444" />

2. Use the following resource to style the `Button` components on the `HomeScreen` - <https://docs.expo.dev/ui-programming/react-native-styling-buttons>

3. Use the following resource to create a toast - <https://docs.expo.dev/ui-programming/react-native-toast>

**Note:** You should have the necessary knowledge to start the **Project** assessment.

## Submission

Learning to use AI tools is an important skill. While AI tools are powerful, you **must** be aware of the following:

- If you provide an AI tool with a prompt that is not refined enough, it may generate a not-so-useful response
- Do not trust the AI tool's responses blindly. You **must** still use your judgement and may need to do additional research to determine if the response is correct
- Acknowledge what AI tool you have used. In the assessment's repository `README.md` file, please include what prompt(s) you provided to the AI tool and how you used the response(s) to help you with your work

**ATTENTION:** The use of AI tools to generate code is **permitted** in formative assessment but **not permitted** in any summative assessments. All submitted work must be entirely your own, reflecting your independent understanding and effort. 

