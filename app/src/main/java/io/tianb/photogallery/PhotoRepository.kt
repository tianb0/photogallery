package io.tianb.photogallery

import io.tianb.photogallery.api.FlickrAPI
import io.tianb.photogallery.api.PhotoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class PhotoRepository {
    private val flickrAPI: FlickrAPI

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(PhotoInterceptor())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

        flickrAPI = retrofit.create()
    }


    suspend fun fetchPhotos(): List<GalleryItem> =
        flickrAPI.fetchPhotos().photos.galleryItems

    suspend fun searchPhotos(query: String): List<GalleryItem> =
        flickrAPI.searchPhotos(query).photos.galleryItems

//    init {
//        val retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl("https://www.flickr.com/")
////            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//        flickrAPI = retrofit.create()
//    }

//    suspend fun fetchContents() = flickrAPI.fetchContents()
//    suspend fun fetchPhotos() = flickrAPI.fetchPhotos()
}
