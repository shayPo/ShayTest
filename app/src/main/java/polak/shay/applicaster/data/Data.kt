package polak.shay.applicaster.data

import android.util.Log
import polak.shay.applicaster.data.network.RestApi
import polak.shay.applicaster.data.network.Retrofit
import polak.shay.applicaster.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

enum class PostType { ALL,LINK,VIDEO }


class Data private constructor() : Callback<Post>, Observable()
{
    private val mRetrofitInterface by lazy{
        Retrofit().getRetrofit().create(RestApi::class.java)
    }

    fun loadPost(type : PostType, observer: Observer){
        addObserver(observer)
        when(type)
        {
            PostType.LINK -> { mRetrofitInterface.getPostsWithLinks().enqueue(this)}
            PostType.VIDEO -> { mRetrofitInterface.getPostsWithVideo().enqueue(this)}
            else -> {
                mRetrofitInterface.getPostsWithLinks().enqueue(this)
                mRetrofitInterface.getPostsWithVideo().enqueue(this)
            }
        }
    }

    override fun onFailure(call: Call<Post>, t: Throwable) {
        //deleteObservers()
        Log.d("call_","onFailure")
    }

    override fun onResponse(call: Call<Post>, response: Response<Post>) {
        setChanged()
        notifyObservers(response.body())
        Log.d("call_","onResponse")
    }

    companion object{
        private val mInstance : Data by lazy { Data() }

        fun getInstance() : Data { return mInstance }
    }
}