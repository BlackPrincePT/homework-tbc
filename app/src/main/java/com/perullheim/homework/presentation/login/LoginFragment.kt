package com.perullheim.homework.presentation.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentLoginBinding
import com.perullheim.homework.utils.ViewBindingFragment
import com.perullheim.homework.utils.showSnackBar
import com.perullheim.homework.utils.validateFields
import com.perullheim.homework.utils.viewLifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class LoginFragment : ViewBindingFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    private var isLoading: Boolean by Delegates.observable(false) { _, _, _ ->
        binding.btnLogin.text = getString(if (isLoading) R.string.loading else R.string.register)
    }

    private var autoFillEmail: String? = null
    private var autoFillPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(REGISTER_REQUEST_KEY) { _, bundle ->
            autoFillEmail = bundle.getString(EMAIL_KEY)
            autoFillPassword = bundle.getString(PASSWORD_KEY)

            if (view != null)
                autoFillCredentials()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleScope {
            viewModel.currentUserToken.collect { token ->
                if (token != null)
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        }

        binding.tvToRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        setupOnLoginListener()
        setupButtonStateManager()
        setupErrorMessageListener()
    }

    private fun setupOnLoginListener() {
        with(binding) {
            btnLogin.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                val shouldRemember = cbRememberMe.isChecked

                viewLifecycleScope {
                    isLoading = true
                    viewModel.login(email, password, shouldRemember)
                    isLoading = false
                }
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

    private fun autoFillCredentials() {
        with(binding) {
            etEmail.setText(autoFillEmail)
            etPassword.setText(autoFillPassword)
        }
    }

    private fun setupButtonStateManager() {
        with(binding) {
            etEmail.addTextChangedListener {
                btnLogin.isEnabled = listOf(etEmail, etPassword).validateFields()
            }

            etPassword.addTextChangedListener {
                btnLogin.isEnabled = listOf(etEmail, etPassword).validateFields()
            }
        }
    }

    companion object {
        const val REGISTER_REQUEST_KEY = "register"
        const val EMAIL_KEY = "email"
        const val PASSWORD_KEY = "password"
    }
}