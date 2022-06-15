package com.example.superheroinfo.data

import com.example.superheroinfo.data.model.SuperHeroesItem
import com.example.superheroinfo.ui.Repository

class ComposeRepository(
    private val networkRepository: NetworkRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository,
) : Repository {

    override suspend fun loadHero(id: Int): SuperHeroesItem? {
        val heroFromPrefs = sharedPreferencesRepository.loadHero(id)
        return if (heroFromPrefs == null) {
            val hero = networkRepository.loadHero(id)
            if (hero != null) {
                sharedPreferencesRepository.saveHero(hero)
            }
            hero
        } else {
            heroFromPrefs
        }
    }
}