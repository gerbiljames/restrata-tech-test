package com.example.restrata.terminal.nfc.activity.nfc

import android.nfc.Tag
import com.example.restrata.terminal.nfc.manager.CardManager
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NFCViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testInitialNullValue() {
        val mockCardManager = mockk<CardManager>()

        val sut = NFCViewModel(mockCardManager, testDispatcher)

        assertNull(sut.card)
    }

    @Test
    fun testOnNewTag() {

        val mockTag = mockk<Tag>()
        every { mockTag.id } returns ByteArray(4) { it.toByte() }

        val mockCardManager = mockk<CardManager>()
        coEvery { mockCardManager.cardExists(any()) } returns true
        coEvery { mockCardManager.addCard(any()) } returns Unit

        val sut = NFCViewModel(mockCardManager, testDispatcher)

        runTest {
            sut.onNewTag(mockTag)
        }

        assertEquals("00010203", sut.card?.first?.id)
        assertEquals(true, sut.card?.second)
    }
}