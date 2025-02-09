package com.perullheim.homework.presentation.register

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentRegisterBinding
import com.perullheim.homework.utils.ViewBindingFragment
import com.perullheim.homework.utils.showSnackBar
import com.perullheim.homework.utils.validateFields
import com.perullheim.homework.utils.viewLifecycleScope
import com.perullheim.homework.presentation.login.LoginFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class RegisterFragment :
    ViewBindingFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    private var isLoading: Boolean by Delegates.observable(false) { _, _, _ ->
        binding.btnRegister.text = getString(if (isLoading) R.string.loading else R.string.register)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvToLogin.setOnClickListener {
            findNavController().navigateUp()
        }

        setupOnRegisterListener()
        setupButtonStateManager()
        setupErrorMessageListener()
    }

    private fun setupOnRegisterListener() {
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            isLoading = true

            lifecycleScope.launch(Dispatchers.IO) {
                val isSuccessful = viewModel.register(email, password)

                if (isSuccessful)
                    with(LoginFragment) {
                        val credentialsBundle = bundleOf(
                            EMAIL_KEY to email,
                            PASSWORD_KEY to password
                        )

                        setFragmentResult(REGISTER_REQUEST_KEY, credentialsBundle)

                        withContext(Dispatchers.Main) {
                            findNavController().navigateUp()
                        }
                    }

                isLoading = false
            }
        }
    }

    private fun setupErrorMessageListener() {
        viewLifecycleScope {
            viewModel.errorMessage.collect { message ->
                message?.let { view?.showSnackBar(it) }

            }
        }
    }

    private fun setupButtonStateManager() {
        with(binding) {
            etEmail.addTextChangedListener {
                btnRegister.isEnabled = listOf(etEmail, etPassword).validateFields()
            }

            etPassword.addTextChangedListener {
                btnRegister.isEnabled = listOf(etEmail, etPassword).validateFields()
            }
        }
    }
}