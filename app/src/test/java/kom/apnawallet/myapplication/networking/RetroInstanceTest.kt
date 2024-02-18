package kom.apnawallet.myapplication.networking

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetroInstanceTest{

    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: ApiService

    @Before
    fun setUp(){
        mockWebServer = MockWebServer()
        apiService =  Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    //Test to check if the data fetching logic works properly
    @Test
    fun testFetchEmptyProducts() = runBlocking {
        // Prepare MockResponse with expected JSON structure
        val emptyProductResponse = """
            {
                "products": [],
                "total": 0,
                "skip": 0,
                "limit": 30
            }
        """
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(emptyProductResponse)
        mockWebServer.enqueue(mockResponse)

        // Make the API call and verify response
        val response = apiService.getProducts()
        mockWebServer.takeRequest()

        Assert.assertTrue(response.isSuccessful)
        Assert.assertEquals(0, response.body()?.products?.size)
    }


    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}
