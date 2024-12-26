package com.perullheim.homework.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.R
import com.perullheim.homework.data.Address
import com.perullheim.homework.databinding.FragmentAddressListBinding
import com.perullheim.homework.detail.AddressDetailFragment

class AddressListFragment : Fragment(), AddressListAdapter.Listener, AddressDetailFragment.Listener {

    private val addressList = mutableListOf(
        Address(label = "Home", city = "Tbilisi", street = "Orange Street"),
        Address(label = "Work", city = "Tbilisi", street = "Apple Street"),
        Address(label = "School", city = "Tbilisi", street = "Banana Street"),
        Address(label = "University", city = "Tbilisi", street = "Pineapple Street"),
        Address(label = "Hospital", city = "Tbilisi", street = "Kiwi Street")
    )

    private val addressListAdapter by lazy {
        AddressListAdapter(this)
    }

    override var selectedAddress: Address? = null
        set(value) {
            field = value

            for (index in 0 until addressListAdapter.itemCount) {
                (binding.rvAddressList.findViewHolderForLayoutPosition(index) as? AddressListAdapter.AddressViewHolder)?.unselect()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvAddressList.layoutManager = LinearLayoutManager(requireContext())
            rvAddressList.adapter = addressListAdapter
        }

        addressListAdapter.submitList(addressList)

        binding.btnAddNewAddress.setOnClickListener {
            showAddressDetail(null)
        }
    }

    // =============== Interface Functions =============== \\

    override fun editAddressClicked(address: Address) {
        showAddressDetail(address)
    }

    override fun confirmClicked(address: Address) {
        addressList.removeAll { it.id == address.id }
        addressList.add(0, address)
        addressListAdapter.submitList(addressList)
    }

    // =============== Show Address Detail =============== \\

    private fun showAddressDetail(address: Address?) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, AddressDetailFragment(address, this))
            .addToBackStack(null)
            .commit()
    }

    // =============== Binding =============== \\

    private var _binding: FragmentAddressListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}