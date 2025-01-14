package com.perullheim.homework.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentDeleteCardDialogBinding
import com.perullheim.homework.view.payment.PaymentViewModel

class DeleteCardDialogFragment : BottomSheetDialogFragment() {

    private val args: DeleteCardDialogFragmentArgs by navArgs()
    private val viewModel: PaymentViewModel by viewModels(
        { findNavController().getBackStackEntry(R.id.nav_graph) }
    )

    private var _binding: FragmentDeleteCardDialogBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteCardDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnYes.setOnClickListener {
                viewModel.removeCard(args.cardToDelete)
                dismiss()
            }

            btnNo.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}