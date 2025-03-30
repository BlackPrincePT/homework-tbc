package com.perullheim.homework.presentation.accounts

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.databinding.FragmentAccountsBinding
import com.perullheim.homework.domain.core.Error
import com.perullheim.homework.presentation.core.base.BaseBottomSheetFragment
import com.perullheim.homework.presentation.core.error.toStringResId
import com.perullheim.homework.presentation.core.extension.collectLatest
import com.perullheim.homework.presentation.core.extension.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountsFragment :
    BaseBottomSheetFragment<FragmentAccountsBinding>(FragmentAccountsBinding::inflate) {

    private val viewModel: AccountsViewModel by viewModels()

    override fun setup() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        subscribeToViewStateUpdates(adapter)
        subscribeToViewEffectUpdates()
    }

    private fun createAdapter(): AccountsAdapter {
        return AccountsAdapter(onEvent = viewModel::onEvent)
    }

    private fun setupRecyclerView(accountsAdapter: AccountsAdapter) {
        binding.rvAccounts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = accountsAdapter
        }
    }

    private fun subscribeToViewStateUpdates(adapter: AccountsAdapter) {
        collectLatest(viewModel.uiState) { state ->
            adapter.submitList(state.accounts)
        }
    }

    private fun subscribeToViewEffectUpdates() {
        collectLatest(viewModel.uiEffect) { effect ->
            when (effect) {
                is AccountsUiEffect.NavigateBack -> navigateBack(effect.selectedAccountId)
                is AccountsUiEffect.Failure -> showError(effect.error)
            }
        }
    }

    private fun navigateBack(selectedAccountId: Int) {
        println(selectedAccountId)
    }

    private fun showError(error: Error) {
        val errorMsg = getString(error.toStringResId())
        binding.root.showSnackBar(errorMsg)
    }
}