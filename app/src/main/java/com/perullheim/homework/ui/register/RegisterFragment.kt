package com.perullheim.homework.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.perullheim.homework.databinding.FragmentRegisterBinding
import com.perullheim.homework.helper.ViewBindingFragment
import com.perullheim.homework.helper.showSnackBar
import com.perullheim.homework.helper.validateFields

class RegisterFragment : ViewBindingFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnRegister.setOnClickListener {
                if (!listOf(etEmail, etUsername, etPassword).validateFields()) {
                    view.showSnackBar("Please fill out all fields!")
                    return@setOnClickListener
                }

                viewModel.register(
                    email = etEmail.text.toString(),
                    username = etUsername.text.toString(),
                    password = etPassword.text.toString(),
                )
            }
        }

        viewModel.registerResult.observe(viewLifecycleOwner) { result ->
            view.showSnackBar(result)
        }
    }
}