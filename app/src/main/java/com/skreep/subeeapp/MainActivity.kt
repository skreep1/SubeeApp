package com.skreep.subeeapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




//
//        val modalBottomSheet = BottomFragment()
//        modalBottomSheet.show(supportFragmentManager, BottomFragment.TAG)


//        bottomSheetBehavior = BottomSheetBehavior.from<ConstraintLayout>(persistent_bottom_sheet)
//
//        bottomSheetBehavior.addBottomSheetCallback(object :
//            BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//
//            }
//
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//            }
//
//        })

//        if(savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragment, BottomFragment::class.java, null)
//                .show(BottomFragment())
//                .commit()
//        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}