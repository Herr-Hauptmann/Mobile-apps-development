package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PitanjeKvizRepository {
    suspend fun getPitanja(idKviza:Int): List<Pitanje>? {
        return withContext(Dispatchers.IO) {
            val tijelo = ApiAdapter.retrofit.dajPitanja(idKviza).body()
            return@withContext tijelo
        }
    }

}