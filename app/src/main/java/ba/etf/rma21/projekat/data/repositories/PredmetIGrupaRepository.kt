package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet

object PredmetIGrupaRepository {
    suspend fun getPredmeti():List<Predmet>{
        var response = ApiAdapter.retrofit.getPredmeti()
        val responseBody = response.body()
        return responseBody!!
    }

    suspend fun getGrupe():List<Grupa>{
        var response = ApiAdapter.retrofit.getGrupe()
        val responseBody = response.body()
        return responseBody!!
    }
}