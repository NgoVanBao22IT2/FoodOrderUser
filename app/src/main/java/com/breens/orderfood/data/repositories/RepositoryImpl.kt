package com.breens.orderfood.data.repositories

import android.util.Log
import com.breens.orderfood.common.COLLECTION_PATH_NAME
import com.breens.orderfood.common.COLLECTION_PATH_NAME_ACCOUNT
import com.breens.orderfood.common.COLLECTION_PATH_NAME_BANNER
import com.breens.orderfood.common.COLLECTION_PATH_NAME_CARD
import com.breens.orderfood.common.COLLECTION_PATH_NAME_CATEGORY
import com.breens.orderfood.common.COLLECTION_PATH_NAME_ORDER
import com.breens.orderfood.common.PLEASE_CHECK_INTERNET_CONNECTION
import com.breens.orderfood.common.Result
import com.breens.orderfood.common.convertDateFormat
import com.breens.orderfood.common.getCurrentTimeAsString
import com.breens.orderfood.data.model.Banner
import com.breens.orderfood.data.model.Card
import com.breens.orderfood.data.model.Cate
import com.breens.orderfood.data.model.Order
import com.breens.orderfood.data.model.Task
import com.breens.orderfood.data.model.User
import com.breens.orderfood.di.IoDispatcher
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val foodDB: FirebaseFirestore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : Repository {
    override suspend fun loginUser(email: String, password: String): Result<List<User>> {
        return try {
            withContext(ioDispatcher) {

                val authResult = try {
                    firebaseAuth.signInWithEmailAndPassword(email, password).await()
                } catch (e: Exception) {
                    // Handle failed login attempt (e.g., wrong password, no network, etc.)
                    Log.d("ERROR: ", "Login failed: ${e.message}")
                    return@withContext Result.Failure(e)
                }

                // If sign-in was successful, proceed to fetch users
                if (authResult.user != null) {
                    val users = try {
                        foodDB.collection(COLLECTION_PATH_NAME_ACCOUNT)
                            .get()
                            .await()
                            .documents.map { document ->
                                User(
                                    firstName = document.getString("firstName") ?: "",
                                    lastName  = document.getString("lastName") ?: "",
                                    email  = document.getString("email") ?: "",
                                )
                            }
                    } catch (e: Exception) {
                        // Handle failed fetching from Firestore
                        Log.d("ERROR: ", "Failed to fetch user data: ${e.message}")
                        return@withContext Result.Failure(e)
                    }

                    Result.Success(users)
                } else {
                    // Handle null user scenario (unlikely in this context but good practice)
                    Result.Failure(IllegalStateException("Unknown error, user is null after successful login"))
                }
            }
        } catch (exception: Exception) {
            Log.d("ERROR: ", "$exception")

            Result.Failure(exception = exception)
        }
    }

    override suspend fun registerUser(firstName:String, lastName: String,email: String, password: String): Result<Unit> {
        return try {
            withContext(ioDispatcher) {
                val addAccountTimeout = withTimeoutOrNull(10000L) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val userProfile = hashMapOf(
                                    "firstName" to firstName,
                                    "lastName" to lastName,
                                    "email" to email
                                )
                                foodDB.collection(COLLECTION_PATH_NAME_ACCOUNT)
                                    .add(userProfile)

                            } else {
                                // Handle sign-up failure

                            }
                        }

                }

                if (addAccountTimeout == null) {
                    Log.d("ERROR: ", PLEASE_CHECK_INTERNET_CONNECTION)

                    Result.Failure(IllegalStateException(PLEASE_CHECK_INTERNET_CONNECTION))
                }

                Result.Success(Unit)
            }
        } catch (exception: Exception) {
            Log.d("ERROR: ", "$exception")

            Result.Failure(exception = exception)
        }
    }
    /*override suspend fun getAllAccount(): Result<List<User>> {
        return try {
            withContext(ioDispatcher) {
                val fetchingAccountTimeout = withTimeoutOrNull(10000L) {
                        todoChampDB.collection(COLLECTION_PATH_NAME_ACCOUNT)
                            .get()
                            .await()
                            .documents.map { document ->
                                User(
                                    firstName = document.getString("firstName") ?: "",
                                    lastName  = document.getString("lastName") ?: "",
                                    email  = document.getString("email") ?: "",
                                )
                            }

                }

                if (fetchingAccountTimeout == null) {
                    Log.d("ERROR: ", PLEASE_CHECK_INTERNET_CONNECTION)

                    Result.Failure(IllegalStateException(PLEASE_CHECK_INTERNET_CONNECTION))
                }

                Log.d("BANNERS: ", "${fetchingAccountTimeout?.toList()}")

                Result.Success(fetchingAccountTimeout?.toList() ?: emptyList())
            }
        } catch (exception: Exception) {
            Log.d("ERROR: ", "$exception")

            Result.Failure(exception = exception)
        }
    }*/
    override suspend fun getAllBanner(): Result<List<Banner>> {
        return try {
            withContext(ioDispatcher) {
                val fetchingBannerTimeout = withTimeoutOrNull(10000L) {
                    foodDB.collection(COLLECTION_PATH_NAME_BANNER)
                        .get()
                        .await()
                        .documents.map { document ->
                            Banner(
                                bannerId = document.id,
                                imageBanner = document.getString("imageBanner") ?: "",
                                titleBanner = document.getString("titleBanner") ?: "",
                                createdAt = convertDateFormat(
                                    document.getString("createdAtBanner") ?: "",
                                ),
                            )
                   }
                }

                if (fetchingBannerTimeout == null) {
                    Log.d("ERROR: ", PLEASE_CHECK_INTERNET_CONNECTION)

                    Result.Failure(IllegalStateException(PLEASE_CHECK_INTERNET_CONNECTION))
                }

                Log.d("BANNERS: ", "${fetchingBannerTimeout?.toList()}")

                Result.Success(fetchingBannerTimeout?.toList() ?: emptyList())
            }
        } catch (exception: Exception) {
            Log.d("ERROR: ", "$exception")

            Result.Failure(exception = exception)
        }
    }
    override suspend fun getAllCates(): Result<List<Cate>> {
        return try {
            withContext(ioDispatcher) {
                val fetchingCategoryTimeout = withTimeoutOrNull(10000L) {
                    foodDB.collection(COLLECTION_PATH_NAME_CATEGORY)
                        .get()
                        .await()
                        .documents.map { document ->
                            Cate(
                                cateId = document.id,
                                imageCate = document.getString("imageCate") ?: "",
                                titleCate = document.getString("titleCate") ?: "",
                                createdAt = convertDateFormat(
                                    document.getString("createdAt") ?: "",
                                ),
                            )
                        }
                }

                if (fetchingCategoryTimeout == null) {
                    Log.d("ERROR: ", PLEASE_CHECK_INTERNET_CONNECTION)

                    Result.Failure(IllegalStateException(PLEASE_CHECK_INTERNET_CONNECTION))
                }

                Log.d("CATEGORIES: ", "${fetchingCategoryTimeout?.toList()}")

                Result.Success(fetchingCategoryTimeout?.toList() ?: emptyList())
            }
        } catch (exception: Exception) {
            Log.d("ERROR: ", "$exception")

            Result.Failure(exception = exception)
        }
    }

    override suspend fun getAllTasks(): Result<List<Task>> {
        return try {
            withContext(ioDispatcher) {
                val fetchingTasksTimeout = withTimeoutOrNull(10000L) {
                    foodDB.collection(COLLECTION_PATH_NAME)
                        .get()
                        .await()
                        .documents.map { document ->
                            Task(
                                taskId = document.id,
                                image = document.getString("image") ?: "",
                                title = document.getString("title") ?: "",
                                body = document.getString("body") ?: "",
                                price = document.get("price")?.let {
                                    when (it) {
                                        is Long -> it.toInt()
                                        is Double -> it.toInt()
                                        is String -> it.toIntOrNull() ?: 0
                                        else -> 0
                                    }
                                } ?: 0,
                                createdAt = convertDateFormat(
                                    document.getString("createdAt") ?: "",
                                ),
                            )
                        }
                }

                if (fetchingTasksTimeout == null) {
                    Log.d("ERROR: ", PLEASE_CHECK_INTERNET_CONNECTION)

                    Result.Failure(IllegalStateException(PLEASE_CHECK_INTERNET_CONNECTION))
                }

                Log.d("TASKS: ", "${fetchingTasksTimeout?.toList()}")

                Result.Success(fetchingTasksTimeout?.toList() ?: emptyList())
            }
        } catch (exception: Exception) {
            Log.d("ERROR: ", "$exception")

            Result.Failure(exception = exception)
        }
    }
    override suspend fun getAllCards(): Result<List<Card>> {
        return try {
            withContext(ioDispatcher) {
                val fetchingTasksTimeout = withTimeoutOrNull(10000L) {
                    foodDB.collection(COLLECTION_PATH_NAME_CARD)
                        .get()
                        .await()
                        .documents.map { document ->
                            Card(
                                cardId = document.id,
                                imageCard = document.getString("image") ?: "",
                                titleCard = document.getString("title") ?: "",
                                bodyCard = document.getString("body") ?: "",
                                priceCard = document.get("price")?.let {
                                    when (it) {
                                        is Long -> it.toInt()
                                        is Double -> it.toInt()
                                        is String -> it.toIntOrNull() ?: 0
                                        else -> 0
                                    }
                                } ?: 0,
                                favorite = document.get("favorite")?.let {
                                    when (it) {
                                        is Long -> it.toInt()
                                        is Double -> it.toInt()
                                        is String -> it.toIntOrNull() ?: 0
                                        else -> 0
                                    }
                                } ?: 0,
                                views = document.get("views")?.let {
                                    when (it) {
                                        is Long -> it.toInt()
                                        is Double -> it.toInt()
                                        is String -> it.toIntOrNull() ?: 0
                                        else -> 0
                                    }
                                } ?: 0,
                                sale = document.get("sale")?.let {
                                    when (it) {
                                        is Long -> it.toInt()
                                        is Double -> it.toInt()
                                        is String -> it.toIntOrNull() ?: 0
                                        else -> 0
                                    }
                                } ?: 0,
                                createdAt = convertDateFormat(
                                    document.getString("createdAt") ?: "",
                                ),
                            )
                        }
                }

                if (fetchingTasksTimeout == null) {
                    Log.d("ERROR: ", PLEASE_CHECK_INTERNET_CONNECTION)

                    Result.Failure(IllegalStateException(PLEASE_CHECK_INTERNET_CONNECTION))
                }

                Log.d("CARDS: ", "${fetchingTasksTimeout?.toList()}")

                Result.Success(fetchingTasksTimeout?.toList() ?: emptyList())
            }
        } catch (exception: Exception) {
            Log.d("ERROR: ", "$exception")

            Result.Failure(exception = exception)
        }
    }

    override suspend fun updateCard(
        image: String,
        title: String,
        body: String,
        price: Int,
        favorite: Int,
        views: Int,
        sale: Int,
        cardId: String
    ): Result<Unit> {
        return try {
            withContext(ioDispatcher) {
                val cardUpdate: Map<String, Any> = hashMapOf(
                    "image" to image,
                    "title" to title,
                    "body" to body,
                    "price" to price,
                    "favorite" to favorite,
                    "views" to views,
                    "sale" to sale,
                )

                val addStatusTimeout = withTimeoutOrNull(10000L) {
                    foodDB.collection(COLLECTION_PATH_NAME_CARD)
                        .document(cardId)
                        .update(cardUpdate)
                }

                if (addStatusTimeout == null) {
                    Log.d("ERROR: ", PLEASE_CHECK_INTERNET_CONNECTION)

                    Result.Failure(IllegalStateException(PLEASE_CHECK_INTERNET_CONNECTION))
                }

                Result.Success(Unit)
            }
        } catch (exception: Exception) {
            Log.d("ERROR: ", "$exception")

            Result.Failure(exception = exception)
        }
    }
    override suspend fun addOrder(
        code: String,
        address: String,
        imageOrder: String,
        titleOrder: String,
        price: Int,
        quantity: Int,
        paymentMethods: String,
        total: Int,
    ): Result<Unit> {
        return try {
            withContext(ioDispatcher) {
                val order = hashMapOf(
                    "code" to code,
                    "address" to address,
                    "imageOrder" to imageOrder,
                    "title" to titleOrder,
                    "price" to price,
                    "quantity" to quantity,
                    "paymentMethods" to paymentMethods,
                    "total" to total,
                    "status" to "0",
                    "createdAt" to getCurrentTimeAsString(),
                )

                val addOrderTimeout = withTimeoutOrNull(10000L) {
                    foodDB.collection(COLLECTION_PATH_NAME_ORDER)
                        .add(order)
                }

                if (addOrderTimeout == null) {
                    Log.d("ERROR: ", PLEASE_CHECK_INTERNET_CONNECTION)

                    Result.Failure(IllegalStateException(PLEASE_CHECK_INTERNET_CONNECTION))
                }

                Result.Success(Unit)
            }
        } catch (exception: Exception) {
            Log.d("ERROR: ", "$exception")

            Result.Failure(exception = exception)
        }
    }



    override suspend fun getAllOrder(): Result<List<Order>> {
        return try {
            withContext(ioDispatcher) {
                val fetchingOrdersTimeout = withTimeoutOrNull(10000L) {
                    foodDB.collection(COLLECTION_PATH_NAME_ORDER)
                        .get()
                        .await()
                        .documents.map { document ->
                            Order(
                                orderId = document.id,
                                code = document.getString("code") ?: "",
                                address = document.getString("address") ?: "",
                                imageOrder = document.getString("imageOrder") ?: "",
                                titleOrder = document.getString("title") ?: "",
                                paymentMethods = document.getString("paymentMethods") ?: "",
                                price = document.get("price")?.let {
                                    when (it) {
                                        is Long -> it.toInt()
                                        is Double -> it.toInt()
                                        is String -> it.toIntOrNull() ?: 0
                                        else -> 0
                                    }
                                } ?: 0,
                                quantity = document.get("quantity")?.let {
                                    when (it) {
                                        is Long -> it.toInt()
                                        is Double -> it.toInt()
                                        is String -> it.toIntOrNull() ?: 0
                                        else -> 0
                                    }
                                } ?: 0,
                                total = document.get("total")?.let {
                                    when (it) {
                                        is Long -> it.toInt()
                                        is Double -> it.toInt()
                                        is String -> it.toIntOrNull() ?: 0
                                        else -> 0
                                    }
                                } ?: 0,
                                createdAt = convertDateFormat(
                                    document.getString("createdAt") ?: "",
                                ),
                                status = document.getString("status") ?: ""
                            )
                        }
                }

                if (fetchingOrdersTimeout == null) {
                    Log.d("ERROR: ", PLEASE_CHECK_INTERNET_CONNECTION)

                    Result.Failure(IllegalStateException(PLEASE_CHECK_INTERNET_CONNECTION))
                }

                Log.d("ORDERS: ", "${fetchingOrdersTimeout?.toList()}")

                Result.Success(fetchingOrdersTimeout?.toList() ?: emptyList())
            }
        } catch (exception: Exception) {
            Log.d("ERROR: ", "$exception")

            Result.Failure(exception = exception)
        }
    }
    override suspend fun updateStatus(code: String, address: String,imageOrder: String, titleOrder: String,price:  Int , quantity:  Int, paymentMethods: String, total: Int,status: String, orderId: String): Result<Unit> {
        return try {
            withContext(ioDispatcher) {
                val statusUpdate: Map<String, Any> = hashMapOf(
                    "code" to code,
                    "address" to address,
                    "imageOrder" to imageOrder,
                    "title" to titleOrder,
                    "price" to price,
                    "quantity" to quantity,
                    "paymentMethods" to paymentMethods,
                    "total" to total,
                    "status" to status,
                )

                val addStatusTimeout = withTimeoutOrNull(10000L) {
                    foodDB.collection(COLLECTION_PATH_NAME_ORDER)
                        .document(orderId)
                        .update(statusUpdate)
                }

                if (addStatusTimeout == null) {
                    Log.d("ERROR: ", PLEASE_CHECK_INTERNET_CONNECTION)

                    Result.Failure(IllegalStateException(PLEASE_CHECK_INTERNET_CONNECTION))
                }

                Result.Success(Unit)
            }
        } catch (exception: Exception) {
            Log.d("ERROR: ", "$exception")

            Result.Failure(exception = exception)
        }
    }

}
