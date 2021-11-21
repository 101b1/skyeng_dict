package com.ilih.skyengdict.ui.meaning

import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilih.skyengdict.databinding.FragmentMeaningBinding
import com.ilih.skyengdict.di.MainComponent
import com.ilih.skyengdict.domain.dto.SearchResultDto
import com.squareup.picasso.Picasso
import java.lang.Exception

class MeaningViewImpl(
    private val meaning: SearchResultDto,
    private val binding: FragmentMeaningBinding,
    private val mainComponent: MainComponent
): MeaningView {

    init{
        val picassoBuilder = Picasso.Builder(mainComponent.getContext())
        picassoBuilder.listener(object : Picasso.Listener{
            override fun onImageLoadFailed(picasso: Picasso?, uri: Uri?, exception: Exception?) {
                Log.d("Skyeng", exception?.stackTraceToString() ?: "Error")
            }
        })
        binding.textWord.text = meaning.text
        binding.recyclerMeanings.layoutManager = LinearLayoutManager(mainComponent.getContext(), RecyclerView.VERTICAL, false)
        binding.recyclerMeanings.adapter = MeaningsAdapter(ArrayList(meaning.meanings), picassoBuilder)
    }

}