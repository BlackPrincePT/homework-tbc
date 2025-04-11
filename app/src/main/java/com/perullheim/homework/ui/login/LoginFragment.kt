package com.perullheim.homework.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.perullheim.homework.databinding.FragmentLoginBinding
import com.perullheim.homework.helper.ViewBindingFragment
import com.perullheim.homework.helper.showSnackBar
import com.perullheim.homework.helper.validateFields

class LoginFragment : ViewBindingFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnLogin.setOnClickListener {
                if (!listOf(etEmail, etPassword).validateFields()) {
                    view.showSnackBar("Please fill out all fields!")
                    return@setOnClickListener
                }

                viewModel.login(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            }
        }

        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            view.showSnackBar(result)
        }
    }
}