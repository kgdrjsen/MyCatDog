package com.android.mycatdog.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.mycatdog.R
import com.android.mycatdog.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val catFragment : Fragment = CatFragment()
        val dogFragment : Fragment = DogFragment()
        val myPageFragment : Fragment = MyPageFragment()

        supportFragmentManager.beginTransaction().replace(R.id.main_view,catFragment).commit()

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.main_view,catFragment).commit()
                    }
                    1 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.main_view,dogFragment).commit()
                    }
                    2 -> {
                        supportFragmentManager.beginTransaction().replace(R.id.main_view,myPageFragment).commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}