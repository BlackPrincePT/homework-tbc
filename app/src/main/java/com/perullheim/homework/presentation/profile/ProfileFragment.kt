package com.perullheim.homework.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.databinding.FragmentProfileBinding
import com.perullheim.homework.utils.ViewBindingFragment
import com.perullheim.homework.utils.viewLifecycleScope
import kotlinx.coroutines.launch

class ProfileFragment : ViewBindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels { ProfileViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleScope {
            viewModel.currentUserToken.collect { token ->
                if (token == null)
                    findNavController().navigate(ProfileFragmentDirections.actionHomeFragmentToLoginFragment())
            }
        }

        binding.btnLogOut.setOnClickListener {
            lifecycleScope.launch {
                viewModel.logout()
            }
        }
    }
}