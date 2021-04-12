package ba.etf.rma21.projekat.data.staticData

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import java.util.*
import kotlin.random.Random

class StaticKviz {
    companion object {
        fun generisiKvizove() : List<Kviz>
        {
            var kvizovi : MutableList<Kviz> = mutableListOf<Kviz>()
            var brojKviza = 1;
            for(grupa in GrupaRepository.sveGrupe)
            {
                brojKviza++
                val vrijemePocetka: Date =
                    KvizRepository.randomDatum(Date(2021, 4, 12), Date(2021, 5, 30))
                val vrijemeKraja: Date =
                    KvizRepository.randomDatum(vrijemePocetka, Date(2021, 6, 30))
                val trajanje: Int = Random.nextInt()%90
                //TODO: Dodati logiku u kojoj su neki kvizovi radjeni
                val kviz: Kviz = Kviz("Kviz $brojKviza", grupa.nazivPredmeta, vrijemePocetka, vrijemeKraja, null, trajanje, grupa.naziv, null)
                kvizovi.add(kviz)
                if (brojKviza == 4)
                    brojKviza = 0
            }
            return kvizovi.toList();
        }
    }
}