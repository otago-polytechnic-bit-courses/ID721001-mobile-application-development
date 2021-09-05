# **13: Firebase Auth**

In this session, you are going to let your users authenticate with Firebase using their own Google accounts by integrating Google sign-in into your application.

Firstly, open **bottom-navigation-auth** in **Android Studio**. Familarise yourself with the code.

## How to Implement Authentication in your Application

### build.gradle (Module)

Go to `build.gradle (Module)`, add the following in the `dependencies` block, then sync your project. Remember, it does not matter which order your dependencies are declared.

```xml
implementation platform('com.google.firebase:firebase-bom:27.1.0') // Manages all Firebase library versions by specifying only one version
implementation 'com.google.firebase:firebase-auth:21.0.1' // Firebase authentication library. If we omit the version, it will be 27.1.0, not 21.01.1
implementation 'com.google.android.gms:play-services-auth:19.0.0' // Google Play services library
```

Also, add the following in the `plugins` block.

```xml
id 'com.google.gms.google-services'
```

### build.gradle (Project)

Go to `build.gradle (Project)`, add the following in the `dependencies` block, then sync your project.

```xml
classpath 'com.google.gms:google-services:4.3.10' // Google Services plugin
```

Also, make sure you have `google()` in both `repositories` blocks.


