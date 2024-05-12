package com.breens.orderfood.data.repositories

import com.breens.orderfood.common.Result
import com.breens.orderfood.data.model.Banner
import com.breens.orderfood.data.model.Card
import com.breens.orderfood.data.model.Cate
import com.breens.orderfood.data.model.Chat
import com.breens.orderfood.data.model.Order
import com.breens.orderfood.data.model.Task
import com.breens.orderfood.data.model.User

interface Repository {
   // Tai khoan
    suspend fun loginUser(email: String, password: String): Result<List<User>>
    suspend fun registerUser(firstName:String, lastName: String,email: String, password: String): Result<Unit>

//    Card
    suspend fun getAllCards(): Result<List<Card>>
 //    suspend fun deleteCard(cardId: String): Result<Unit>
    suspend fun updateCard(image: String,title: String, body: String, price: Int, favorite: Int, views: Int, sale: Int, cardId: String): Result<Unit>

    suspend fun getAllTasks(): Result<List<Task>>

    suspend fun getAllBanner(): Result<List<Banner>>

    //   Category
    suspend fun getAllCates(): Result<List<Cate>>


    suspend fun addOrder(userID: String, code: String, address: String,imageOrder: String, titleOrder: String,price: Int, quantity: Int, paymentMethods: String, total: Int): Result<Unit>
    suspend fun getAllOrder(): Result<List<Order>>
    suspend fun updateStatus(userID: String, code: String, address: String,imageOrder: String, titleOrder: String,price:  Int , quantity:  Int, paymentMethods: String, total: Int,status: String, orderId: String): Result<Unit>

    suspend fun addMessage( senderID: String, message: String, direction : Boolean): Result<Unit>
    suspend fun getAllMessage(): Result<List<Chat>>
}


