package com.perullheim.homework

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.perullheim.homework.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            imgBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            tvSignUp.setOnClickListener {
                val registerFragment = RegisterFragment()
                moveTo(registerFragment)
            }

            etEmail.addTextChangedListener {
                checkFields()
            }

            etPassword.addTextChangedListener {
                checkFields()
            }
        }
    }

    private fun moveTo(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .commit()
    }

    private fun checkFields() {
        with(binding) {
            val isEmailEmpty = etEmail.text.toString().isEmpty()
            val isPasswordEmpty = etPassword.text.toString().isEmpty()
            btnSignIn.isEnabled = !(isEmailEmpty && isPasswordEmpty)
        }
    }
}