package com.academy.alfagiftmini.presentation.homepage.components.adapter.banner

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.smarteist.autoimageslider.SliderViewAdapter

class BannerBerandaSliderAdapter(
    private var listBanner: List<BannerDomainModel>, var context: Context
): SliderViewAdapter<BannerBerandaSliderAdapter.BannerAdapterViewHolder>() {

    class BannerAdapterViewHolder(itemView: View) : ViewHolder(itemView) {

    }

    override fun getCount(): Int {
        return listBanner.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): BannerAdapterViewHolder {

    }

    override fun onBindViewHolder(viewHolder: BannerAdapterViewHolder?, position: Int) {

    }

}