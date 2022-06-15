package com.example.superheroinfo.ui.superHereos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroinfo.App
import com.example.superheroinfo.R
import com.example.superheroinfo.data.model.SuperHeroesItem
import com.example.superheroinfo.ui.hero.HeroActivity

@ExperimentalStdlibApi
class HeroesActivity : AppCompatActivity(), OnHeroClickListener {

    private val adapter by lazy {
        HeroesAdapter(lifecycleScope, (applicationContext as App).repository, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)
        supportActionBar?.hide()
        val recycler = findViewById<RecyclerView>(R.id.recyclerViewHeroes)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }


    override fun onClick(hero: SuperHeroesItem) {
        val intent = Intent(this, HeroActivity::class.java)
        intent.putExtra("hero", hero)
        startActivity(intent)
    }
}