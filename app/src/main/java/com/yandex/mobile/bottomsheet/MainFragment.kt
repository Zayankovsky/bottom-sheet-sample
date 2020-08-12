package com.yandex.mobile.bottomsheet

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val behavior = BottomSheetBehavior.from(view.findViewById<View>(R.id.scroll))
        behavior.isHideable = true
        behavior.skipCollapsed = true
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    parentFragmentManager.commit { remove(this@MainFragment) }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            behavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }
}