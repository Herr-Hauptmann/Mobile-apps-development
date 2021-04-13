package ba.etf.rma21.projekat

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import ba.etf.rma21.projekat.data.repositories.PredmetRepository
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class PredmetiGrupeTest {
    @Test
    fun getUpisaniPredmeti() {
        org.junit.jupiter.api.assertEquals(
            PredmetRepository.getUpisani()[0].naziv, "OE"
        )
    }
    @Test
    fun getSviPredmeti() {
        org.junit.jupiter.api.assertEquals(
            PredmetRepository.getAll().size, 15
        )
    }
    @Test
    fun getPredmetiByGodina() {
        org.junit.jupiter.api.assertAll(
            Assert.assertEquals(PredmetRepository.getPredmetsByGodina(1).size,3),
            Assert.assertEquals(PredmetRepository.getPredmetsByGodina(1)[0].naziv, "IM2"),
            Assert.assertEquals(PredmetRepository.getPredmetsByGodina(1)[1].naziv, "MLTI"),
            Assert.assertEquals(PredmetRepository.getPredmetsByGodina(1)[2].naziv, "OE")
        )
    }

    @Test
    fun getSveGrupe() {
        org.junit.jupiter.api.assertEquals(
            GrupaRepository.sveGrupe.size , 45
        )
    }

    @Test
    fun getGrupePoPredmetu() {
        org.junit.jupiter.api.assertAll(
            Assert.assertEquals(GrupaRepository.getGroupsByPredmet("OE").size,3),
            Assert.assertEquals(GrupaRepository.getGroupsByPredmet("OE")[0].naziv, "Grupa 1"),
            Assert.assertEquals(GrupaRepository.getGroupsByPredmet("OE")[1].naziv, "Grupa 2"),
            Assert.assertEquals(GrupaRepository.getGroupsByPredmet("OE")[2].naziv, "Grupa 3")
        )
    }



}