package com.example.superheroinfo.ui

import com.example.superheroinfo.data.model.SuperHeroesItem

interface Repository {
    suspend fun loadHero(id: Int): SuperHeroesItem?
}
