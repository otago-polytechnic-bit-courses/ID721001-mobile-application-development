# 05: Tailwind CSS and Toast Messages

## Getting Started

Create a new project using the following command:

```bash
npx create-expo-app 05-playground
```

## Tailwind CSS

**TailWind CSS** is a utility-first **CSS** framework that allows you to rapidly build custom user interfaces. It provides a set of pre-defined **CSS** classes that can be used to style your components. We will specifically use **NativeWind** which is built on top of **TailWind CSS** and is designed to work with **React Native**.

### Getting Started

```bash
npm install nativewind
npm install -D tailwindcss@3.3.2
```

Run the following command to create a `tailwind.config.js` file.

```bash
npx tailwindcss init
```

### tailwind.config.js

In the `tailwind.config.js` file, you should see the following code:

```js
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [],
  theme: {
    extend: {},
  },
  plugins: [],
};
```

Add the following code to the `tailwind.config.js` file:

```js
module.exports = {
  content: [
    "./App.{js,jsx,ts,tsx}",
    "./components/**/*.{js,jsx,ts,tsx}",
    "./screens/**/*.{js,jsx,ts,tsx}",
  ],
  // ...
};
```

### babel.config.js

In the `babel.config.js` file, add the following code:

```js
plugins: [/* ... */, "nativewind/babel"],
```

### App.jsx

In the `App.jsx` file, add the following code:

```jsx
import { StatusBar } from "expo-status-bar";
import { Text, View } from "react-native";

const App = () => {
  return (
    <View className="flex-1 items-center justify-center bg-white">
      <Text>Open up App.js to start working on your app!</Text>
      <StatusBar style="auto" />
    </View>
  );
};

export default App;
```

## Toast Messages
