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
            LoginModelFactory((activity?.applicationContext as LoginApplication).repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.loginViewModel = viewModel

        binding.rvJobs.adapter = LoginAdapter()

        binding.btnLogin.setOnClickListener {
            viewModel.insert(Login("hello", "P@ssw0rd123"))
        }

        return binding.root
    }
}