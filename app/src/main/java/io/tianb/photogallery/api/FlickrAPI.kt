package io.tianb.photogallery.api

import io.tianb.photogallery.FlickrResponse
import retrofit2.http.GET

interface FlickrAPI {
//    @GET("/")
//    suspend fun fetchContents(): String

    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=8aa170a449149a2ed4644ab9f20feb57" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}