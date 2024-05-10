package com.breens.orderfood.feature_tasks.state

import android.graphics.Bitmap
import com.breens.orderfood.data.model.Banner
import com.breens.orderfood.data.model.Card
import com.breens.orderfood.data.model.Cate
import com.breens.orderfood.data.model.Order
import com.breens.orderfood.data.model.Task
import com.breens.orderfood.data.model.User

data class SignInScreenUiState(
    val accounts: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val currentFirstname: String = "",
    val currentLastName: String = "",
    val currentEmail: String = "",
    val currentPassword: String = "",
)
data class TasksScreenUiState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val errorMessage: String? = null,
    val taskToBeUpdated: Task? = null,
    val isShowAddTaskDialog: Boolean = false,
    val isShowUpdateTaskDialog: Boolean = false,
    val currentTextFieldTitle: String = "",
    val currentTextFieldBody: String = "",
    val currentTextFieldPrice: Int = 0,
    var imgUrl: String = "",
    var bitmap: Bitmap? = null,
    var selectedOptionSale : Int = options[0],
)
data class CardsScreenUiState(
    val isLoading: Boolean = false,
    val cards: List<Card> = emptyList(),
    val errorMessage: String? = null,
    val cardToBeUpdated: Card? = null,
    val isShowAddCardDialog: Boolean = false,
    val isShowUpdateCardDialog: Boolean = false,
    val currentTextFieldTitle: String = "",
    val currentTextFieldBody: String = "",
    val currentTextFieldPrice: Int = 0,
    val currentTextFieldViews: Int = 0,
    val currentTextFieldFavorite: Int = 0,
    val currentTextFieldSale: Int = 0,

    var imgUrl: String = "",
    var bitmap: Bitmap? = null,

    var selectedOption : Int = options[0],
    val cartProducts: List<Card> = emptyList(),

    )

val options = listOf(10000 ,15000, 20000)
data class BannerScreenUiState(
    val isLoadingBanner: Boolean = false,
    val banners: List<Banner> = emptyList(),
    val errorMessage: String? = null,
    val isShowAddBannerDialog: Boolean = false,
    val currentTextFieldTitleBanner: String = "",
    var imgUrlBanner: String = "",
    var bitmapBanner: Bitmap? = null
)
data class CatesScreenUiState(
    val isLoadingCate: Boolean = false,
    val cates: List<Cate> = emptyList(),
    val errorMessage: String? = null,
    val isShowAddCateDialog: Boolean = false,
    val currentTextFieldTitleCate: String = "",
    var imgUrlCate: String = "",
    var bitmapCate: Bitmap? = null,
)
data class OrderScreenUiState(
    val isLoading: Boolean = false,
    val orders: List<Order> = emptyList(),
    val statusToBeUpdated: Order? = null,
    val errorMessage: String? = null,
    val currentCode: String = "",
    val currentTitle: String = "",
    val currentAddressOrder: String = "",
    val currentQuantityOrder:Int = 0,
    val currentPriceOrder: Int = 0,
    val total: Int = 0,
    val currentPaymentOrder: String = "",
    var imgUrlOrder: String = "",
    var bitmapOrder: Bitmap? = null,
    val currentStatus: String = "",
    var selectedOptionPayment : String = optionsPayment[0],
    var selectedOptionAddress : String = optionsAddress[0],
    var valueChange : Int = 1,
    var valueCart : Int = 0,
    var value : Int = 1

)
val optionsAddress = listOf("FPT Plaza2, đường Trần Quốc Vượng, phường Hòa Hải", "Kí túc xá VKU, đường Nam kì Khởi Nghĩa, phường Hòa Hải")
val optionsPayment = listOf("Thanh toán khi nhận hàng" ,"Thanh toán qua ví điện tử")


