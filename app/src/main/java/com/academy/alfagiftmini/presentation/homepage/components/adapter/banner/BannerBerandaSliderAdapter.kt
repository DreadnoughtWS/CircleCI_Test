package com.academy.alfagiftmini.presentation.homepage.components.adapter.banner

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ItemBannerSliderBinding
import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.banner.BannerPromoItemList
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class BannerBerandaSliderAdapter(
    private var listBanner: List<BannerDomainModel>, var context: Context
): SliderViewAdapter<BannerBerandaSliderAdapter.BannerAdapterViewHolder>() {

    class BannerAdapterViewHolder(val binding: ItemBannerSliderBinding) : ViewHolder(binding.root) {
        fun bindData(data: BannerDomainModel) {
            Glide.with(itemView.context).load(data.bannerImageFileName).placeholder(R.drawable.banner_placeholder)
                .into(binding.ivImageSlider)
        }
    }

    override fun getCount(): Int {
        return listBanner.size
    }

    override fun onCreateViewHolder(parent: ViewGroup): BannerAdapterViewHolder {
        val binding =
            ItemBannerSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return BannerAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: BannerAdapterViewHolder, position: Int) {
            viewHolder.bindData(listBanner[position])
            viewHolder.binding.ivImageSlider.setOnClickListener {
                val intent = Intent(context, BannerPromoItemList::class.java)
                intent.putExtra(PresentationUtils.BANNER_ID, listBanner[position].id)
                context.startActivity(intent)
            }
    }


}