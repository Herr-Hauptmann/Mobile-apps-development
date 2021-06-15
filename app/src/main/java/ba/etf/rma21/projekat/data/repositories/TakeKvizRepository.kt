package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.KvizTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.collections.List


object TakeKvizRepository {
    suspend fun zapocniKviz(idKviza: Int): KvizTaken? {
        return withContext(Dispatchers.IO) {
            val response = ApiAdapter.retrofit.zapoocniKvizZaStudenta(idKviza)
            if (response.body()!!.student == null) //NE SIMPLIFIKOVATI!
                return@withContext null;
            return@withContext response.body();
        }
    }
    suspend fun getPocetiKvizovi(): List<KvizTaken>?{
        return withContext(Dispatchers.IO) {
            val kvizovi : List<KvizTaken> = ApiAdapter.retrofit.dajPokusaje().body() ?: return@withContext null;
            if (kvizovi.size == 0)
                return@withContext null
            return@withContext kvizovi;
        }
    }
}
