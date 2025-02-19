package com.perullheim.homework.presentation.auth.register

import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentRegisterBinding
import com.perullheim.homework.presentation.Event
import com.perullheim.homework.presentation.auth.AuthState
import com.perullheim.homework.presentation.auth.login.LoginFragment.Companion.EMAIL_KEY
import com.perullheim.homework.presentation.auth.login.LoginFragment.Companion.PASSWORD_KEY
import com.perullheim.homework.presentation.auth.login.LoginFragment.Companion.REGISTER_REQUEST_KEY
import com.perullheim.homework.utils.BaseFragment
import com.perullheim.homework.utils.collect
import com.perullheim.homework.utils.showSnackBar
import com.perullheim.homework.utils.validateFields
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun collectors() {
        collect(viewModel.state) {
            updateScreenState(it)
        }
    }

    override fun listeners() {
        setupOnRegisterListener()
        setupButtonStateManager()
    }

    // ========= State Management ========= \\

    private fun updateScreenState(state: AuthState) {
        val buttonTextRes = if (state.loading) R.string.loading else R.string.register
        binding.btnRegister.text = getString(buttonTextRes)

        disableAllActions(state.loading)

        handleSuccess(state.success)
        handleFailure(state.failure)
    }

    private fun handleSuccess(isSuccessful: Boolean) {
        if (isSuccessful) {
            val credentialsBundle = bundleOf(
                EMAIL_KEY to binding.etEmail.text.toString(),
                PASSWORD_KEY to binding.etPassword.text.toString()
            )

            setFragmentResult(REGISTER_REQUEST_KEY, credentialsBundle)

            findNavController().navigateUp()
        }
    }

    private fun handleFailure(failure: Event<String>?) {
        val message = failure?.getContentIfNotHandled() ?: return

        view?.showSnackBar(message)
    }

    // ========= Setup Listeners ========= \\

    private fun setupOnRegisterListener() {
        with(binding) {
            btnRegister.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                viewModel.register(email, password)
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

    // ========= Helper ========= \\

    private fun disableAllActions(isLoading: Boolean) {
        with(binding) {
            etEmail.isEnabled = !isLoading
            etPassword.isEnabled = !isLoading
            btnRegister.isEnabled = !isLoading
        }
    }
}