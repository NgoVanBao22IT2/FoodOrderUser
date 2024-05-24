package com.breens.orderfoodapp.data.repositories

import com.breens.orderfoodapp.data.model.UserProfile

interface UserRepository {
    suspend fun getCurrentUser(): UserProfile
    suspend fun updateUser(user: UserProfile)
    suspend fun getSavedJobs(): List<String>
    suspend fun getUserById(userId: String): UserProfile
    suspend fun saveJob(jobId: String)
    suspend fun removeSavedJob(jobId: String)
}