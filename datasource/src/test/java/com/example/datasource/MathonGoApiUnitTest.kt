package com.example.datasource

import com.example.datasource.remote.ApiClient
import com.example.datasource.remote.apis.MathonGoApi
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MathonGoApiUnitTest {

    private val api = ApiClient().retrofit.create(MathonGoApi::class.java)

    @Test
    fun `GET - All Questions`() = runBlocking {
        val response = api.fetchQuestionsList()
        assertNotNull(response.body()!!)
    }
}