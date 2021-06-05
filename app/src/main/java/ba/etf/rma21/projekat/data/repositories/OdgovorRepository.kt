package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Odgovor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object OdgovorRepository {
    suspend fun getOdgovoriKviz(idKviza:Int):List<Odgovor>{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.dajOdgovore(idKviza)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun postaviOdgovorKviz(idKvizTaken:Int,idPitanje:Int,odgovor:Int):Int {
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.unesiOdgovor(idKvizTaken, odgovor, idPitanje, 1);
            if (response.body()!!.odgovoreno == odgovor)
                return@withContext 1
            return@withContext -1
        }
    }
}