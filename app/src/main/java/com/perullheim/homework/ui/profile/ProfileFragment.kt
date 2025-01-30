package com.perullheim.homework.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentProfileBinding
import com.perullheim.homework.helper.ViewBindingFragment
import com.perullheim.homework.helper.showSnackBar
import com.perullheim.homework.model.User

class ProfileFragment :
    ViewBindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels { ProfileViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSave.setOnClickListener {
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                val email = etEmail.text.toString()

                if (listOf(firstName, lastName, email).any { it.isEmpty() }) {
                    val errorMsg = getString(R.string.please_fill_out_all_fields)
                    view.showSnackBar(errorMsg)
                    return@setOnClickListener
                }

                val user = User(firstName, lastName, email)

                viewModel.updateUser(user)
                emptyFields()
            }

            btnRead.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToUserInfoFragment())
            }
        }
    }

    private fun emptyFields() {
        with(binding) {
            etFirstName.text = null
            etLastName.text = null
            etEmail.text = null
        }
    }
}