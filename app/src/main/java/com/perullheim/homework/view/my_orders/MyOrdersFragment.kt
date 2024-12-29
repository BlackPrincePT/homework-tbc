package com.perullheim.homework.view.my_orders

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentMyOrdersBinding
import com.perullheim.homework.model.Order
import com.perullheim.homework.model.OrderStatus
import com.perullheim.homework.model.generateRandomOrders
import com.perullheim.homework.view.my_order_details.MyOrderDetailsFragment
import java.util.ArrayList

private const val MY_ORDERS_KEY = "myOrdersList"

class MyOrdersFragment : Fragment(), MyOrdersRecyclerViewAdapter.Listener {

    private var myOrders = generateRandomOrders(8)

    private val myOrdersAdapter = MyOrdersRecyclerViewAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvMyOrders.layoutManager = LinearLayoutManager(requireContext())
            rvMyOrders.adapter = myOrdersAdapter

            setupFilterListeners()
        }

        currentFilter = OrderStatus.PENDING

        myOrdersAdapter.submitList(myOrders.filter { it.status == currentFilter })
    }

    override fun detailsClicked(order: Order) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, MyOrderDetailsFragment.newInstance(order))
            .addToBackStack(null)
            .commit()
    }

    // =============== Selected Filter View Modifications =============== \\

    private var currentFilter: OrderStatus = OrderStatus.PENDING
        set(value) {
            resetFilterStyles()

            applySelectedFilterStyle(value)

            myOrdersAdapter.submitList(myOrders.filter { it.status == value })

            field = value
        }

    private fun resetFilterStyles() {
        with(binding) {
            resetStyle(tvPending)
            resetStyle(tvDelivered)
            resetStyle(tvCanceled)
        }
    }

    private fun resetStyle(textView: AppCompatTextView) {
        textView.apply {
            background = null
            setTextColor(context.getColor(R.color.black))
        }
    }

    private fun applySelectedFilterStyle(orderStatus: OrderStatus) {
        val selectedFilter = when (orderStatus) {
            OrderStatus.PENDING -> binding.tvPending
            OrderStatus.DELIVERED -> binding.tvDelivered
            OrderStatus.CANCELED -> binding.tvCanceled
        }

        selectedFilter.apply {
            background = context.getDrawable(R.drawable.select_order_status_background)
            setTextColor(context.getColor(R.color.white))
        }
    }

    private fun setupFilterListeners() {
        with(binding) {
            tvPending.setOnClickListener {
                currentFilter = OrderStatus.PENDING
            }

            tvDelivered.setOnClickListener {
                currentFilter = OrderStatus.DELIVERED
            }

            tvCanceled.setOnClickListener {
                currentFilter = OrderStatus.CANCELED
            }
        }
    }

    // =============== Binding =============== \\

    private var _binding: FragmentMyOrdersBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyOrdersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    // =============== Save Current State =============== \\

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.getParcelableArrayList(MY_ORDERS_KEY, Order::class.java)?.let {
            myOrders = it.toList()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(MY_ORDERS_KEY, ArrayList(myOrders))
    }
}