package com.perullheim.homework.view.choose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.perullheim.homework.model.GridType
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentChooseGridTypeBinding
import com.perullheim.homework.view.game.GameFragment

class ChooseGridTypeFragment : Fragment() {

    private var selectedGridType = GridType.THREE_BY_THREE
        set(value) {
            field = value

            resetGridTypeStyles()

            applySelectedGridTypeStyle(value)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupGridTypeListeners()

        binding.btnStartGame.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, GameFragment.newInstance(selectedGridType))
                .commit()
        }
    }

    private fun resetGridTypeStyles() {
        with(binding) {
            resetStyle(tv3x3)
            resetStyle(tv4x4)
            resetStyle(tv5x5)
        }
    }

    private fun resetStyle(textView: AppCompatTextView, color: Int = R.color.red) {
        ContextCompat.getColor(requireContext(), color).let {
            textView.setBackgroundColor(it)
        }
    }

    private fun applySelectedGridTypeStyle(gridType: GridType) {
        val selectedFilter = when (gridType) {
            GridType.THREE_BY_THREE -> binding.tv3x3
            GridType.FOUR_BY_FOUR -> binding.tv4x4
            GridType.FIVE_BY_FIVE -> binding.tv5x5
        }

        resetStyle(selectedFilter, R.color.green)
    }

    private fun setupGridTypeListeners() {
        with(binding) {
            tv3x3.setOnClickListener {
                selectedGridType = GridType.THREE_BY_THREE
            }

            tv4x4.setOnClickListener {
                selectedGridType = GridType.FOUR_BY_FOUR
            }

            tv5x5.setOnClickListener {
                selectedGridType = GridType.FIVE_BY_FIVE
            }
        }
    }

    // =============== Binding =============== \\

    private var _binding: FragmentChooseGridTypeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseGridTypeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}