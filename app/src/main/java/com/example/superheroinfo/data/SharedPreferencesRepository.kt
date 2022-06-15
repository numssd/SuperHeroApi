package com.example.superheroinfo.data

import android.content.SharedPreferences
import com.example.superheroinfo.data.model.SuperHeroesItem
import com.example.superheroinfo.ui.Repository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedPreferencesRepository(
    private val preferences: SharedPreferences,
    private val gson: Gson,
) : Repository {

    suspend fun saveHero(hero: SuperHeroesItem) {
        withContext(Dispatchers.IO) {
            val json = gson.toJson(hero)
            val editor = preferences.edit()
            editor.putString(hero.id.toString(), json)
            editor.apply()
        }
    }

    override suspend fun loadHero(id: Int): SuperHeroesItem? {
        return withContext(Dispatchers.IO) {
            val json = preferences.getString(id.toString(), null)
            return@withContext gson.fromJson(json, SuperHeroesItem::class.java)
        }
    }
}