package com.ilih.skyengdict.ui.meaning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilih.skyengdict.R
import com.ilih.skyengdict.domain.dto.MeaningDto
import com.squareup.picasso.Picasso

class MeaningsAdapter(
    private val dataList: ArrayList<MeaningDto>,
    private val picassoBuilder: Picasso.Builder
) : RecyclerView.Adapter<MeaningsAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView: TextView = itemView.findViewById(R.id.textWord)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(item: MeaningDto) {
            textView.text = item.translation.text
            picassoBuilder.build()
                .load("https:"+item.imageUrl)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_time)
                .fit()
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.meaning_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

}