package com.perullheim.homework.view.my_order_details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.perullheim.homework.databinding.FragmentMyOrderDetailsBinding
import com.perullheim.homework.model.Order
import com.perullheim.homework.model.OrderStatus

private const val ARG_PARAM1 = "param1"

class MyOrderDetailsFragment : Fragment() {

    private var order: Order? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            order = it.getParcelable(ARG_PARAM1, Order::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (order?.status == OrderStatus.PENDING)
            setupListeners()
        else
            hideStatusButtons()
    }

    private fun setupListeners() {
        with(binding) {
            btnCancelOrder.setOnClickListener {
                changeStatus(OrderStatus.CANCELED)
            }

            btnConfirmOrder.setOnClickListener {
                changeStatus(OrderStatus.DELIVERED)
            }
        }
    }

    private fun changeStatus(status: OrderStatus) {
        order?.status = status

        parentFragmentManager.popBackStack()
    }

    private fun hideStatusButtons() {
        with(binding) {
            btnCancelOrder.visibility = View.GONE
            btnConfirmOrder.visibility = View.GONE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(order: Order) =
            MyOrderDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, order)
                }
            }
    }

    // =============== Binding =============== \\

    private var _binding: FragmentMyOrderDetailsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyOrderDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}