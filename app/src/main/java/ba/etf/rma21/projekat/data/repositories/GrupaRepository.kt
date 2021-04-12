package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.staticData.StaticGrupa

class GrupaRepository {
    companion object {
        var sveGrupe : List<Grupa> = StaticGrupa.kreirajGrupe()

        fun getGroupsByPredmet(nazivPredmeta: String): List<Grupa> {
            // TODO: Implementirati
            return emptyList()
        }
    }
}