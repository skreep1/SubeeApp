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
import com.skreep.subeeapp.model.Subscription
import com.skreep.subeeapp.viewmodel.SubViewModel


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var mSubViewModel: SubViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAddBinding.inflate(inflater, container,false)
        val view = binding.root

        mSubViewModel = ViewModelProvider(this).get(SubViewModel::class.java)

        binding.buttonAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = binding.nameEt.text.toString()
        val desc = binding.descEt.text.toString()
        val price = binding.priceEt.text.toString()

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
