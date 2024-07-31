# 03: ScrollView and Lists

## Getting Started

Create a new project using the following command:

```bash
npx create-expo-app 04-playground --template blank
```

## ScrollView

A **scroll view** is a component that provides a scrollable view of its content. It is used to display a large amount of content that does not fit within the device's screen and allows the user to scroll through the content by swiping up or down.

Here is an example of a **scroll view** - <https://reactnative.dev/docs/scrollview>

## FlatList

A **flat list** is a component that provides an efficient way to display lists of data. It is similar to a **scroll view** but is optimised to only display its content that is currently visible on the device's screen rather than rendering all of its content at once.

### App.jsx

Use the knowledge and skills gained from the `02-navigation` lecture to implement **stack navigation**.

```jsx
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";

import HomeScreen from "./screens/HomeScreen";
import DetailsScreen from "./screens/DetailsScreen";

const Stack = createNativeStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
```

### nbaTeams.js

In the root directory, create a new directory called `data`. In the `data` directory, create a new file called `nbaTeams.js`. In the `nbaTeams.js` file, add the following code:

```js
const nbaTeams = [
  { key: "Atlanta Hawks" },
  { key: "Boston Celtics" },
  { key: "Brooklyn Nets" },
  { key: "Charlotte Hornets" },
  { key: "Chicago Bulls" },
  { key: "Cleveland Cavaliers" },
  { key: "Dallas Mavericks" },
  { key: "Denver Nuggets" },
  { key: "Detroit Pistons" },
  { key: "Golden State Warriors" },
  { key: "Houston Rockets" },
  { key: "Indiana Pacers" },
  { key: "LA Clippers" },
  { key: "Los Angeles Lakers" },
  { key: "Memphis Grizzlies" },
  { key: "Miami Heat" },
  { key: "Milwaukee Bucks" },
  { key: "Minnesota Timberwolves" },
  { key: "New Orleans Pelicans" },
  { key: "New York Knicks" },
  { key: "Oklahoma City Thunder" },
  { key: "Orlando Magic" },
  { key: "Philadelphia 76ers" },
  { key: "Phoenix Suns" },
  { key: "Portland Trail Blazers" },
  { key: "Sacramento Kings" },
  { key: "San Antonio Spurs" },
  { key: "Toronto Raptors" },
  { key: "Utah Jazz" },
  { key: "Washington Wizards" },
];

export default nbaTeams;
```

### HomeScreen.jsx

In the root directory, create a new directory called `screens`. In the `screens` directory, create a new file called `HomeScreen.jsx`. Add the following code:

```jsx
import { FlatList, StyleSheet, Text, View } from "react-native";
import nbaTeams from "../data/nba-teams";

const HomeScreen = () => {
  return (
    <View style={styles.container}>
      <FlatList
        data={nbaTeams}
        renderItem={({ item }) => <Text style={styles.item}>{item.key}</Text>}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  item: {
    padding: 10,
    fontSize: 18,
    height: 44,
  },
});

export default HomeScreen;
```

Reload your application. You should see the following:

<img src="../resources%20(ignore)/img/04/phone-1.png" width="250" height="444" />

## SectionList

### nbaTeams.js

Refactor `nbaTeams.js` to the following:

```js
const nbaTeams = [
  { key: "Atlanta Hawks", division: "Southeast" },
  { key: "Boston Celtics", division: "Atlantic" },
  { key: "Brooklyn Nets", division: "Atlantic" },
  { key: "Charlotte Hornets", division: "Southeast" },
  { key: "Chicago Bulls", division: "Central" },
  { key: "Cleveland Cavaliers", division: "Central" },
  { key: "Dallas Mavericks", division: "Southwest" },
  { key: "Denver Nuggets", division: "Northwest" },
  { key: "Detroit Pistons", division: "Central" },
  { key: "Golden State Warriors", division: "Pacific" },
  { key: "Houston Rockets", division: "Southwest" },
  { key: "Indiana Pacers", division: "Central" },
  { key: "LA Clippers", division: "Pacific" },
  { key: "Los Angeles Lakers", division: "Pacific" },
  { key: "Memphis Grizzlies", division: "Southwest" },
  { key: "Miami Heat", division: "Southeast" },
  { key: "Milwaukee Bucks", division: "Central" },
  { key: "Minnesota Timberwolves", division: "Northwest" },
  { key: "New Orleans Pelicans", division: "Southwest" },
  { key: "New York Knicks", division: "Atlantic" },
  { key: "Oklahoma City Thunder", division: "Northwest" },
  { key: "Orlando Magic", division: "Southeast" },
  { key: "Philadelphia 76ers", division: "Atlantic" },
  { key: "Phoenix Suns", division: "Pacific" },
  { key: "Portland Trail Blazers", division: "Northwest" },
  { key: "Sacramento Kings", division: "Pacific" },
  { key: "San Antonio Spurs", division: "Southwest" },
  { key: "Toronto Raptors", division: "Atlantic" },
  { key: "Utah Jazz", division: "Northwest" },
  { key: "Washington Wizards", division: "Southeast" },
];

export default nbaTeams;
```

