package com.example.taskimplement

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.taskimplement.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(),Communicator {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(FirstFragment())

        binding.button.setOnClickListener {
            if (!isAccessibilityServiceEnabled()) {
                val intent = Intent(ACTION_ACCESSIBILITY_SETTINGS)
                startActivity(intent)
            }
        }

    }
    private fun isAccessibilityServiceEnabled(): Boolean {
        val accessibilityServiceComponent = ComponentName(this, MyAccessibilityService::class.java)
        val accessibilityServices =
            Settings.Secure.getString(contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
        return accessibilityServices?.contains(accessibilityServiceComponent.flattenToString()) == true
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout, fragment)
        transaction.commit()

    }

    override fun passData(id: Int, userId: Int, title: String, body: String) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        bundle.putInt("userid", userId)
        bundle.putString("title", title)
        bundle.putString("body", body)
        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentTwo = SecondFragment()
        fragmentTwo.arguments = bundle
        transaction.replace(R.id.framelayout, fragmentTwo)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()


    }


}