# **14: Adaptive Launcher Icon**

An **adaptive launcher icon** is a graphic that represents your application to users. 

An icon can:
- Appear in the list of applications installed on a device
- Represent a shortcut to your application
- Help users find your application on **Google Play Store**

## Create an Adaptive Launcher Icon

1. To create a new **adaptive launcher icon**, right-click on `res/mipmap` **> New > Image Asset**.

<img src="../resources/img/16-adaptive-launcher-icon/16-asset-studio-1.png" width="400" height="500" />

2. You will be presented with the following window. **Note:** you will have a pre-existing **adaptive launcher icon**. 

<img src="../resources/img/16-adaptive-launcher-icon/16-asset-studio-2.png" width="500" height="350" />

3. The **foreground** layer is the icon itself. You can use your image, clip art, or text.

<img src="../resources/img/16-adaptive-launcher-icon/16-asset-studio-3.png" width="500" height="350" />

4. The **background** layer is the background color behind the icon, i.e., red.

<img src="../resources/img/16-adaptive-launcher-icon/16-asset-studio-4.png" width="500" height="350" />

5. Once you are happy with your icon, click **Next** then **Finish**. 

<img src="../resources/img/16-adaptive-launcher-icon/16-asset-studio-5.png" width="500" height="350" />

**Resources:**
- https://developer.android.com/guide/practices/ui_guidelines/icon_design_adaptive
- https://medium.com/google-design/understanding-android-adaptive-icons-cee8a9de93e2
- https://medium.com/google-design/designing-adaptive-icons-515af294c783
- https://medium.com/androiddevelopers/implementing-adaptive-icons-1e4d1795470e
- https://developer.android.com/google-play/resources/icon-design-specifications
- https://developer.android.com/studio/write/image-asset-studio

**Note::** play around with the **Image Asset** studio. You will learn more if you try things out.

## AndroidManifest.xml

`android:icon` and `android:roundIcon` attributes allow you to specify your application's launcher icons.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="op.mobile.app.dev.bottom.navigation">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher" <!-- Square icon -->
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round" <!-- Circle icon -->
        android:supportsRtl="true"
        android:theme="@style/Theme.BottomNavigation">
        <activity
            android:name=".MainActivity"
            android:theme="@style/Theme.BottomNavigation.NoActionBar">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```
