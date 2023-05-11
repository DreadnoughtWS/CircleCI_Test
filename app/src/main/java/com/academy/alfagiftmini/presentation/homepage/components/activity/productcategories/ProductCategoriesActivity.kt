package com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductCategoriesBinding
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.OfficialStoreSearchActivity
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories.FragmentProductCategoriesDetail
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import javax.inject.Inject

class ProductCategoriesActivity: AppCompatActivity() {
    private lateinit var binding: ActivityProductCategoriesBinding

    @Inject
    lateinit var viewModelFactory: PresentationFactory
    private val viewModel: ProductCategoriesViewModel by viewModels{
        viewModelFactory
    }
    private val productListViewModel: ProductListViewModel by viewModels{
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productCategoriesActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityProductCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = viewModel.getIntentData(intent) ?: return
        setToolbar(data.text)
        setTabItems(data.subcategories, data.text)
        setupFragment(data.subcategories[0], data.text)
    }

    private fun setToolbar(category: String) {
        supportActionBar?.hide()
        binding.apply {
            productCategoryListToolbar.tvToolbar.text = category
            productCategoryListToolbar.ivBackToolbar.setOnClickListener {
                finish()
            }
            productCategoryListToolbar.ivSearchToolbar.setOnClickListener {
                startActivity(
                    Intent(
                        this@ProductCategoriesActivity, OfficialStoreSearchActivity::class.java
                    )
                )
            }
        }

    }

    private fun setTabItems(subcategories: List<String>, category: String) {
        binding.apply {
            subcategories.forEach {
                tabLayout.addTab(tabLayout.newTab().setText(it))
            }

            tabLayout.addOnTabSelectedListener(object: OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) { setupFragment(subcategories[tab.position], category) }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                }

                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        }
    }

    private fun setupFragment(subcategories: String, category: String) {
        val fragment = FragmentProductCategoriesDetail(subcategories, category)
        val tag = FragmentProductCategoriesDetail::class.java.simpleName
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(binding.container.id, fragment, tag)
            commit()
        }
    }

    fun getProductListCategoryViewModel(): ProductListViewModel {
        return productListViewModel
    }
}