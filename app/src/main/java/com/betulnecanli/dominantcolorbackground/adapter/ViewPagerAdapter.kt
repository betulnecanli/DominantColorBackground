package com.betulnecanli.dominantcolorbackground.adapter


import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.betulnecanli.dominantcolorbackground.R
import com.betulnecanli.dominantcolorbackground.databinding.ItemBinding
import com.betulnecanli.dominantcolorbackground.model.ResultItem
import com.betulnecanli.dominantcolorbackground.util.linearGradientBackground
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ArtistViewHolder>(){

    companion object{
        val diffCallBack = object : DiffUtil.ItemCallback<ResultItem>(){
            override fun areItemsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
               return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
                return oldItem == newItem
            }

        }
    }


    private val differ = AsyncListDiffer(this, diffCallBack)

    fun submitList(list: List<ResultItem>) = differ.submitList(list)

    inner class ArtistViewHolder(private val binding : ItemBinding) : RecyclerView.ViewHolder(binding.root){

        var dominantColor : Int = 0

        fun bind(item : ResultItem){
            binding.apply {
                artistId.text = item.name

                Glide.with(root)
                    .load(item.url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                           return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            val drawable  = resource as BitmapDrawable
                            val bitmap  = drawable.bitmap
                            Palette.Builder(bitmap).generate{
                                it?.let { palette ->
                                    dominantColor = palette.getDominantColor(
                                        ContextCompat.getColor(
                                            root.context,
                                            R.color.white
                                        )
                                    )

                                    rootLayout.setBackgroundDrawable(rootLayout.linearGradientBackground(dominantColor))

                                }
                            }
                            return false
                        }

                    }).into(artistImage)


            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
       return ArtistViewHolder(
           ItemBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
       val artist = differ.currentList[position]
        holder.bind(artist)
    }

    override fun getItemCount(): Int = differ.currentList.size
}