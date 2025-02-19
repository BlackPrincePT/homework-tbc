package com.perullheim.homework.presentation.auth.login

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentLoginBinding
import com.perullheim.homework.presentation.Event
import com.perullheim.homework.presentation.auth.AuthState
import com.perullheim.homework.utils.BaseFragment
import com.perullheim.homework.utils.collect
import com.perullheim.homework.utils.showSnackBar
import com.perullheim.homework.utils.validateFields
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun collectors() {
        collect(viewModel.state) {
            updateScreenState(it)
        }
    }

    override fun listeners() {
        setupButtonStateManager()
        setupOnLoginListener()
        autoFillCredentialsListener()
        navigateToRegisterListener()
    }

    // ========= State Management ========= \\

    private fun updateScreenState(state: AuthState) {
        val buttonTextRes = if (state.loading) R.string.loading else R.string.login
        binding.btnLogin.text = getString(buttonTextRes)

        disableAllActions(state.loading)

        handleSuccess(state.success)
        handleFailure(state.failure)
    }

    private fun handleSuccess(isSuccessful: Boolean) {
        if (isSuccessful)
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
    }

    private fun handleFailure(failure: Event<String>?) {
        val message = failure?.getContentIfNotHandled() ?: return

        view?.showSnackBar(message)
    }

    // ========= Setup Listeners ========= \\

    private fun setupOnLoginListener() {
        with(binding) {
            btnLogin.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                val shouldRemember = cbRememberMe.isChecked

                viewModel.login(email, password, shouldRemember)
            }
        }
    }

    private fun navigateToRegisterListener() {
        binding.tvToRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
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

    private fun autoFillCredentialsListener() {
        setFragmentResultListener(REGISTER_REQUEST_KEY) { _, bundle ->
            with(binding) {
                etEmail.setText(bundle.getString(EMAIL_KEY))
                etPassword.setText(bundle.getString(PASSWORD_KEY))
            }
        }
    }

    // ========= Helper ========= \\

    private fun disableAllActions(isLoading: Boolean) {
        with(binding) {
            etEmail.isEnabled = !isLoading
            etPassword.isEnabled = !isLoading
            cbRememberMe.isEnabled = !isLoading
            btnLogin.isEnabled = !isLoading
        }
    }

    companion object {
        const val REGISTER_REQUEST_KEY = "register"
        const val EMAIL_KEY = "email"
        const val PASSWORD_KEY = "password"
    }
}