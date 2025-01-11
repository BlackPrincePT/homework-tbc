package com.perullheim.homework.view.my_orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.perullheim.homework.databinding.FragmentMyOrdersBinding
import com.perullheim.homework.model.DeliveryStatus
import com.perullheim.homework.view.my_orders.adapter.MyOrdersViewPagerAdapter

class MyOrdersFragment : Fragment() {

    // ============ State ============ \\

    private var _binding: FragmentMyOrdersBinding? = null
    private val binding
        get() = _binding!!

    // ============ Lifecycle Callbacks ============ \\

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    // ============ Functions ============ \\

    private fun setupTabLayout() {
        with(binding) {
            viewPager.adapter = MyOrdersViewPagerAdapter(this@MyOrdersFragment)
            TabLayoutMediator(tlFilters, viewPager) { tab, position ->
                tab.text = DeliveryStatus.entries[position].filterDisplayName
            }.attach()
        }
    }
}