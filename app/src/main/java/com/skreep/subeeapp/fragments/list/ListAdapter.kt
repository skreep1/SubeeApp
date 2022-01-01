package com.skreep.subeeapp.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.skreep.subeeapp.databinding.ItemRvBinding
import com.skreep.subeeapp.model.Subscription


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var subList = ArrayList<Subscription>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRvBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = subList[position]
        with(holder) {
            with(binding) {
                nameTv.text = currentItem.nameSub
                descTv.text = currentItem.descSub
                priceTv.text = currentItem.priceSub.toString()
                rowLayout.setOnClickListener {
                    val action =
                        ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                    holder.itemView.findNavController().navigate(action)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return subList.size
    }

    fun setData(subscription: List<Subscription>) {
        this.subList = subscription as ArrayList<Subscription>
        notifyDataSetChanged()
    }

//    fun total(subList: List<Subscription>): Double {
//        var total = 0.0
//        for (subscription in subList) {
//            total += subscription.priceSub.toString().toDouble()
//        }
//        return total
//        notifyDataSetChanged()
//    }



    class MyViewHolder(var binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}







