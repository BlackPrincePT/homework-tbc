package com.perullheim.homework.view.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.perullheim.homework.R
import com.perullheim.homework.model.GridType
import com.perullheim.homework.databinding.FragmentGameBinding
import com.perullheim.homework.model.GameSlot
import com.perullheim.homework.model.GameSlotValue
import com.perullheim.homework.view.choose.ChooseGridTypeFragment

private const val GRID_TYPE = "GRID_TYPE"

class GameFragment : Fragment(), GameAdapter.Listener {

    private var gridTypeValue: Int = GridType.THREE_BY_THREE.gridValue

    private val adapter: GameAdapter = GameAdapter(this)

    private val slots: List<GameSlot> by lazy {
        List(gridTypeValue * gridTypeValue) {
            GameSlot(
                coordinates = Pair(
                    it / gridTypeValue,
                    it % gridTypeValue
                )
            )
        }
    }

    override fun onSlotClick(pos: Int) {
        slots[pos].value =
            if (slots.filter { it.value != null }.size % 2 == 0) GameSlotValue.X else GameSlotValue.O

        if (checkGameStatus(pos))
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, ChooseGridTypeFragment())
                .commit()

    }

    private fun checkGameStatus(pos: Int): Boolean {
        if (slots.none { it.value == null })
            return true
        else {
            val (x, y) = slots[pos].coordinates

            val filteredByX = slots.filter { it.coordinates.first == x }
            val filteredByY = slots.filter { it.coordinates.second == y }

            if (filteredByX.all { it.value != null && it.value == filteredByX.first().value })
                return true
            if (filteredByY.all { it.value != null && it.value == filteredByY.first().value })
                return true


            if (x == y) {
                val diagonal1 = slots.filter { it.coordinates.first == it.coordinates.second }

                if (diagonal1.all { it.value != null && it.value == diagonal1.first().value })
                    return true
            } else if (x + y == slots.size / gridTypeValue - 1) {
                val diagonal2 =
                    slots.filter { it.coordinates.first + it.coordinates.second == slots.size / gridTypeValue - 1 }

                if (diagonal2.all { it.value != null && it.value == diagonal2.first().value })
                    return true
            }
        }

        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gridTypeValue = it.getInt(GRID_TYPE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvGame.layoutManager = GridLayoutManager(requireContext(), gridTypeValue)
        binding.rvGame.adapter = adapter

        adapter.submitList(slots)
    }

    companion object {
        @JvmStatic
        fun newInstance(gridType: GridType) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putInt(GRID_TYPE, gridType.gridValue)
                }
            }
    }

    // =============== Binding =============== \\

    private var _binding: FragmentGameBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}