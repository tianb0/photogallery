package io.tianb.photogallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel() {

    private val photoRepository = PhotoRepository()
    private val preferencesRepository = PreferencesRepository.get()

//    private val _galleryItems: MutableStateFlow<List<GalleryItem>> =
//        MutableStateFlow(emptyList())
//    val galleryItems: StateFlow<List<GalleryItem>>
//        get() = _galleryItems.asStateFlow()

    private val _uiState: MutableStateFlow<PhotoGalleryUiState> =
        MutableStateFlow(PhotoGalleryUiState())
    val uiState: StateFlow<PhotoGalleryUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepository.storedQuery.collectLatest { storedQuery ->
                try {
                    val items = fetchGalleryItems(storedQuery)

                    _uiState.update {  oldState ->
                        oldState.copy(
                            images = items,
                            query = storedQuery
                        )
                    }
                } catch (ex: java.lang.Exception) {

                }
            }
        }
    }

//    init {
//        viewModelScope.launch {
//            preferencesRepository.storedQuery.collectLatest { storedQuery ->
//                try {
////                val items = photoRepository.fetchPhotos()
//                    val items = fetchGalleryItems(storedQuery)
//                    Log.d(TAG, "Items received: $items")
//                    _galleryItems.value = items
//                } catch (ex: java.lang.Exception) {
//                    Log.e(TAG, "Failed to fetch gallery items", ex)
//                }
//            }
//        }
//    }

    fun setQuery(query: String) {
//        viewModelScope.launch {
//            _galleryItems.value = fetchGalleryItems(query)
//        }
        viewModelScope.launch {
            preferencesRepository.setStoredQuery(query)
        }
    }

    private suspend fun fetchGalleryItems(query: String): List<GalleryItem> {
        return if (query.isNotEmpty()) {
            photoRepository.searchPhotos(query)
        } else {
            photoRepository.fetchPhotos()
        }
    }
}

data class PhotoGalleryUiState(
    val images: List<GalleryItem> = listOf(),
    val query: String = "",
)