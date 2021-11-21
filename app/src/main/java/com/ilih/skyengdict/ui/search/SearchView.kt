package com.ilih.skyengdict.ui.search

interface SearchView {

    fun initViews()
    fun setListeners()
    fun onFinishInflate(listener: Listener)

    interface Listener{
        fun search(query: String)
    }
}