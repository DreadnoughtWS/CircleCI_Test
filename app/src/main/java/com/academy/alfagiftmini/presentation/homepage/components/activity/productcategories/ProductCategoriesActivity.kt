package com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductCategoriesBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories.FragmentProductCategoriesDetail
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productCategoriesActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityProductCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = viewModel.getIntentData(intent) ?: return
        setTabItems(data.subcategories, data.text)
    }

    private fun setTabItems(subcategories: List<String>, category: String) {

        binding.apply {
            subcategories.forEach {
                tabLayout.addTab(tabLayout.newTab().setCustomView(
                    R.layout.tab_item
                ).apply {
                    customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = it
                })
            }

            tabLayout.addOnTabSelectedListener(object: OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    setupFragment(tab.position, subcategories, category)

                    if (tab.position == 1) {
                        tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                            ?.setImageResource(R.drawable.arrow_up_tab_item_blue)

                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    if (tab.position == 1) {
                        tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                            ?.setImageResource(R.drawable.arrow_up_tab_item)
                        tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                            ?.setImageResource(R.drawable.arrow_down_tab_item)

                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        }
    }

    private fun setupFragment(position: Int, subcategories: List<String>, category: String) {
        val fragment = FragmentProductCategoriesDetail(viewModel, subcategories[position], category)
        val tag = FragmentProductCategoriesDetail::class.java.simpleName
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(binding.container.id, fragment, tag)
            commit()
        }
    }
}