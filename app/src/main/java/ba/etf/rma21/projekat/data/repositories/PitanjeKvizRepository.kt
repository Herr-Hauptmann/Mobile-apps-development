package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje

object PitanjeKvizRepository {
    suspend fun getPitanja(idKviza:Int):List<Pitanje>{
        return ApiAdapter.retrofit.dajPitanja(idKviza).body()!!
    }
}