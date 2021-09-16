# **15: Localization**

With Android being the most popular mobile os, making sure localization is done correctly has become crucial in application development.

## Resources

You will use the **resources** in `res/values/strings.xml`. Make sure you do not have hard-coded **strings** in the selected screen, i.e., `Fragment`'s **XML** layout file. `res/values/strings.xml` should use the default language, i.e., English, which is the language that you expect most application user's to speak.

An essential part of localization is providing alternative text for different languages, i.e., French, Japanese. To do this, you can create two alternative `strings.xml` files:

- `res/values-fr/strings.xml` - contains French text for all the strings except application name
- `res/values-ja/strings.xml` - contains Japanese text for all the strings except application name

## Self-Directed Task

Using the following resources, particularly the **YouTube** video, implement localization for **one** screen in your **Travelling** application. It will meet the requirement as described in the **Project** assessment document. 

- https://developer.android.com/guide/topics/resources/localization
- https://www.youtube.com/watch?v=XNFz97zqN-E