package op.mobile.app.dev.animal.sounds.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import op.mobile.app.dev.animal.sounds.R
import op.mobile.app.dev.animal.sounds.SplashScreenFragmentDirections
import op.mobile.app.dev.animal.sounds.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentSplashScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_splash_screen, container, false
        )

        Handler(Looper.getMainLooper()).postDelayed({
            val action =
                SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeFragment()
            view?.findNavController()?.navigate(action)
        }, 3000)

        return binding.root
    }
}