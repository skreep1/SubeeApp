package com.skreep.subeeapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.skreep.subeeapp.R
import com.skreep.subeeapp.databinding.FragmentUpdateBinding
import com.skreep.subeeapp.model.Subscription
import com.skreep.subeeapp.viewmodel.SubViewModel


private const val UPDATE_TEXT = "Обновленно"
private const val ERROR_FIELDS = "Заполните все поля"
private const val YES_ALERT = "Да"
private const val NO_ALERT = "Нет"

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mSubViewModel: SubViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        mSubViewModel = ViewModelProvider(this).get(SubViewModel::class.java)

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        with(binding) {
            updateNameEt.setText(args.currentSub.nameSub)
            updateDescEt.setText(args.currentSub.descSub)
            updatePriceEt.setText(args.currentSub.priceSub.toString())

            buttonUpdate.setOnClickListener {
                updateSub()
            }
            binding.buttonDelete.setOnClickListener {
                deleteSub()
            }
        }

        return view
    }

    private fun updateSub() {
        val nameSub = binding.updateNameEt.text.toString()
        val descSub = binding.updateDescEt.text.toString()
        val priceSub = binding.updatePriceEt.text.toString().toDouble()

        if (inputCheck(nameSub, descSub, priceSub.toString().toDouble())) {

            val updateSub =
                Subscription(args.currentSub.id, nameSub, descSub, priceSub.toString().toDouble())

            mSubViewModel.updateSub(updateSub)

            Toast.makeText(requireContext(), UPDATE_TEXT, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), ERROR_FIELDS, Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(name: String, desc: String, price: Double): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(desc) && price.toString().isEmpty())
    }


    private fun deleteSub() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(YES_ALERT) { _, _ ->
            mSubViewModel.deleteSub(args.currentSub)
            Toast.makeText(
                requireContext(), "Вы удалили: ${args.currentSub.nameSub}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton(NO_ALERT) { _, _ -> }
        builder.setTitle("Удалить ${args.currentSub.nameSub}?")
        builder.setMessage("Вы уверены, что хотите удалить ${args.currentSub.nameSub}")
        builder.create().show()
    }
}
