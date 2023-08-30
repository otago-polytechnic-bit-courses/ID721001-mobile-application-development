# 09: Web View

## Getting Started

Create a new project using the following command:

```bash
npx create-expo-app 09-playground
```

## WebView

A `WebView` enables you to display web content in your application. It is similar to an `iframe` in **HTML**.

### Getting Started

To get started, install the following dependency:

```bash
npm install react-native-webview@11.26.1
```

### App.jsx

```jsx
import { WebView } from "react-native-webview";
import { StyleSheet } from "react-native";
import Constants from "expo-constants";

const App = () => {
  return (
    <WebView
      style={styles.container}
      source={{ uri: "https://www.youtube.com/live/IDDmrzzB14M" }}
    />
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: Constants.statusBarHeight,
  },
});

export default App;
```

Reload your application. You should see the following:

<img src="../resources%20(ignore)/img/09/phone-1.png" width="250" height="444" />

## Research Tasks

1. What is the difference between a `WebView` and an `iframe`? What are the advantages and disadvantages of each? When would you use one over the other? Write your answers in the `09-research.md` file.

2. Display ten random YouTube video links in a `FlatList`. When the user clicks on a link, the video should play in a `WebView`. 
