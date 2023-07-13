# 02: Navigation

## Getting Started

```bash
npx create-expo-app 02-playground
```

```bash
npm install @react-navigation/native
```

```bash
npm install react-native-screens react-native-safe-area-context
```

```bash
npx pod-install ios
```

## Stack Navigation

```bash
npm install @react-navigation/native-stack
```

### HomeScreen.jsx

```jsx
import { View, Text } from "react-native";

const HomeScreen = () => {
  return (
    <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
      <Text>Home Screen</Text>
    </View>
  );
};

export default HomeScreen;
```

### App.jsx

```jsx
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";

import HomeScreen from "./screens/HomeScreen";

const Stack = createNativeStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Home" component={HomeScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
```

### DetailsScreen.jsx

```jsx
import { View, Text } from "react-native";

const DetailsScreen = () => {
  return (
    <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
      <Text>Details Screen</Text>
    </View>
  );
};

export default DetailsScreen;
```

```jsx
//...

import DetailsScreen from "./screens/DetailsScreen";

//...

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} />
        <Stack.Screen name="Details" component={DetailsScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

//...
```

## Moving Between Screens

### HomeScreen.jsx

```jsx
import { Button, View, Text } from "react-native";

const HomeScreen = (props) => {
  return (
    <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
      <Text>Home Screen</Text>
      <Button
        title="Go to Details"
        onPress={() => props.navigation.navigate("Details")}
      />
    </View>
  );
};

//...
```

### DetailsScreen.jsx

```jsx
import { Button, View, Text } from "react-native";

const DetailsScreen = (props) => {
  return (
    <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
      <Text>Details Screen</Text>
      <Button title="Go back" onPress={() => props.navigation.goBack()} />
    </View>
  );
};

//...
```

<img src="../resources/img/02/phone-1.png" width="250" height="444" />

<img src="../resources/img/02/phone-2.png" width="250" height="444" />

## Passing Parameters to Routes

### HomeScreen.jsx

```jsx
//...

const HomeScreen = (props) => {
  return (
    <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
      <Text>Home Screen</Text>
      <Button
        title="Go to Details"
        onPress={() =>
          props.navigation.navigate("Details", {
            first_name: "John",
            last_name: "Doe",
          })
        }
      />
    </View>
  );
};

//...
```

### DetailsScreen.jsx

```jsx
//...

const DetailsScreen = (props) => {
  const { first_name, last_name } = props.route.params;
  return (
    <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
      <Text>Details Screen</Text>
      <Text>First Name: {first_name}</Text>
      <Text>Last Name: {last_name}</Text>
      <Button title="Go back" onPress={() => props.navigation.goBack()} />
    </View>
  );
};

//...
```

<img src="../resources/img/02/phone-3.png" width="250" height="444" />

**Research Tasks:**

- Easy task
- Hard task

## Configuring the Header Bar

### App.jsx

```jsx
//...

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen
          name="Home"
          component={HomeScreen}
          options={{ title: "My Home" }}
        />
        <Stack.Screen
          name="Details"
          component={DetailsScreen}
          options={({ route }) => ({
            title: `${route.params.first_name} ${route.params.last_name}`,
          })}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

//...
```

<img src="../resources/img/02/phone-4.png" width="250" height="444" />

<img src="../resources/img/02/phone-5.png" width="250" height="444" />

## Bottom Tab Navigation

### Getting Started

```bash
npm install @react-navigation/bottom-tabs
```

### App.jsx

```jsx
//...

import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";

//...

const Tab = createBottomTabNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Tab.Navigator>
        <Tab.Screen
          name="Home"
          component={HomeScreen}
          options={{ title: "My Home" }}
        />
        <Tab.Screen
          name="Details"
          component={DetailsScreen}
          options={{ title: "My Details" }}
        />
      </Tab.Navigator>
    </NavigationContainer>
  );
};

//...
```

<img src="../resources/img/02/phone-5.png" width="250" height="444" />

```bash
npm install react-native-vector-icons/MaterialCommunityIcons
```

```jsx
//...

import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";

//...

const App = () => {
  return (
    <NavigationContainer>
      <Tab.Navigator>
        <Tab.Screen
          name="Home"
          component={HomeScreen}
          options={{
            title: "My Home",
            tabBarLabel: "Home",
            tabBarIcon: ({ color, size }) => (
              <MaterialCommunityIcons name="home" color={color} size={size} />
            ),
          }}
        />
        <Tab.Screen
          name="Details"
          component={DetailsScreen}
          options={{ title: "My Details" }}
        />
      </Tab.Navigator>
    </NavigationContainer>
  );
};

//...
```

<img src="../resources/img/02/phone-6.png" width="250" height="444" />

**Research Task:**

**Resource:** <https://reactnavigation.org/docs/bottom-tab-navigator>

## Drawer Navigation

### Getting Started

```bash
npm install @react-navigation/drawer
```

```bash
npm install react-native-gesture-handler react-native-reanimated@2.14.4
```

```js
module.exports = function (api) {
  api.cache(true);
  return {
    presets: ["babel-preset-expo"],
    plugins: ["react-native-reanimated/plugin"],
  };
};
```

### App.jsx

```jsx
//...

import { createDrawerNavigator } from "@react-navigation/drawer";

//...

const Drawer = createDrawerNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Drawer.Navigator>
        <Drawer.Screen
          name="Home"
          component={HomeScreen}
          options={{ title: "My Home" }}
        />
        <Tab.Screen
          name="Details"
          component={DetailsScreen}
          options={{ title: "My Details" }}
        />
      </Drawer.Navigator>
    </NavigationContainer>
  );
};

//...
```

<img src="../resources/img/02/phone-7.png" width="250" height="444" />

<img src="../resources/img/02/phone-8.png" width="250" height="444" />

**Research Task:**