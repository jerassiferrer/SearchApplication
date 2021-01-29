package com.jera.searchapplication.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jera.searchapplication.domain.RepoImpl
import com.jera.searchapplication.util.testObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations.initMocks


@RunWith (AndroidJUnit4::class)
class SearchViewModelUnitTest {

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp(){
        initMocks(this)
        val mockRepo = mock(RepoImpl::class.java)
        searchViewModel = SearchViewModel(mockRepo)
    }


    @Test
    fun init_data() {

        //initial livedata
        val initSearchString = searchViewModel.searchData.testObserver()
        assertEquals(emptyList<String>(), initSearchString.observedValues)


        //method update livedata
        searchViewModel.setSearchData("katy")

        //Observe live data modified
        val searchStringModified = searchViewModel.searchData.testObserver()
        assertEquals(listOf("katy"), searchStringModified.observedValues)

    }
}