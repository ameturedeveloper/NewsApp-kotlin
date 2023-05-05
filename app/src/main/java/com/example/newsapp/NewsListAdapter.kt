package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {

    private var items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
       val curItem = items[position]
        holder.titleView.text = curItem.title
        holder.author.text = curItem.author
        Glide.with(holder.itemView.context).load(curItem.imageUrl).into(holder.image)
    }

    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }

}
class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val titleView : TextView = itemView.findViewById(R.id.titleView)
    val image: ImageView = itemView.findViewById(R.id.imageView)
    val author: TextView = itemView.findViewById(R.id.authorView)
}
interface NewsItemClicked{
    fun onItemClicked(item: News)
}