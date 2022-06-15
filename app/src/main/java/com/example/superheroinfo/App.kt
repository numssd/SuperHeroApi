package com.example.superheroinfo

import android.app.Application
import android.content.SharedPreferences
import com.example.superheroinfo.data.ComposeRepository
import com.example.superheroinfo.data.NetworkRepository
import com.example.superheroinfo.data.SharedPreferencesRepository
import com.example.superheroinfo.data.api.SuperHeroAPI
import com.example.superheroinfo.ui.Repository
import com.google.gson.Gson

class App : Application() {


    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        val preferences: SharedPreferences = getSharedPreferences("HERO", MODE_PRIVATE)
        val gson = Gson()
        val networkRepository = NetworkRepository(SuperHeroAPI.retrofitServices)
        val sharedPreferencesRepository = SharedPreferencesRepository(preferences,gson)
        repository = ComposeRepository(networkRepository, sharedPreferencesRepository)


    }
}