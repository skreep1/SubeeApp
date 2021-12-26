package com.skreep.subeeapp.fragments.start

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.skreep.subeeapp.R
import com.skreep.subeeapp.databinding.FragmentAddBinding
import com.skreep.subeeapp.databinding.FragmentGetStartBinding


private const val SHARED_PREFS_NAME = "shared_prefs_name"

class GetStartFragment : Fragment() {

    private lateinit var getStartBinding: FragmentGetStartBinding
    private var _binding: FragmentGetStartBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGetStartBinding.inflate(inflater, container,false)
        val view = binding.root

        binding.getStartButton.setOnClickListener {
            findNavController().navigate(R.id.action_getStartFragment_to_listFragment)
        }


        return view


    }

    override fun onResume() {
        super.onResume()

        val sharedPref = activity?.getPreferences(
            Context.MODE_PRIVATE
        ) ?: return

        val first = sharedPref.getString(SHARED_PREFS_NAME, "")

        if (first.equals("yes")) {

            findNavController().navigate(R.id.action_getStartFragment_to_listFragment)


        } else {
            val editor = sharedPref.edit();
            editor.putString(SHARED_PREFS_NAME, "yes");
            editor.apply();
        }
    }


}




