package com.perullheim.homework.presentation.profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.perullheim.homework.databinding.FragmentProfileBinding
import com.perullheim.homework.utils.BaseFragment
import com.perullheim.homework.utils.collectLatest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun collectors() {
        collectLatest(viewModel.shouldLogout) { shouldLogout ->
            if (shouldLogout)
                findNavController().navigate(ProfileFragmentDirections.actionHomeFragmentToLoginFragment())
        }
    }

    override fun listeners() {
        binding.btnLogOut.setOnClickListener {
            viewModel.logout()
        }
    }
}