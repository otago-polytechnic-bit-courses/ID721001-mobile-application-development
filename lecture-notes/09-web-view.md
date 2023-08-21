# 09: Web View

## Getting Started

Create a new project using the following command:

```bash
npx create-expo-app 09-playground
```

## WebView

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
