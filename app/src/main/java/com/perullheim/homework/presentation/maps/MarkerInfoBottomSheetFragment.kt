package com.perullheim.homework.presentation.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.perullheim.homework.databinding.FragmentMarkerInfoBottomSheetBinding

class MarkerInfoBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentMarkerInfoBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val args: MarkerInfoBottomSheetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarkerInfoBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvAddressInfo.text = args.address.info
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}