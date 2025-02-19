package com.perullheim.homework.presentation.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.databinding.FragmentHomeBinding
import com.perullheim.homework.utils.BaseFragment
import com.perullheim.homework.utils.collect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun setup() {
        setupUI()
    }

    override fun listeners() {
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
        }
    }

    private fun setupUI() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        subscribeToViewStateUpdates(adapter)
    }

    private fun createAdapter(): UsersListAdapter {
        return UsersListAdapter()
    }

    private fun setupRecyclerView(usersAdapter: UsersListAdapter) {
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }
    }

    private fun subscribeToViewStateUpdates(adapter: UsersListAdapter) {
        collect(viewModel.pageData) {
            adapter.submitData(it)
        }
    }
}