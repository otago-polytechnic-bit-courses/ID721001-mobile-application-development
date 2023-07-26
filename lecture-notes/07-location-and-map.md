# 07: Location and Map

## Getting Started

Create a new project using the following command:

```bash
npx create-expo-app 07-playground
```

## Location

### Getting Started

### App.jsx

In the `App.jsx` file, add the following code:

```js
import { useState, useEffect } from 'react';
import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";
import { getCurrentPositionAsync, requestForegroundPermissionsAsync, LocationAccuracy } from 'expo-location';

const App = () => {
  const [location, setLocation] = useState(null);
  const [errorMsg, setErrorMsg] = useState(null);

  useEffect(() => {
    const getLocation = async () => {
      try {
        // Request permission to access location. This will prompt the user for permission
        let { status } = await requestForegroundPermissionsAsync();
        if (status !== 'granted') {
          setErrorMsg('Permission to access location was denied');
          return;
        }

        // Get the current location with high accuracy
        let location = await getCurrentPositionAsync({ accuracy: LocationAccuracy.High });
        setLocation(location);
      } catch (error) {
        setErrorMsg('Error while fetching location');
        console.error(error);
      }
    };

    getLocation();
  }, []);

  let text = 'Waiting..';
  if (errorMsg) {
    text = errorMsg;
  } else if (location) {
    text = JSON.stringify(location);
  }

  return (
    <View style={styles.container}>
      <Text>{text}</Text>
      <StatusBar style="auto" />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});

export default App;
```

Reload your application. You should see the following:

1. The user will be prompted to allow the application to access their location

<img src="../resources/img/07/phone-1.png" width="250" height="444" />

2. If the user allows the application to access their location, their information will be displayed on the screen

<img src="../resources/img/07/phone-2.png" width="250" height="444" />

3. If the user denies the application to access their location, an error message will be displayed on the screen

<img src="../resources/img/07/phone-3.png" width="250" height="444" />

## Map


