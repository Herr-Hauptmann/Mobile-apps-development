package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Korisnik
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.staticData.StaticKviz
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class KvizRepository {

    companion object {
        var kvizovi : List<Kviz> = StaticKviz.generisiKvizove()

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
               if (kviz.osvojeniBodovi != null)
                   gotovi.add(kviz)
            }
            return gotovi.toList()
        }

        fun getFuture(): List<Kviz> {
            var buduci : MutableList<Kviz> = mutableListOf<Kviz>()
            for(kviz in kvizovi)
            {
                if (kviz.datumPocetka > StaticKviz.getCurrentDateTime())
                    buduci.add(kviz)
            }
            return buduci.toList()
        }

        fun getNotTaken(): List<Kviz> {
            var nisuRadjeni : MutableList<Kviz> = mutableListOf<Kviz>()
            for(kviz in kvizovi)
            {
                if (kviz.datumRada == null && kviz.datumKraj < StaticKviz.getCurrentDateTime())
                    nisuRadjeni.add(kviz)
            }
            return nisuRadjeni.toList()
        }


    }
}