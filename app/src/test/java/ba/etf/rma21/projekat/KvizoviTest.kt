package ba.etf.rma21.projekat

import ba.etf.rma21.projekat.data.repositories.KvizRepository
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class KvizoviTest {
    @Test
    fun getMyKvizes() {
        org.junit.jupiter.api.assertAll(
            Assert.assertEquals(KvizRepository.getMyKvizes().size,1),
            Assert.assertEquals(KvizRepository.getMyKvizes()[0].nazivPredmeta, "OE")
        )
    }

    fun getAllKvizes() {
        org.junit.jupiter.api.assertAll(
            KvizRepository.getAll().size, 45
        )
    }

    fun getDoneKvizes() {
        org.junit.jupiter.api.assertAll(
            KvizRepository.getDone().size, 0
        )
    }
}