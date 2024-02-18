package kom.apnawallet.myapplication.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//singleton instance of the apiService interface
object RetroInstance {

    val baseUrl = "https://dummyjson.com"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}