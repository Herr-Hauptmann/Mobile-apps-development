package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
}