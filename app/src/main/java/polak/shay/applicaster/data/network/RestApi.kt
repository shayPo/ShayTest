package polak.shay.applicaster.data.network

import polak.shay.applicaster.model.LinkPosts
import polak.shay.applicaster.model.Post
import polak.shay.applicaster.model.VideoPosts
import retrofit2.Call
import retrofit2.http.GET

interface RestApi
{
    @GET("link_json.json")
    fun getPostsWithLinks() : Call<Post>

    @GET("video_json.json")
    fun getPostsWithVideo() : Call<Post>
}