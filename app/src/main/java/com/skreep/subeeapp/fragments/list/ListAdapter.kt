package com.skreep.subeeapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.skreep.subeeapp.R
import com.skreep.subeeapp.databinding.ItemRvBinding
import com.skreep.subeeapp.model.Subscription
import com.skreep.subeeapp.viewmodel.SubViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.item_rv.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var subList = emptyList<Subscription>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = subList[position]

        holder.itemView.name_tv.text = currentItem.nameSub
        holder.itemView.desc_tv.text = currentItem.descSub
        holder.itemView.price_tv.text = currentItem.priceSub

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return subList.size
    }

    fun setData(subscription: List<Subscription>) {
        this.subList = subscription
        notifyDataSetChanged()
    }


    }
