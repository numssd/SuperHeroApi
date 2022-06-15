package com.example.superheroinfo.ui.hero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.superheroinfo.R
import com.example.superheroinfo.data.model.SuperHeroesItem
import com.squareup.picasso.Picasso

class HeroActivity : AppCompatActivity() {
    companion object {
        const val HERO_ARG = "hero"
    }

    private val heroItem: SuperHeroesItem by lazy { intent.getSerializableExtra(HERO_ARG) as SuperHeroesItem }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)
        supportActionBar?.hide()
        val img = findViewById<ImageView>(R.id.imageViewHero)
        val name = findViewById<TextView>(R.id.textViewNameHero)
        val intelligence = findViewById<TextView>(R.id.textViewIntelligence)
        val strength = findViewById<TextView>(R.id.textViewStrength)
        val speed = findViewById<TextView>(R.id.textViewSpeed)
        val durability = findViewById<TextView>(R.id.textViewDurability)
        val power = findViewById<TextView>(R.id.textViewPower)
        val combat = findViewById<TextView>(R.id.textViewCombat)
        Picasso.get().load(heroItem.image.sm).into(img)
        name.text = heroItem.name
        intelligence.text = heroItem.powerstats.intelligence.toString()
        strength.text = heroItem.powerstats.strength.toString()
        speed.text = heroItem.powerstats.speed.toString()
        durability.text = heroItem.powerstats.durability.toString()
        power.text = heroItem.powerstats.power.toString()
        combat.text = heroItem.powerstats.combat.toString()
    }
}