package op.mobile.app.dev.login.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.login.R
import op.mobile.app.dev.login.databinding.FragmentLoginBinding
import op.mobile.app.dev.login.login.LoginAdapter
import op.mobile.app.dev.login.login.LoginApplication
import op.mobile.app.dev.login.model.Login

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        val viewModelFactory =
            LoginViewModelFactory((activity?.applicationContext as LoginApplication).repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.loginViewModel = viewModel

        binding.rvLoginDetails.adapter = LoginAdapter()

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername
            val password = binding.etPassword
            when {
                username.text.toString().trim().isEmpty() -> binding.etUsername.error = "Please enter a username"
                password.text.toString().trim().isEmpty() -> binding.etPassword.error = "Please enter a password"
                else -> {
                    viewModel.insertLoginDetail(Login(username.text.toString().trim(),  password.text.toString().trim()))
                    username.text.clear()
                    password.text.clear()
                }
            }
        }

        return binding.root
    }
}