package com.ilih.skyengdict.ui

interface SearchView {

    interface Listener{
        fun search(query: String)
    }
}