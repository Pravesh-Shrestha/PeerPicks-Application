package com.example.peerpicks.ui.activity


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.peerpicks.R
import com.example.peerpicks.databinding.ActivityNavigationBinding
import com.example.peerpicks.ui.fragment.Home1Fragment
import com.example.peerpicks.ui.fragment.Home2Fragment
import com.example.peerpicks.ui.fragment.HomeFragment
import com.example.peerpicks.ui.fragment.MessageFragment
import com.example.peerpicks.ui.fragment.NotificationFragment
import com.example.peerpicks.ui.fragment.ProfileFragment
import com.example.peerpicks.ui.fragment.SearchFragment

class NavigationActivity : AppCompatActivity() {
    lateinit var binding:ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        replaceFragment(HomeFragment())
//        binding.btnFirst.setOnClickListener {
//            replaceFragment(HomeFragment())
//        }
//        binding.btnSecond.setOnClickListener {
//            replaceFragment(Home2Fragment())
//        }

        replaceFragment(HomeFragment())
        binding.bottomView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navHome->replaceFragment(Home1Fragment())
                R.id.navSearch->replaceFragment(SearchFragment())
                R.id.navMessage->replaceFragment(MessageFragment())
                R.id.navNotification->replaceFragment(NotificationFragment())
                R.id.navProfile->replaceFragment(ProfileFragment())

                else ->{}
            }
            true

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager : FragmentManager =
            supportFragmentManager
        val fragmentTransaction : FragmentTransaction =
            fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.frameBottom.id,
            fragment)
        fragmentTransaction.commit()
    }
}