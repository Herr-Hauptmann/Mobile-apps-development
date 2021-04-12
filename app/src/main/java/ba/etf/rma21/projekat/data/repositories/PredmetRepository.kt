package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Korisnik
import ba.etf.rma21.projekat.data.models.Korisnik.predmeti
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.staticData.StaticPredmeti

class PredmetRepository {
    companion object {
        fun getUpisani(): List<Predmet> {
            return Korisnik.predmeti;
        }

        fun getAll(): List<Predmet> {
            val p = StaticPredmeti();
            var predmeti : MutableList<Predmet> = emptyList<Predmet>().toMutableList()
            for (i in 1..3)
            {
                predmeti.addAll(p.dajPredmete(i))
            }
            return predmeti.toList();
        }
    }

}