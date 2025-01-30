package com.perullheim.homework.ui.user_info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.perullheim.homework.databinding.FragmentUserInfoBinding
import com.perullheim.homework.helper.ViewBindingFragment
import com.perullheim.homework.helper.viewLifecycleScope
import kotlinx.coroutines.flow.collectLatest

class UserInfoFragment :
    ViewBindingFragment<FragmentUserInfoBinding>(FragmentUserInfoBinding::inflate) {

    private val viewModel: UserInfoViewModel by viewModels { UserInfoViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleScope {
            viewModel.savedUser.collectLatest {
                binding.tvUserInfo.text = it?.info
            }
        }

    }
}