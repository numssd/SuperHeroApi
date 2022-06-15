package com.example.superheroinfo.data

import com.example.superheroinfo.data.api.RetrofitServices
import com.example.superheroinfo.data.model.SuperHeroesItem
import com.example.superheroinfo.ui.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class NetworkRepository(

    private val retrofitServices: RetrofitServices

) : Repository {
    override suspend fun loadHero(id: Int): SuperHeroesItem? {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext retrofitServices.getSuperHero(id)

            } catch (e: IOException) {
                return@withContext null

            } catch (e: HttpException) {
                return@withContext null
            }
        }
    }
}