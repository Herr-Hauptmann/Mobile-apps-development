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

    suspend fun getGrupeZaPredmet(idPredmeta:Int):List<Grupa>{
        var response = ApiAdapter.retrofit.getGrupeZaPredmet(idPredmeta)
        val responseBody = response.body()
        return responseBody!!
    }

    suspend fun getUpisaneGrupe():List<Grupa>{
        var response = ApiAdapter.retrofit.getGrupeZaStudenta()
        val responseBody = response.body()
        return responseBody!!
    }

    suspend fun upisiUGrupu(idGrupa:Int):Boolean{
        var response = ApiAdapter.retrofit.upisiUGrupu(idGrupa)
        val responseBody = response.body()
        if (responseBody != null) {
            return responseBody.dajPoruku().contains("je dodan u grupu");
        }
        return false;
    }


}