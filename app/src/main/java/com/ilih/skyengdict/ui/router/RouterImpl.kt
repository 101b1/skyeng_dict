package com.ilih.skyengdict.ui.router

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.ilih.skyengdict.R
import com.ilih.skyengdict.domain.dto.SearchResultDto
import com.ilih.skyengdict.ui.meaning.MEANING
import com.ilih.skyengdict.ui.meaning.MeaningFragment


class RouterImpl(private val fragmentManager: FragmentManager): Router {
    override fun navToMeaning(searchResultDto: SearchResultDto) {
        val bundle = Bundle()
        bundle.putParcelable(MEANING, searchResultDto)
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MeaningFragment::class.java, bundle)
            .addToBackStack(null)
            .commit()
    }

    override fun navBack() {
        fragmentManager.popBackStack()
    }
}