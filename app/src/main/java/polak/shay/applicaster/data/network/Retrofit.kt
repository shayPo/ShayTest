package polak.shay.applicaster.data.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit
{
    private val mUrl = "http://assets-production.applicaster.com/applicaster-employees/israel_team/Elad/assignment/"

    fun getRetrofit(): Retrofit {
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(mUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}