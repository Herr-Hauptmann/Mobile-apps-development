package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.KvizTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

object TakeKvizRepository {
    suspend fun zapocniKviz(idKviza: Int): KvizTaken? {
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.zapoocniKvizZaStudenta(idKviza)
            if (response.body()!!.student == null) //NE SIMPLIFIKOVATI!
                return@withContext null;
            return@withContext response.body();
        }
    }
    suspend fun getPocetiKvizovi(): List<KvizTaken>?{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.dajPokusaje()
            if (response.body()!!.isEmpty())
                return@withContext null
            return@withContext response.body();
        }
    }
}
