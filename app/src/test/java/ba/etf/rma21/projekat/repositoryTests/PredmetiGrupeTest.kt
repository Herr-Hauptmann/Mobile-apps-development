package ba.etf.rma21.projekat.repositoryTests

import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.PredmetRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class PredmetiGrupeTest {
    @Test
    fun getUpisaniPredmeti() {
        assertEquals(
            PredmetRepository.getUpisani()[0].naziv, "OE"
        )
    }
    @Test
    fun getSviPredmeti() {
        assertEquals(
            PredmetRepository.getAll().size, 15
        )
    }
    @Test
    fun getPredmetiByGodina() {
            val lista: List<String> = listOf("IM2", "MLTI", "OE")
            val listaPredmetaPrve: MutableList<String> = emptyList<String>().toMutableList()
            for (predmet in PredmetRepository.getPredmetsByGodina(1))
                listaPredmetaPrve.add(predmet.naziv)

            assertEquals(lista, listaPredmetaPrve.toList())
    }
    @Test
    fun getSveGrupe() {
        assertEquals(
            GrupaRepository.sveGrupe.size , 45
        )
    }
    @Test
    fun getGrupePoPredmetu() {
        val lista: List<String> = listOf("Grupa 1", "Grupa 2", "Grupa 3")
        val listaGrupaOE: MutableList<String> = emptyList<String>().toMutableList()

        for (grupa in GrupaRepository.getGroupsByPredmet("OE"))
            listaGrupaOE.add(grupa.naziv)

        assertEquals(lista, listaGrupaOE.toList())
    }
}