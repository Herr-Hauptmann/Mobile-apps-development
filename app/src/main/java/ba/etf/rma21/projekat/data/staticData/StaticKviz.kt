package ba.etf.rma21.projekat.data.staticData

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class StaticKviz {
    companion object {
        fun generisiKvizove() : List<Kviz>
        {
            var kvizovi : MutableList<Kviz> = mutableListOf<Kviz>()
            for(grupa in GrupaRepository.sveGrupe)
            {
                val brojKviza: Int = Random.nextInt(1, 5)
                val vrijemePocetka: Date =
                    randomDatum(Date(121, 0, 1), Date(121, 4, 30))
                val vrijemeKraja: Date =
                    randomDatum(vrijemePocetka, Date(121, 5, 30))
                val trajanje: Int = (Random.nextInt(10, 90)/5)*5
                //TODO: Dodati logiku u kojoj su neki kvizovi radjeni
                val kviz: Kviz = Kviz("Kviz $brojKviza", grupa.nazivPredmeta, vrijemePocetka, vrijemeKraja, null, trajanje, grupa.naziv, null)
                kvizovi.add(kviz)
            }
            return kvizovi.toList();
        }
        fun randomDatum(poslije: Date, prije: Date): Date {
            val startMillis: Long = poslije.getTime()
            val endMillis: Long = prije.getTime()
            val randomMillisSinceEpoch: Long = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis)
            return Date(randomMillisSinceEpoch)
        }
        fun getCurrentDateTime(): Date {
            return Calendar.getInstance().time
        }
    }


}