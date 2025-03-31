package com.perullheim.homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.perullheim.homework.databinding.ActivityMainBinding
import com.perullheim.homework.databinding.FragmentWelcomeBinding
import kotlin.math.log

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

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
        binding.btnSignIn.setOnClickListener {
            val loginFragment = LoginFragment()
            moveTo(loginFragment)
        }

        binding.tvSignUp.setOnClickListener {
            val registerFragment = RegisterFragment()
            moveTo(registerFragment)
        }
    }

    private fun moveTo(fragment: Fragment) {

        val name = fragment::class.java.simpleName

        parentFragmentManager.beginTransaction()
            .replace(R.id.main, fragment, name)
            .addToBackStack(name)
            .commit()
    }
}