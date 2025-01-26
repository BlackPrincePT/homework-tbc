package com.perullheim.homework.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.databinding.FragmentHomeBinding
import com.perullheim.homework.helper.ViewBindingFragment
import com.perullheim.homework.helper.viewLifecycleScope
import com.perullheim.homework.model.service.home.UserDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : ViewBindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private val usersAdapter = UsersListAdapter()

    private val currentPage = MutableStateFlow(1)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvUsers.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = usersAdapter
            }

            btnProfile.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
            }

            btnListBack.setOnClickListener {
                if (currentPage.value > 1)
                    currentPage.value -= 1
            }

            btnListForward.setOnClickListener {
                if (currentPage.value < (viewModel.pageData.value?.totalPages ?: 0))
                    currentPage.value += 1
            }
        }

        viewLifecycleScope {
            viewModel.pageData.collect {
                usersAdapter.submitList(it?.data)
            }
        }

        viewLifecycleScope {
            currentPage.collect {
                binding.tvCurrentPage.text = it.toString()
                viewModel.getUsers(it)
            }
        }
    }
}