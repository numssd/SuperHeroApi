package com.example.superheroinfo.ui.superHereos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroinfo.R
import com.example.superheroinfo.data.model.SuperHeroesItem
import com.example.superheroinfo.ui.Repository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HeroesAdapter(
    private val scope: CoroutineScope,
    private val repository: Repository,
    private val onHeroClickListener: OnHeroClickListener
) : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    companion object {
        const val HEROES_COUNT = 731
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_items, parent, false)
        return HeroesViewHolder(item)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bindData(position + 1)
    }

    override fun getItemCount(): Int {
        return HEROES_COUNT
    }

    override fun onViewRecycled(holder: HeroesViewHolder) {
        holder.cancelData()
    }

    inner class HeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val heroImg = itemView.findViewById<ImageView>(R.id.imageViewPhotoHero)
        private val heroName = itemView.findViewById<TextView>(R.id.textViewHeroName)
        private var job: Job? = null
        private var hero: SuperHeroesItem? = null

        init {
            itemView.setOnClickListener {
                val hero = hero
                if (hero != null) {
                    onHeroClickListener.onClick(hero)
                }
            }
        }

        fun bindData(id: Int) {
            Picasso.get().load(R.drawable.placeholder_loading).into(heroImg)
            heroName.text = "..."
            hero = null
            job?.cancel()

            job = scope.launch {
                val item = repository.loadHero(id)
                if (item == null) {
                    Picasso.get().load(R.drawable.placeholder_error).into(heroImg)
                    heroName.text = "Х_Х"
                } else {
                    hero = item
                    heroName.text = item.name
                    Picasso.get()
                        .load(item.image.sm)
                        .error(R.drawable.placeholder_error)
                        .placeholder(R.drawable.placeholder_loading)
                        .into(heroImg)
                }
            }
        }

        fun cancelData() {
            job?.cancel()
        }
    }
}