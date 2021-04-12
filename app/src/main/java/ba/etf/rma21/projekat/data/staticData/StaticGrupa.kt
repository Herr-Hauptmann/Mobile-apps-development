package ba.etf.rma21.projekat.data.staticData

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.PredmetRepository

class StaticGrupa {
    companion object {
        fun kreirajGrupe() : List<Grupa> {
            val grupe: MutableList<Grupa> = emptyList<Grupa>().toMutableList()
            //Kreiramo po tri grupe za svaki predmet
            for (predmet in PredmetRepository.getAll()) {
                for (i: Int in 1..3)
                    grupe.add(Grupa("Grupa $i", predmet.naziv))
            }
            return grupe.toList();
        }
    }
}