package com.perullheim.homework.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.perullheim.homework.data.Address
import com.perullheim.homework.databinding.FragmentAddressDetailBinding
import java.util.UUID

class AddressDetailFragment(private val address: Address?, private val listener: Listener) :
    Fragment() {

    interface Listener {
        fun confirmClicked(address: Address)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        address?.let {
            with(binding) {
                etLabel.hint = it.label
                etCity.hint = it.city
                etStreet.hint = it.street
            }
        }

        binding.btnConfirm.setOnClickListener {
            listener.confirmClicked(getAddress())
            parentFragmentManager.popBackStack()
        }
    }

    private fun getAddress(): Address {
        with(binding) {
            return Address(
                id = address?.id ?: UUID.randomUUID(),
                label = etLabel.text.toString().ifBlank { address?.label ?: "New Address" },
                street = etStreet.text.toString().ifBlank { address?.street ?: "Not Provided" },
                city = etCity.text.toString().ifBlank { address?.city ?: "Not Provided" }
            )
        }
    }

    // =============== Binding =============== \\

    private var _binding: FragmentAddressDetailBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}