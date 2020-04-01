package com.tribehired.swf

import com.airasia.swf.module.engineersList.EngineersListViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class EngineersListViewModelUnitTest {

    private val engineersListViewModel =
        EngineersListViewModel()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getEngineer_valid() {
        val engineersListResponse = engineersListViewModel.fetchEngineersList()
        assertNotNull(engineersListResponse)
    }
}
