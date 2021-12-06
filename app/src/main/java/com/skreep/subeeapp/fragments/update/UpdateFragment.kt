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
import com.skreep.subeeapp.model.Subscription
import com.skreep.subeeapp.viewmodel.SubViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mSubViewModel: SubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mSubViewModel = ViewModelProvider(this).get(SubViewModel::class.java)


        view.updateName_et.setText(args.currentSub.nameSub)
        view.updateDesc_et.setText(args.currentSub.descSub)
        view.updatePrice_et.setText(args.currentSub.priceSub)

        view.button_update.setOnClickListener {
            updateSub()
        }

        //добавлени меню
        setHasOptionsMenu(true)

        return view
    }

    /** Функция для обновления подписок в БД */

    private fun updateSub() {
        val nameSub = updateName_et.text.toString()
        val descSub = updateDesc_et.text.toString()
        val priceSub = updatePrice_et.text.toString()

        if (inputCheck(nameSub, descSub, priceSub)) {
            //добавление обьекта

            val updateSub = Subscription(args.currentSub.id, nameSub, descSub, priceSub)

            //обновление текущего Sub
            mSubViewModel.updateSub(updateSub)

            Toast.makeText(requireContext(), "Обновленно", Toast.LENGTH_SHORT).show()
            //Навигация назад
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(name: String, desc: String, price: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(desc) && price.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_menu) {
            deleteSub()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteSub() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") { _, _ ->
            mSubViewModel.deleteSub(args.currentSub)
            Toast.makeText(
                requireContext(),
                "Вы удалили: ${args.currentSub.nameSub}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("Нет") { _, _ -> }
        builder.setTitle("Удалить ${args.currentSub.nameSub}?")
        builder.setMessage("Вы уверены, что хотите удалить ${args.currentSub.nameSub}")
        builder.create().show()
    }
}
