# **13a: Firebase Auth - Email and Password**

In this session, you are going to let your users authenticate with **Firebase** using an email and password.

Firstly, open **bottom-navigation-auth** in **Android Studio**. Familarise yourself with the code.

## How to implement Firebase email and password authentication in your application

The initial setup process is the same as authenticating with **Google**. You do not need to add addition dependencies to use email and password authentication with **Firebase**.

## RegisterFragment XML Layout

As a requirement in the **Project** assessment, you will need to register a new user. To do this, you will create an **XML** layout file named `fragment_register.xml`. It should contain the following:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/tv_header"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="32dp"
        android:gravity="center"
        android:text="Register"
        android:textSize="24sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/et_email_address"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="32dp"
        android:ems="10"
        android:hint="Email Address"
        android:inputType="textEmailAddress"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/tv_header" />

    <EditText
        android:id="@+id/et_password"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="32dp"
        android:ems="10"
        android:hint="Password"
        android:inputType="textPassword"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/et_email_address" />

    <EditText
        android:id="@+id/et_confirm_password"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="32dp"
        android:ems="10"
        android:hint="Confirm Password"
        android:inputType="textPassword"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/et_password" />

    <Button
        android:id="@+id/btn_register"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="32dp"
        android:text="Register"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/et_confirm_password" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## RegisterFragment

Import the necessary APIs.

```kotlin
class RegisterFragment: Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        // Get a new instance of Firebase authentication
        auth = FirebaseAuth.getInstance()

        val btnRegister: Button = view.findViewById(R.id.btn_register)
        val etEmailAddress: EditText = view.findViewById(R.id.et_email_address)
        val etPassword: EditText = view.findViewById(R.id.et_password)
        val etConfirmPassword: EditText = view.findViewById(R.id.et_confirm_password)

        btnRegister.setOnClickListener {
            val email = etEmailAddress.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            // Set of validation rules
            when {
                email.isEmpty() ->
                    etEmailAddress.error = "Email is required."
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                    etEmailAddress.error = "Invalid email address format."
                password.isEmpty() ->
                    etPassword.error = "Password is required."
                password.length < 8 ->
                    etPassword.error = "Password needs to be at least eight characters."
                password != confirmPassword ->
                    etConfirmPassword.error = "Passwords do not match."
                else -> {
                    register(email, password) // Call if validation rules pass
                }
            }
        }

        return view
    }

    /**
     * This function registers a new user using Firebase authentication
     *
     * @param email the new user's email address
     * @param password the new user's password
     */
    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Indicate to the application user that a new user has been created
                    Toast.makeText(
                        activity,
                        "User created.",
                        Toast.LENGTH_LONG
                    ).show()
                    
                    // Navigate to the login screen. Check mobile_navigation.xml for this action
                    view?.findNavController()?.navigate(
                        RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    )
                } else {
                    // Indicate to the application user if the email address already exists
                    Toast.makeText(
                        activity,
                        "Email address already exists.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}
```

## LoginFragment XML Layout

In `fragment_login.xml`, add the following:

```xml
  <TextView
      android:id="@+id/tv_sign_up"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_marginStart="32dp"
      android:layout_marginTop="16dp"
      android:text="Sign Up"
      app:layout_constraintStart_toStartOf="parent"
      app:layout_constraintTop_toBottomOf="@+id/btn_login" />
```

The **Google** sign-in button has been removed for the purposes of this lecture. The `TextView` is constrained to the default login button.

## LoginFragment

```kotlin
class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        auth = FirebaseAuth.getInstance()

        val btnLogin: Button = view.findViewById(R.id.btn_login)
        val etEmailAddress: EditText = view.findViewById(R.id.et_email_address)
        val etPassword: EditText = view.findViewById(R.id.et_password)
        val tvSignUp: TextView = view.findViewById(R.id.tv_sign_up)

        btnLogin.setOnClickListener {
            val email = etEmailAddress.text.toString()
            val password = etPassword.text.toString()

            // Set of validation rules
            when {
                email.isEmpty() ->
                    etEmailAddress.error = "Email is required."
                password.isEmpty() ->
                    etPassword.error = "Password is required"
                else -> {
                    firebaseAuthSignInWithEmailAndPassword(email, password) // Call if validation rules pass
                }
            }
        }

        // You can set on click listeners for other Views
        tvSignUp.setOnClickListener {
            // Navigate to the register screen. Check mobile_navigation.xml for this action
            val action =
                LoginFragmentDirections
                    .actionLoginFragmentToRegisterFragment()
            view?.findNavController()?.navigate(action)
        }

        return view
    }

    /**
     * This function signs in a user using Firebase authentication
     *
     * @param email the user's email address
     * @param password the user's password
     */
    private fun firebaseAuthSignInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password) // In-built Firebase authentication function
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    // Navigate to the home screen. Check mobile_navigation.xml for this action
                    val action =
                        LoginFragmentDirections
                            .actionLoginFragmentToHomeFragment()
                    view?.findNavController()?.navigate(action)
                } else {
                    // Indicate to the application user that their credentials may be incorrect
                    Toast.makeText(
                        activity,
                        "Failed to sign in.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
```

## Final Remarks

It is important that you look at the navigation graph in the `mobile_navigation.xml`. You will need to declare a new fragment for `RegisterFragment` and the appropriate actions for navigating between the login and register screen.
