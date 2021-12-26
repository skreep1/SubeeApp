package com.skreep.subeeapp.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skreep.subeeapp.R
import com.skreep.subeeapp.databinding.FragmentAddBinding
import com.skreep.subeeapp.databinding.FragmentListBinding
import com.skreep.subeeapp.viewmodel.SubViewModel




class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mSubViewModel: SubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container,false)
        val view = binding.root

        binding.floating.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        //добавить меню
        setHasOptionsMenu(true)

        //recyclerView
        val adapter = ListAdapter()
        val recyclerview = binding.recycler
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




