package com.academy.alfagiftmini.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.academy.alfagiftmini.domain.banner.BannerDomainUseCase
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.productcategories.ProductCategoriesUseCase
import com.academy.alfagiftmini.domain.productdetail.ProductDetailUseCase
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCase
import com.academy.alfagiftmini.domain.register.RegisterDomainUseCase
import com.academy.alfagiftmini.presentation.authentication.viewmodel.LoginViewModel
import com.academy.alfagiftmini.presentation.authentication.viewmodel.RegisterViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.*
import javax.inject.Inject

class PresentationFactory @Inject constructor(
    private var loginUseCase: LoginDomainUseCase,
    private var registerUseCase: RegisterDomainUseCase,
    private var officialStoreUseCase: OfficialStoreDomainUseCase,
    private var productListDomainUseCase: ProductListDomainUseCase,
    private var bannerDomainUseCase: BannerDomainUseCase,
    private var productDetailDomainUseCase: ProductDetailUseCase,
    private var productCategoriesUseCase: ProductCategoriesUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                loginUseCase
            ) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(
                registerUseCase
            ) as T
            modelClass.isAssignableFrom(OfficialStoreViewModel::class.java) -> OfficialStoreViewModel(
                officialStoreUseCase
            ) as T
            modelClass.isAssignableFrom(ProductListViewModel::class.java) -> ProductListViewModel(
                productListDomainUseCase
            ) as T
            modelClass.isAssignableFrom(BannerListViewModel::class.java) -> BannerListViewModel(
                bannerDomainUseCase
            ) as T
            modelClass.isAssignableFrom(ProductDetailViewModel::class.java) -> ProductDetailViewModel(
                productDetailDomainUseCase
            ) as T
            modelClass.isAssignableFrom(ProductCategoriesViewModel::class.java) -> ProductCategoriesViewModel(
                productCategoriesUseCase
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

}