# 10: Speech

## Getting Started

Create a new project using the following command:

```bash
npx create-expo-app 11-playground
```

## Speech

### Getting Started

To get started, install the following dependency:

```bash
npm install expo-speech
```

### App.jsx

```jsx
import { View, StyleSheet, Button } from "react-native";
import { speak } from "expo-speech";

const App = () => {
  const speakText = () => {
    const thingToSay = "Hello, World!";
    speak(thingToSay);
  };

  return (
    <View style={styles.container}>
      <Button title="Press to hear some words" onPress={speakText} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
  },
});

export default App;
```

Reload your application. You should see the following:

## Research Tasks

1. Using the **Musixmatch** API, display the name of a song in a `Text` component. When the user clicks on the `Button` component, the lyrics should be read aloud using the `speak` function. 

Here is an example endpoint - <https://api.musixmatch.com/ws/1.1/track.get?commontrack_id=5920049&apikey=<ADD YOUR API KEY HERE>.

You can find a **Musixmatch** API key on the **Microsoft Teams** course channel under `Files` > `api-keys` > `musixmatch-api-key.txt`.

2. In this task, you will create a language translator. Create two `TextInput` components. When the user enters text into the first `TextInput` component, the text should be translated into the language specified in the second `TextInput` component. For example, if the user enters `Hello, World!` into the first `TextInput` component and `fr` into the second `TextInput` component, the text should be translated into French.