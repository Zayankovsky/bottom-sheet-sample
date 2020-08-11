package com.yandex.mobile.bottomsheet

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main.setOnClickListener { replace(R.layout.fragment_main) }
        second.setOnClickListener { replace(R.layout.fragment_second) }
        third.setOnClickListener { replace(R.layout.fragment_third) }
    }

    private fun replace(@LayoutRes contentLayoutId: Int) {
        supportFragmentManager.commit { replace(R.id.container, MainFragment(contentLayoutId)) }
    }
}