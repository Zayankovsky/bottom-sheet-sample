package com.yandex.mobile.bottomsheet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val behavior = BottomSheetBehavior.from(scroll)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        content.setOnClickListener { behavior.state = BottomSheetBehavior.STATE_EXPANDED }
    }
}