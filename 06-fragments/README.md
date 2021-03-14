# **Fragments**

## Overview
A `Fragment` is a reusable portion of your application's UI. A **fragment** defines & manages its own layout, has its own lifecycle & can handle events. However, **fragments** cannot live on their own meaning they must be hosted by either an **activity** or another **fragment**. The **fragment's** view hierarchy attaches to the host's view hierarchy, i.e., an **activity's'** or another **fragment's** view hierarchy.

## Modularity
**Fragments** are extremely useful for modularity & reusability in your **activity's** UI & it does this by allowing you divide the UI into chunks. **Activities** are an ideal place to put global UI elements such as a **navigation**. **Fragments** are better suited to define & manage the UI of a single screen or portion of a screen.

Here is an example...consider an application that responds to various screen sizes, i.e., a mobile and tablet device. On a tablet device, the application should display a static **navigation drawer** & a list in a **grid layout**. On a mobile device, the application should display a **bottom navigation bar** & a list in a **linear layout**. Managing all the variations in the activity can be tedious...trust me. Separating navigation elements from the content can be much more manageable. The **activity** is responsible for displaying the correct UI navigation & the **fragment** is responsible for displaying the list in a layout.

<img src="../tex/img/06-fragments/readme/fragment-large-small-screen.png" width="750" height="450" />

While your **activity** is in the `onStart` lifecycle state or higher, **fragments** can be added, replaced or removed from an **activity**.