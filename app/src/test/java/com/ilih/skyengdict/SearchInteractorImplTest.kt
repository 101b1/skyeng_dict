package com.ilih.skyengdict

import com.ilih.skyengdict.data.SearchService
import com.ilih.skyengdict.domain.dto.MeaningDto
import com.ilih.skyengdict.domain.dto.SearchResultDto
import com.ilih.skyengdict.domain.dto.SearchSuccess
import com.ilih.skyengdict.domain.dto.TranslationDto
import com.ilih.skyengdict.domain.interactor.SearchInteractorImpl
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class SearchInteractorImplTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val rxRule = RxRule()

    @Mock
    lateinit var service: SearchService

    lateinit var interactor: SearchInteractorImpl

    @Before
    fun setUp() {
        interactor = SearchInteractorImpl(service)
    }

    @Test
    fun testReturnSearchSuccess() {
        val serviceResult = listOf (
            SearchResultDto(
                id = 1,
                text = "apple",
                meanings = listOf(
                    MeaningDto(
                        id = 321,
                        translation = TranslationDto(
                            text = "яблоко",
                            note = ""
                        )
                    )
                )
            )
        )
        val expectedResult = SearchSuccess(serviceResult)
        Mockito.`when`(service.search("apple", 1))
            .thenReturn(Single.just(serviceResult))

        interactor.searchWord("apple", 1)

        interactor.getObservable()
            .test()
            .assertNoErrors()
            .assertResult(expectedResult)
    }

}