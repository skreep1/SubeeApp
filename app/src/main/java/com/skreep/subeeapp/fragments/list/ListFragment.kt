package com.skreep.subeeapp.fragments.list

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.skreep.subeeapp.R
import com.skreep.subeeapp.databinding.FragmentListBinding
import com.skreep.subeeapp.model.Subscription
import com.skreep.subeeapp.viewmodel.SubViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_list.view.button_add
import kotlinx.android.synthetic.main.item_bottom.*


class ListFragment : Fragment() {

    private lateinit var mSubViewModel: SubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        view.button_add.setOnClickListener {
            insertDataToDatabase()
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

    private fun insertDataToDatabase() {
        val name = name_et.text.toString()
        val desc = desc_et.text.toString()
        val price = price_et.text.toString()

        if (inputCheck(name, desc, price)) {

            //создание sub обьекта
            val sub = Subscription(0, name, desc, price)

            //добавление в БД
            mSubViewModel.addSub(sub)

            Toast.makeText(requireContext(), "Подписка добавлена", Toast.LENGTH_LONG).show()
            //кнопка назад
            //findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, desc: String, price: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(desc) && price.isEmpty())
    }


}




