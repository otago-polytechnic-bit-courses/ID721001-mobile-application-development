# **13: Firebase Auth**

In this session, you are going to let your users authenticate with **Firebase** using their own **Google** accounts by integrating **Google** sign-in into your application.

Firstly, open **bottom-navigation-auth** in **Android Studio**. Familarise yourself with the code.

## How to Implement Firebase Authentication in your Application

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

### Create a Firebase Project

Sign into the **Firebase Console** uisng your **Google account**. Link to the **Firebase Console** - https://console.firebase.google.com

<img src="https://raw.githubusercontent.com/otago-polytechnic-bit-courses/IN721-mobile-application-development/master/resources/img/13-firebase-auth/13-firebase-auth-1.png" width=800 height=500 />

**Steps:**

1. Click **Add Project**.
2. Name your **Project** (I recommend **your Otago Polytechnic username-travelling**, for example, **graysono-travelling**)  and click **Continue**.
3. Disable **Firebase Analytics** and click **Continue**.
4. Wait while your **Project** being created. Once, it has been created, click **Continue**.

You will presented with the following window:

<img src="https://raw.githubusercontent.com/otago-polytechnic-bit-courses/IN721-mobile-application-development/master/resources/img/13-firebase-auth/13-firebase-auth-2.png" width=800 height=500 />

Click **Authentication** in the left-hand side panel, then click **Get Started**.

Enable the following:
- **Username/Password**. Ignore **Passwordless** option and click **Save**.
- **Goggle**. Provide your **Google** account's email and click **Save**.

<img src="https://raw.githubusercontent.com/otago-polytechnic-bit-courses/IN721-mobile-application-development/master/resources/img/13-firebase-auth/13-firebase-auth-3.png" width=800 height=500 />

### Add Firebase to your Android Application

1. Got back to **Project Overview** and click the **Android** icon.

<img src="https://raw.githubusercontent.com/otago-polytechnic-bit-courses/IN721-mobile-application-development/master/resources/img/13-firebase-auth/13-firebase-auth-4.png" width=800 height=500 />

2. Register your application. Please make sure you provide information for both optional inputs.
3. Download the configuration file and put it in your application's `app` directory.
4. You should have added all required dependencies to use **Firebase Authentication** so you can skip this.

<img src="https://raw.githubusercontent.com/otago-polytechnic-bit-courses/IN721-mobile-application-development/master/resources/img/13-firebase-auth/13-firebase-auth-5.png" width=800 height=500 />

### LoginFragment XML Layout

Add the following:

```xml
<com.google.android.gms.common.SignInButton
    android:id="@+id/btn_google_login"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_marginStart="32dp"
    android:layout_marginTop="16dp"
    android:layout_marginEnd="32dp"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/btn_login" />
```

A **Google** sign in button will be placed under the login button.

### LoginFragment
1. Declare two variables under the `LoginFragment` class declaration - `FirebaseAuth` and `GoogleSignInClient`.

```kotlin
private lateinit var auth: FirebaseAuth
private lateinit var googleSignInClient: GoogleSignInClient
```

2. In `onCreateView()`, instantiate `FirebaseAuth` and `GoogleSignInClient`.

```kotlin
auth = FirebaseAuth.getInstance()

val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestIdToken(getString(R.string.default_web_client_id))
    .requestEmail() 
    .build()
googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
```

3. In `onCreateView()`, find the **Google** sign in button by its id.

```kotlin
val btnGoogleLogin: SignInButton = view.findViewById(R.id.btn_google_login)
```

4. Under `onCreateView`, add the following:

```kotlin
    // Prompt the user to sign in using their Google account's email
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // Once the user has chosen their Google account's email 
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception

            // Authenticate with Firebase if Google sign in is successful
            if (task.isSuccessful) {
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                // Catch Google sign in issues
                } catch (e: ApiException) {
                    Toast.makeText(
                        activity,
                        "Failed to sign in with Google",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            // Display a message if there is Firebase Authentication issues
            } else {
                Toast.makeText(activity, exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    /** 
     * After a user successfully signs in, get an idToken from the GoogleSignInAccount 
     * object, exchange it for a Firebase credential, and authenticate with Firebase
     * using the Firebase credential
     */ 
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) {
                // Navigate to HomeFragment
                if (it.isSuccessful) {
                    val action =
                        LoginFragmentDirections
                            .actionLoginFragmentToHomeFragment()
                    view?.findNavController()?.navigate(action)
                } else {
                    Toast.makeText(
                        activity,
                        "Failed to authenticate with Google",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    companion object {
        const val RC_SIGN_IN = 9001
    }
```

5. In `onCreateView()`, bind the `signIn()` method to `btnGoogleLogin`:

```kotlin
btnGoogleLogin.setOnClickListener {
    signIn()
}
```

## Resources
- https://firebase.google.com/docs/android/setup
- https://firebase.google.com/docs/auth/android/google-signin
