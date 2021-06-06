package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

object KvizRepository {
    suspend fun getAll():List<Kviz>{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getKvizovi()
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }
    suspend fun getById(id:Int):Kviz{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getKvizById(id)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }
    suspend fun getUpisani():List<Kviz>{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getGrupeZaStudenta()
            val grupe = response.body()
            var kvizovi: MutableList<Kviz> = mutableListOf()
            for (grupa in grupe!!) {
                ApiAdapter.retrofit.getKvizByGrupaId(grupa.id).body()?.let { kvizovi.addAll(it) }
            }
            return@withContext kvizovi
        }
    }

    suspend fun getBuduci():List<Kviz>{
        return withContext(Dispatchers.IO) {
            val kvizovi = getUpisani()
            var buduci : MutableList<Kviz> = emptyList<Kviz>().toMutableList()
            for (kviz in kvizovi)
            {
                if (kviz.datumPocetka > getCurrentDateTime())
                    buduci.add(kviz)
            }
            return@withContext buduci!!
        }
    }

    suspend fun getRadjeni():List<Kviz>{
        return withContext(Dispatchers.IO) {
            var radjeni : MutableList<Kviz> = emptyList<Kviz>().toMutableList()
            val pokusaji = ApiAdapter.retrofit.dajPokusaje().body() ?: return@withContext emptyList<Kviz>()
            for (pokusaj in pokusaji) {
                var novi = ApiAdapter.retrofit.getKvizById(pokusaj.KvizId).body()!!
                radjeni.add(novi)
            }
            return@withContext radjeni
        }
    }

    suspend fun getNeRadjeni():List<Kviz> {
        return withContext(Dispatchers.IO) {
            var neradjeni : MutableList<Kviz> = emptyList<Kviz>().toMutableList();
            val kvizovi = getUpisani()
            var prosli : MutableList<Kviz> = emptyList<Kviz>().toMutableList()
            for (kviz in kvizovi)
            {
                if (kviz.datumKraj==null || kviz.datumKraj!! < getCurrentDateTime())
                    prosli.add(kviz)
            }
            val pokusaji = ApiAdapter.retrofit.dajPokusaje().body() ?: return@withContext emptyList<Kviz>()
            for (kviz in prosli)
            {
                var bio = false
                for (pokusaj in pokusaji){
                    if (kviz.id == pokusaj.KvizId)
                        bio = true
                }
                if (!bio) {
                    neradjeni.add(kviz)
                    bio = false
                }
            }
            return@withContext neradjeni
        }
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}