package ba.etf.rma21.projekat.repositoryTests

import ba.etf.rma21.projekat.data.repositories.KvizRepository
import org.junit.Assert.*
import org.junit.Test

class KvizoviTest {
    @Test
    fun getMyKvizes() {
        val kvizovi: List<String> = listOf("OE")
        val mojiKvizovi: MutableList<String> = emptyList<String>().toMutableList()

        for (kviz in KvizRepository.getMyKvizes())
            mojiKvizovi.add(kviz.nazivPredmeta)

        assertEquals(kvizovi, mojiKvizovi.toList())
    }

    @Test
    fun getAllKvizes() {
        assertEquals(
           45 , KvizRepository.getAll().size
        )
    }
    @Test
    fun getDoneKvizes() {
        assertEquals(
            0, KvizRepository.getDone().size
        )
    }
}