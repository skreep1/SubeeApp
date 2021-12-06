package com.skreep.subeeapp.fragments.list

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skreep.subeeapp.R
import com.skreep.subeeapp.databinding.FragmentListBinding
import com.skreep.subeeapp.viewmodel.SubViewModel
import jahirfiquitiva.libs.textdrawable.TextDrawable
import jahirfiquitiva.libs.textdrawable.TextDrawable.Companion.buildRect
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.item_rv.view.*


class ListFragment : Fragment() {

    private lateinit var mSubViewModel: SubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        view.floating.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }



        //добавить меню
        setHasOptionsMenu(true)

        //recyclerView
        val adapter = ListAdapter()
        val recyclerview = view.recycler
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        //SubViewModel
        mSubViewModel = ViewModelProvider(this).get(SubViewModel::class.java)
        mSubViewModel.readAllData.observe(viewLifecycleOwner, Observer { sub ->
            adapter.setData(sub)
        })

        return view
    }


}




