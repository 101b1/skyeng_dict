package com.ilih.skyengdict.domain.data

import com.ilih.skyengdict.domain.dto.SearchResultDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search")
    fun search(
        @Query("search")
        query: String,
        @Query("page")
        pageNumber: Int,
        @Query("pageSize")
        pageSize: Int = 10
    ): Single<List<SearchResultDto>>
}