package com.perullheim.homework.view.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.perullheim.homework.databinding.FragmentWriteFeedbackBinding
import com.perullheim.homework.model.Feedback
import com.perullheim.homework.model.Stars

class WriteFeedbackFragment(private val completion: (feedback: Feedback) -> Unit) : BottomSheetDialogFragment() {

    // ============ State ============ \\

    private var _binding: FragmentWriteFeedbackBinding? = null
    private val binding
        get() = _binding!!

    // ============ Lifecycle Callbacks ============ \\

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteFeedbackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnCancel.setOnClickListener {
                dismiss()
            }

            btnSubmit.setOnClickListener {
                completion.invoke(makeFeedback())
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    // ============ Functions ============ \\

    private fun makeFeedback(): Feedback {
        return Feedback(
            rating = Stars.entries[binding.rbStars.rating.toInt() - 1],
            review = binding.etReview.text.toString()
        )
    }
}