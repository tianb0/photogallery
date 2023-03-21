package io.tianb.photogallery.api

import io.tianb.photogallery.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPI {
//    @GET("/")
//    suspend fun fetchContents(): String

//    @GET(
//        "services/rest/?method=flickr.interestingness.getList" +
//                "&api_key=8aa170a449149a2ed4644ab9f20feb57" +
//                "&format=json" +
//                "&nojsoncallback=1" +
//                "&extras=url_s"
//    )
//    suspend fun fetchPhotos(): FlickrResponse

    @GET("services/rest/?method=flickr.interestingness.getList")
    suspend fun fetchPhotos(): FlickrResponse

    @GET("services/rest?method=flickr.photos.search")
    suspend fun searchPhotos(@Query("text") query: String): FlickrResponse
}