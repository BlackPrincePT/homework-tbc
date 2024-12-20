package com.perullheim.homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.perullheim.homework.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding
        get() = _binding!!

    // ========= Lifecycle Callbacks ========= \\

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    // ========= Functions ========= \\

    private fun setupListeners() {
        with(binding) {
            btnBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            btnAddUser.setOnClickListener {
                addUser()
            }
        }
    }


    private fun addUser() {
        with(binding) {
            val id = etId.text.toString()
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val birthday = etBirthday.text.toString()
            val address = etAddress.text.toString()
            val email = etEmail.text.toString()
            val desc = etDesc.text.toString().ifEmpty { null }

            val fields = listOf(id, firstName, lastName, birthday, address, email)

            if (!checkForEmptyFields(fields)) {
                val errorMsg = "Please fill out all fields"
                root.showSnackBar(errorMsg)
                return
            }

            if (UserManager.searchForUser(id) != null) {
                val errorMsg = "User with such id already exists"
                root.showSnackBar(errorMsg)
                return
            }

            val newUser = User(
                id = id.toInt(),
                firstName = firstName,
                lastName = lastName,
                birthday = birthday,
                address = address,
                email = email,
                desc = desc
            )

            UserManager.addNewUser(newUser)

            parentFragmentManager.popBackStack()
        }
    }

    private fun checkForEmptyFields(fields: List<String>): Boolean {
        return fields.none { it.isEmpty() }
    }
}


