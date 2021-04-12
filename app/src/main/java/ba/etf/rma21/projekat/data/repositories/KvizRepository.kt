package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Korisnik
import ba.etf.rma21.projekat.data.models.Kviz
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class KvizRepository {

    companion object {
        var kvizovi : MutableList<Kviz> = mutableListOf<Kviz>()
        init {
            for(grupa in GrupaRepository.sveGrupe)
            {
                val brojKviza: Int = Random.nextInt()%5
                val vrijemePocetka: Date = randomDatum(Date(2021, 4, 12), Date(2021, 5, 30))
                val vrijemeKraja: Date = randomDatum(vrijemePocetka, Date(2021, 6, 30))
                val trajanje: Int = Random.nextInt()%90
                //TODO: Dodati logiku u kojoj su neki kvizovi radjeni
                val kviz: Kviz = Kviz("Kviz $brojKviza", grupa.nazivPredmeta, vrijemePocetka, vrijemeKraja, null, trajanje, grupa.naziv, null)
                kvizovi.add(kviz)
            }
        }

        fun getMyKvizes(): List<Kviz> {
            var mojiKvizovi : MutableList<Kviz> = mutableListOf<Kviz>()
            for(kviz in kvizovi)
            {
                if (kviz.nazivGrupe.equals(Korisnik.grupa?.naziv))
                    mojiKvizovi.add(kviz)
            }
            return mojiKvizovi.toList()
        }

        fun getAll(): List<Kviz> {
            return kvizovi.toList()
        }

        fun getDone(): List<Kviz> {
            var gotovi : MutableList<Kviz> = mutableListOf<Kviz>()
            for(kviz in kvizovi)
            {
                if (kviz.datumKraj < getCurrentDateTime())
                    gotovi.add(kviz)
            }
            return gotovi.toList()
        }

        fun getFuture(): List<Kviz> {
            var buduci : MutableList<Kviz> = mutableListOf<Kviz>()
            for(kviz in kvizovi)
            {
                if (kviz.datumPocetka > getCurrentDateTime())
                    buduci.add(kviz)
            }
            return buduci.toList()
        }

        fun getNotTaken(): List<Kviz> {
            var nisuRadjeni : MutableList<Kviz> = mutableListOf<Kviz>()
            for(kviz in kvizovi)
            {
                if (kviz.datumRada == null)
                    nisuRadjeni.add(kviz)
            }
            return nisuRadjeni.toList()
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