package com.perullheim.homework.view.my_orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.databinding.FragmentMyOrdersRecyclerViewBinding
import com.perullheim.homework.model.DeliveryStatus
import com.perullheim.homework.model.Order
import com.perullheim.homework.view.feedback.WriteFeedbackFragment
import com.perullheim.homework.view.my_orders.adapter.MyOrdersRecyclerViewAdapter

private const val ARG_FILTER = "filter"

class MyOrdersRecyclerViewFragment private constructor() : Fragment() {

    // ============ State ============ \\

    private val ordersAdapter = MyOrdersRecyclerViewAdapter { pos ->
        WriteFeedbackFragment { review ->
            Order.exampleOrders[pos].copy().apply {
                feedback = review
            }.let {
                Order.exampleOrders[pos] = it
            }

            submitList()
        }.let {
            it.show(parentFragmentManager, it.tag)
        }
    }

    private lateinit var filterType: DeliveryStatus

    private var _binding: FragmentMyOrdersRecyclerViewBinding? = null
    private val binding
        get() = _binding!!

    // ============ Lifecycle Callbacks ============ \\

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            filterType = DeliveryStatus.valueOf(it.getString(ARG_FILTER)!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyOrdersRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvMyOrders.layoutManager = LinearLayoutManager(requireContext())
            rvMyOrders.adapter = ordersAdapter
        }

        submitList()
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    // ============ Functions ============ \\

    private fun submitList() {
        val newList = Order.exampleOrders.toList()
        ordersAdapter.submitList(newList.filter { it.deliveryStatus == filterType })
    }

    companion object {
        @JvmStatic
        fun newInstance(filter: DeliveryStatus) =
            MyOrdersRecyclerViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_FILTER, filter.name)
                }
            }
    }
}