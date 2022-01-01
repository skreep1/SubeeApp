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


private const val SUBSCRIBE_ADD = "Подписка добавлена"
private const val ERROR_FIELDS = "Заполните все поля"

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var mSubViewModel: SubViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        mSubViewModel = ViewModelProvider(this).get(SubViewModel::class.java)

        binding.buttonAdd.setOnClickListener {
            insertDataToDatabase()
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = binding.nameEt.text.toString()
        val desc = binding.descEt.text.toString()
        val price = binding.priceEt.text.toString().toDouble()

        if (inputCheck(name, desc, price)) {

            //создание sub обьекта
            val sub = Subscription(0, name, desc, price)

            //добавление в БД
            mSubViewModel.addSub(sub)

            Toast.makeText(requireContext(), SUBSCRIBE_ADD, Toast.LENGTH_LONG).show()
            //кнопка назад
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), ERROR_FIELDS, Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, desc: String, price: Double): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(desc) && price.toString().isEmpty())
    }

}
