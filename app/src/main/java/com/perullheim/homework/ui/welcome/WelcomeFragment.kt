package com.perullheim.homework.ui.welcome

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.perullheim.homework.databinding.FragmentWelcomeBinding
import com.perullheim.homework.helper.ViewBindingFragment

class WelcomeFragment : ViewBindingFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment().let { action ->
                btnLogin.setOnClickListener {
                    Navigation.findNavController(view).navigate(action)
                }
            }

            WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment().let { action ->
                btnRegister.setOnClickListener {
                    Navigation.findNavController(view).navigate(action)
                }
            }
        }
    }
}