### HomeScreen.jsx

Refactor `HomeScreen.jsx` to the following:

```jsx
import { SectionList, StyleSheet, Text, View } from "react-native";
import nbaTeams from "../data/nba-teams";

const HomeScreen = () => {
  const getTeamsByDivision = (division) =>
    nbaTeams.filter((team) => team.division === division);

  const renderItem = ({ item }) => <Text style={styles.item}>{item.key}</Text>;

  const renderSectionHeader = ({ section }) => (
    <Text style={styles.sectionHeader}>{section.title}</Text>
  );

  const sections = [
    { title: "Atlantic", data: getTeamsByDivision("Atlantic") },
    { title: "Central", data: getTeamsByDivision("Central") },
    { title: "Northwest", data: getTeamsByDivision("Northwest") },
    { title: "Pacific", data: getTeamsByDivision("Pacific") },
    { title: "Southeast", data: getTeamsByDivision("Southeast") },
    { title: "Southwest", data: getTeamsByDivision("Southwest") },
  ];

  return (
    <View style={styles.container}>
      <SectionList
        sections={sections}
        renderItem={renderItem}
        renderSectionHeader={renderSectionHeader}
        keyExtractor={(item) => item.key}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  sectionHeader: {
    padding: 10,
    fontSize: 18,
    fontWeight: "bold",
    backgroundColor: "lightgray",
  },
  item: {
    padding: 10,
    fontSize: 18,
    height: 44,
  },
});

export default HomeScreen;
```

Reload your application. You should see the following:

<img src="../resources%20(ignore)/img/04/phone-2.png" width="250" height="444" />

## Research Tasks

1. Refactor the `sections` array to use the `map` method.

2. Add an `Image` component to the `renderItem` function. This should display the team logo. You can find the images in the given `nba-teams-imgs` directory. **Note:** You will need to update the `nbaTeams` array to include the path to the team logo.

<img src="../resources%20(ignore)/img/04/research-task-1.png" width="250" height="444" />

3. When you click on a team, navigate to a new screen called `TeamDetails`. This screen should display the team logo and the team name. **Note:** Use the `TouchableOpacity` component to make the team name clickable.

<img src="../resources%20(ignore)/img/04/research-task-2.png" width="250" height="444" />

4. Create a new screen called `TopStories`. This screen should display the title of the first 30 top stories via the [Hacker News API](https://github.com/HackerNews/API).

<img src="../resources%20(ignore)/img/04/research-task-3.png" width="250" height="444" />

5. Truncate the title of each story. **Note:** Look into the `numberOfLines` and `ellipsizeMode` props.

<img src="../resources%20(ignore)/img/04/research-task-4.png" width="250" height="444" />

6. Create a **bottom tab navigation** that allows the user to navigate between the `TopStories` and `AskStories` screens. **Note:** The `AskStories` screen should display the title of the first 30 ask stories via the [Hacker News API](https://github.com/HackerNews/API).

<img src="../resources%20(ignore)/img/04/research-task-5.png" width="250" height="444" /> <img src="../resources%20(ignore)/img/04/research-task-6.png" width="250" height="444" />

7. Create functionality that allows the user to search for a story using this resource - <https://aboutreact.com/react-native-search-bar-filter-on-listview>

<img src="../resources%20(ignore)/img/04/research-task-7.png" width="250" height="444" /> <img src="../resources%20(ignore)/img/04/research-task-8.png" width="250" height="444" />

## Submission

If you get stuck on any of the above research tasks, feel free to use **ChatGPT** permitting, you are aware of the following:

- If you provide **ChatGPT** with a prompt that is not refined enough, it may generate a not-so-useful response
- Do not trust **ChatGPT's** responses blindly. You must still use your judgement and may need to do additional research to determine if the response is correct
- Acknowledge that you are using **ChatGPT**. In the **README.md** file, please include what prompt(s) you provided to **ChatGPT** and how you used the response(s) to help you with your work

Create a new branch called **week-03-formative-assessment**. Add, commit and push your code.
