package com.academy.alfagiftmini.presentation.homepage.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityMainBinding
import com.academy.alfagiftmini.presentation.homepage.fragment.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var berandaFragment: FragmentBeranda
    private lateinit var belanjaFragment: FragmentBelanja
    private lateinit var promoFragment: FragmentPromo
    private lateinit var pesananFragment: FragmentPesanan
    private lateinit var akunFragment: FragmentAkun

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragmentNavigation()
    }

    fun setFragmentNavigation(){
        berandaFragment = FragmentBeranda()
        belanjaFragment = FragmentBelanja()
        promoFragment = FragmentPromo()
        pesananFragment = FragmentPesanan()
        akunFragment = FragmentAkun()

        setCurrentFragment(berandaFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bnv_item_beranda->setCurrentFragment(berandaFragment)
                R.id.bnv_item_belanja->setCurrentFragment(belanjaFragment)
                R.id.bnv_item_promo->setCurrentFragment(promoFragment)
                R.id.bnv_item_pesanan->setCurrentFragment(pesananFragment)
                R.id.bnv_item_akun->setCurrentFragment(akunFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.flHomeFragmentHolder.id,fragment)
        fragmentTransaction.commit()
    }
}

