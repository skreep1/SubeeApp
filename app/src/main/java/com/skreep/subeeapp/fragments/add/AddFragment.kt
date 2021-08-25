package com.skreep.subeeapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.skreep.subeeapp.R
import com.skreep.subeeapp.databinding.FragmentAddBinding
import com.skreep.subeeapp.viewmodel.SubViewModel
import com.skreep.subeeapp.model.Subscription
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mSubViewModel: SubViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mSubViewModel = ViewModelProvider(this).get(SubViewModel::class.java)

        view.button_add.setOnClickListener {
            insertDataToDatabase()
        }

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
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, desc: String, price: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(desc) && price.isEmpty())
    }

}

