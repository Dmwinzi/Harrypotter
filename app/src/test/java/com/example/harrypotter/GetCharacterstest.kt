package com.example.harrypotter

import com.example.harrypotter.Domain.Repository.Repository
import com.example.harrypotter.Util.Constants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Instant

@RunWith(JUnit4::class)
class GetCharacterstest {

    private lateinit var server : MockWebServer
    private lateinit var repository: Repository

    @Before
    fun setup(){
        server  = MockWebServer()
        var retrofit  = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        repository  = retrofit.create(Repository::class.java)
    }


    @Test
    fun testGetcharacters()  = runBlocking {
        var  response  = MockResponse()
        var content = Helper.readFile("/response.json")
        response.setResponseCode(200)
        response.setBody(content)
        server.enqueue(response)

        var characters = repository.getcharacters()

        server.takeRequest()

        Assert.assertEquals(true,characters.body()!!.isNotEmpty())
        Assert.assertEquals(2,characters.body()!!.size)

    }


    @After
    fun closeserver(){
       server.shutdown()
    }

}