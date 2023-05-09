package com.academy.alfagiftmini.presentation.homepage.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityMainBinding
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.presentation.authentication.viewmodel.LoginViewModel
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.academy.alfagiftmini.presentation.homepage.fragment.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var berandaFragment: FragmentBeranda
    private lateinit var belanjaFragment: FragmentBelanja
    private lateinit var promoFragment: FragmentPromo
    private lateinit var pesananFragment: FragmentPesanan
    private lateinit var akunFragment: FragmentAkun

    @Inject
    lateinit var viewModelFactory: PresentationFactory
    private val mainActivityViewModel: LoginViewModel by viewModels{
        viewModelFactory
    }
    private val productListViewModel: ProductListViewModel by viewModels {
        viewModelFactory
    }
    private val officialStoreViewModel: OfficialStoreViewModel by viewModels {
        viewModelFactory
    }
    private val productCategoriesViewModel: ProductCategoriesViewModel by viewModels {
        viewModelFactory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.mainActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setFragmentNavigation()
        getUserId()
    }

    fun getMainViewModel(): LoginViewModel {
        return mainActivityViewModel
    }

    private fun getUserId() {
        mainActivityViewModel.getIntentData(intent)
    }

    private fun setToolbar() {
        supportActionBar?.hide()
    }

    private fun setFragmentNavigation(){
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


    fun setCurrentFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.flHomeFragmentHolder.id,fragment)
        fragmentTransaction.commit()
    }

    fun getViewModelProductList():ProductListViewModel{
        return productListViewModel
    }

    fun getViewModelOfficialStore():OfficialStoreViewModel{
        return officialStoreViewModel
    }

    fun getViewModelProductCategories():ProductCategoriesViewModel{
        return productCategoriesViewModel
    }
}

