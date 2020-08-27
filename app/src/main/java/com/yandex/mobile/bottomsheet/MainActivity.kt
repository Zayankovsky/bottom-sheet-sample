package com.yandex.mobile.bottomsheet

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.transition.Slide
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet
import kotlinx.android.synthetic.main.activity_main.*

private const val transitionName = "scroll"

class MainActivity : AppCompatActivity() {

    private val currentScroll get() = supportFragmentManager
        .findFragmentById(R.id.container)
        ?.view?.findViewById<View>(R.id.scroll)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        first.setOnClickListener { replace(R.layout.fragment_first) }
        second.setOnClickListener { replace(R.layout.fragment_second) }
        third.setOnClickListener { replace(R.layout.fragment_third) }
    }

    private fun replace(@LayoutRes contentLayoutId: Int) {
        val fragment = MainFragment(contentLayoutId)
        supportFragmentManager.commit {
            currentScroll?.let { setupSharedTransition(fragment, it) }
                ?: setupSlideTransition(fragment)
            replace(R.id.container, fragment)
        }
    }

    private fun FragmentTransaction.setupSharedTransition(fragment: Fragment, element: View) {
        val moveTransition = TransitionInflater.from(this@MainActivity)
            .inflateTransition(android.R.transition.move) as TransitionSet
        moveTransition.addTransition(ChangeColor())
        fragment.sharedElementEnterTransition = moveTransition
        addSharedElement(element, transitionName)
    }

    private fun setupSlideTransition(fragment: Fragment) {
        fragment.enterTransition = Slide()
    }
}