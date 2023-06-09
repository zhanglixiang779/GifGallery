package com.example.gifgallery.data

import com.example.gifgallery.data.local.LocalGif
import com.example.gifgallery.data.remote.RemoteGif
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultSearchRepositoryTest {

    private val fakeApiService = FakeApiService()
    private val fakeGifDao = FakeGifDao()
    private val searchRepository = DefaultSearchRepository(fakeApiService, fakeGifDao)
    private val cat = LocalGif(0, "100", "cat", "www.cat.com")
    private val dog = LocalGif(0, "200", "dog", "www.dog.com")

    @Test
    fun saveGifsToLocal_verifiesGifsSaved() = runTest {
        // Given
        val gifsToBeAdded = listOf(cat, dog)

        // When
        searchRepository.saveGifsToLocal(gifsToBeAdded.map { LocalGif.toGif(it) })

        // Then
        val actual = fakeGifDao.gifs
        assertEquals(gifsToBeAdded, actual)
    }

    @Test
    fun clearGifsFromLocal_verifiesEmptyGifs() = runTest {
        // Given
        fakeGifDao.gifs.addAll(listOf(cat, dog))

        // When
        searchRepository.clearGifsFromLocal()

        // Then
        val actual = fakeGifDao.gifs
        assertEquals(emptyList<List<LocalGif>>(), actual)
    }

    @Test
    fun getGifsFromLocal_verifiesGifs() = runTest {
        // Given
        fakeGifDao.gifs.addAll(listOf(cat, dog))

        // When
        searchRepository.getGifsFromLocal().collect {
            // Then
            val expected = listOf(cat, dog).map { localGif ->  LocalGif.toGif(localGif) }
            assertEquals(expected, it)
        }
    }

    @Test
    fun getGifsFromRemote_verifiesGifs() = runTest {
        // When
        searchRepository.getGifsFromRemote("cat").collect {
            // Then
            val expected = fakeApiService.gifs.map { remoteGif ->  RemoteGif.toGifLarge(remoteGif) }
            assertEquals(expected, it)
        }
    }
}