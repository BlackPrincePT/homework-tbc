package com.perullheim.homework.view.my_orders.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.perullheim.homework.model.DeliveryStatus
import com.perullheim.homework.view.my_orders.MyOrdersRecyclerViewFragment

class MyOrdersViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return DeliveryStatus.entries.size
    }

    override fun createFragment(position: Int): Fragment {
        return DeliveryStatus.entries[position].let {
            MyOrdersRecyclerViewFragment.newInstance(it)
        }
    }
}