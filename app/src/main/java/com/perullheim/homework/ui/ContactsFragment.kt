package com.perullheim.homework.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.databinding.FragmentContactsBinding
import com.perullheim.homework.helper.ViewBindingFragment

class ContactsFragment : ViewBindingFragment<FragmentContactsBinding>(FragmentContactsBinding::inflate) {

    private val viewModel: ContactsViewModel by viewModels()
    private val contactsAdapter = ContactsListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvContacts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = contactsAdapter
        }

        contactsAdapter.submitList(viewModel.chats)

        binding.etSearchBar.addTextChangedListener {
            val text = binding.etSearchBar.text.toString()
            viewModel.search(text).let {
                contactsAdapter.submitList(it)
            }
        }
    }
}