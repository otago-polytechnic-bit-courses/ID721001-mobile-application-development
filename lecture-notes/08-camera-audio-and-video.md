# 08: Camera, Audio and Video

## Getting Started

Create a new project using the following command:

```bash
npx create-expo-app 08-playground
```

## Camera

### Getting Started

To get started, install the following dependencies:

```bash
npm install expo-constants expo-camera expo-media-library @expo/vector-icons
```

- `expo-constants` - provides system information, i.e., device name, operating system, etc.
- `expo-camera` - provides access to the device's camera
- `expo-media-library` - provides access to the device's media library
- `@expo/vector-icons`

### Button.jsx

```jsx
import { Text, TouchableOpacity, StyleSheet } from "react-native";
import { Entypo } from "@expo/vector-icons";

const Button = (props) => {
  return (
    <TouchableOpacity onPress={props.onPress} style={styles.button}>
      <Entypo name={props.icon} size={28} color="#f1f1f1" />
      <Text style={styles.text}>{props.title}</Text>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  button: {
    height: 40,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
  },
  text: {
    fontWeight: "bold",
    fontSize: 16,
    color: "#f1f1f1",
    marginLeft: 10,
  },
});

export default Button;
```

### App.jsx

```js
import { useState, useEffect, useRef } from "react";
import { Text, View, StyleSheet, Image } from "react-native";
import Constants from "expo-constants";
import { Camera } from "expo-camera";
import { createAssetAsync, requestPermissionsAsync } from "expo-media-library";

import Button from "./components/Button";

const App = () => {
  const [hasCameraPermission, setHasCameraPermission] = useState(null);
  const [image, setImage] = useState(null);

  const cameraRef = useRef(null);

  useEffect(() => {
    // Request permission to access camera. This will prompt the user for permission
    const requestCameraPermissions = async () => {
      try {
        await requestPermissionsAsync(); // Request permission to access media library
        const cameraStatus = await Camera.requestCameraPermissionsAsync(); // Request permission to access camera
        setHasCameraPermission(cameraStatus.status === "granted"); // Check if permission was granted
      } catch (err) {
        console.error(err);
      }
    };

    requestCameraPermissions();
  }, []);

  const takeAPicture = async () => {
    if (cameraRef) {
      try {
        const data = await cameraRef.current.takePictureAsync(); // Take a picture
        setImage(data.uri); // Set the image
      } catch (err) {
        console.log(err);
      }
    }
  };

  const savePicture = async () => {
    if (image) {
      try {
        await createAssetAsync(image); // Save the picture to the device's media library
        alert("Picture saved");
        setImage(null); // Reset the image
      } catch (err) {
        console.log(err);
      }
    }
  };

  if (!hasCameraPermission) return <Text>No access to camera</Text>;

  return (
    <View style={styles.container}>
      {!image ? (
        <Camera style={styles.camera} ref={cameraRef} />
      ) : (
        <Image source={{ uri: image }} style={styles.camera} />
      )}

      <View style={styles.controls}>
        {image ? (
          <View
            style={{
              flexDirection: "row",
              justifyContent: "space-between",
              paddingHorizontal: 50,
            }}
          >
            <Button
              title="Retake"
              onPress={() => setImage(null)}
              icon="retweet"
            />
            <Button title="Save" onPress={savePicture} icon="check" />
          </View>
        ) : (
          <Button title="Take a Picture" onPress={takeAPicture} icon="camera" />
        )}
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    paddingTop: Constants.statusBarHeight,
    backgroundColor: "#000",
    padding: 8,
  },
  controls: {
    flex: 0.5,
  },
  camera: {
    flex: 5,
    borderRadius: 20,
  },
});

export default App;
```

Reload your application. You should see the following:

1. The user will be prompted to allow the application to access their media and photos

<img src="../resources%20(ignore)/img/08/phone-1.png" width="250" height="444" />

2. The user will be prompted to allow the application to take pictures and record video

<img src="../resources%20(ignore)/img/08/phone-2.png" width="250" height="444" />

3. Once the user allows permissions, they can take a picture

<img src="../resources%20(ignore)/img/08/phone-3.png" width="250" height="444" />

4. The user can then save the picture to their device or retake the picture

<img src="../resources%20(ignore)/img/08/phone-4.png" width="250" height="444" />

## Audio

### Getting Started

To get started, install the following dependency:

```bash
npm install expo-av
```

### App.jsx

```jsx
// ...
import { Audio } from "expo-av";

// ...

const App = () => {
  // ...
  const [sound, setSound] = useState(null);

  // ...

  useEffect(() => {
    return sound
      ? () => {
        sound.unloadAsync();
      }
      : undefined;
  }, [sound]);

  const playASound = async () => {
    const { sound } = await Audio.Sound.createAsync(require("./assets/sounds/music.mp3")
    );
    setSound(sound);
    await sound.playAsync();
  }

  // ...

  return (
    <View style={styles.container}>
      {/* ... */}

      <View style={styles.controls}>
        {image ? (
          // ...
        ) : (
          <View
            style={{
              flexDirection: "row",
              justifyContent: "space-between",
              paddingHorizontal: 50,
            }}>
            <Button title="Take a Picture" onPress={takePicture} icon="camera" />
            <Button title="Play a Sound" onPress={playASound} icon="sound" />
          </View>
        )}
      </View>
    </View>
  );
};

// ...
```

Reload your application. You should see the following:

<img src="../resources%20(ignore)/img/08/phone-5.png" width="250" height="444" />

## Video

### App.jsx

```jsx
import { useRef, useState } from "react";
import { View, StyleSheet, Button } from "react-native";
import { Video, ResizeMode } from "expo-av";

const App = () => {
  const video = useRef(null);
  const [status, setStatus] = useState({});

  return (
    <View style={styles.container}>
      <Video
        ref={video}
        style={styles.video}
        source={{
          uri: "https://d23dyxeqlo5psv.cloudfront.net/big_buck_bunny.mp4",
        }}
        useNativeControls
        resizeMode={ResizeMode.CONTAIN}
        isLooping
        onPlaybackStatusUpdate={(status) => setStatus(() => status)}
      />
      <View style={styles.buttons}>
        <Button
          title={status.isPlaying ? "Pause" : "Play"}
          onPress={() =>
            status.isPlaying
              ? video.current.pauseAsync()
              : video.current.playAsync()
          }
        />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    backgroundColor: "#ecf0f1",
  },
  video: {
    alignSelf: "center",
    width: 320,
    height: 200,
  },
  buttons: {
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
  },
});

export default App;
```

Reload your application. You should see the following:

1.

<img src="../resources%20(ignore)/img/08/phone-6.png" width="250" height="444" />

2.

<img src="../resources%20(ignore)/img/08/phone-7.png" width="250" height="444" />

## Research Tasks
