package com.wcp.moviedb.view.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wcp.moviedb.R
import com.wcp.moviedb.utilits.showToast
import com.wcp.moviedb.view.fragments.NowShowingFragment
import com.wcp.moviedb.view.fragments.PopularFragment
import com.wcp.moviedb.view.fragments.UpComingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nowShowingFragment = NowShowingFragment.newInstance()
        val upComingFragment = UpComingFragment.newInstance()
        val popularFragment = PopularFragment.newInstance()
        var fm=supportFragmentManager
        var active : Fragment = nowShowingFragment

        fm.beginTransaction().add(R.id.frameLayout, popularFragment,"popular").hide(popularFragment).commit()

        fm.beginTransaction().add(R.id.frameLayout, upComingFragment,"upcoming").hide(upComingFragment).commit()

        fm.beginTransaction().add(R.id.frameLayout, nowShowingFragment,"nowshowing").commit()

        bottom_navigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.action_nowShowing -> {
                    fm.beginTransaction().hide(active).show(nowShowingFragment).commit()
                    active = nowShowingFragment
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_upComing ->
                {
                    fm.beginTransaction().hide(active).show(upComingFragment).commit()
                    active = upComingFragment
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_cinema ->
                {

                    fm.beginTransaction().hide(active).show(popularFragment).commit()
                    active = popularFragment
                    return@setOnNavigationItemSelectedListener true
                }
            }

            return@setOnNavigationItemSelectedListener false

        }
    }

}
