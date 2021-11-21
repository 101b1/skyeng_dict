package com.ilih.skyengdict.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ilih.skyengdict.R
import com.ilih.skyengdict.domain.dto.SearchResultDto
import kotlinx.android.synthetic.main.search_row.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class SearchResultAdapter(private val dataList: ArrayList<SearchResultDto>, private val listener: Listener): RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    private val diffUtilScope = CoroutineScope(Dispatchers.Default)

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val text: TextView = itemView.findViewById(R.id.textRow)

        fun bind(data: SearchResultDto){
            itemView.textRow.text = data.text
            itemView.setOnClickListener {
                listener.onItemClicked(data)
            }
        }

    }

    inner class SearchDiffUtilCallback(
        private val oldList: ArrayList<SearchResultDto>,
        private val newList: ArrayList<SearchResultDto>,
    ) : DiffUtil.Callback() {

        override fun getOldListSize() =
            oldList.size

        override fun getNewListSize() =
            newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.search_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    private fun calculateDiffAsync(callback: SearchDiffUtilCallback) =
        diffUtilScope.async {
            DiffUtil.calculateDiff(callback)
        }

    fun setData(newList: ArrayList<SearchResultDto>) {
        dataList.clear()
        dataList.addAll(newList)
    }

    fun updateData(newList: ArrayList<SearchResultDto>) {
        val diffUtilCallback = SearchDiffUtilCallback(dataList, newList)
        runBlocking {
            val result = calculateDiffAsync(diffUtilCallback).await()
            setData(newList)
            result.dispatchUpdatesTo(this@SearchResultAdapter)
        }
    }

    interface Listener{
        fun onItemClicked(row: SearchResultDto)
    }

}