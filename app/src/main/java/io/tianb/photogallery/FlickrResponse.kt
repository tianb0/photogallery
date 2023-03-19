package io.tianb.photogallery

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlickrResponse(
    val photos: PhotoResponse
)