package com.perullheim.homework.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentHomeBinding
import com.perullheim.homework.helper.ViewBindingFragment
import com.perullheim.homework.helper.viewLifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine

class HomeFragment : ViewBindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels { HomeViewModel.Factory }
    private val usersAdapter = UserListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }

        val networkStatusRes =
            if (viewModel.isConnected) R.string.you_are_online else R.string.you_are_offline
        binding.tvConnectionStatus.text = getString(networkStatusRes)


        viewLifecycleScope {
            combine(viewModel.users, viewModel.isLoading) { users, isLoading ->
                users to isLoading
            }.collectLatest { (users, isLoading) ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                usersAdapter.submitList(users)
            }
        }
    }

}