# 11: Speech

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
import { View, StyleSheet, Button } from 'react-native';
import { speak } from 'expo-speech';

const App = () => {
  const speakText = () => {
    const thingToSay = 'Hello, World!';
    speak(thingToSay);
  };

  return (
    <View style={styles.container}>
      <Button title="Press to hear some words" onPress={speakText} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
});

export default App;
```

Reload your application. You should see the following:

## Research Tasks
