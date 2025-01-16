package com.perullheim.homework.view.form

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentFormBinding
import com.perullheim.homework.helper.ViewBindingFragment
import com.perullheim.homework.helper.showSnackBar

class FormFragment : ViewBindingFragment<FragmentFormBinding>(FragmentFormBinding::inflate) {

    private val formAdapter = FormAdapter()
    private val viewModel: FormViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvForm.layoutManager = LinearLayoutManager(requireContext())
        binding.rvForm.adapter = formAdapter

        formAdapter.submitList(viewModel.fields.toList())

        binding.btnRegister.setOnClickListener {
            val newData = formAdapter.onRegister()
            if (newData == null) {
                binding.root.showSnackBar(getString(R.string.please_fill_out_all_fields))
                return@setOnClickListener
            }

            viewModel.savedData.putAll(newData)
        }
    }
}