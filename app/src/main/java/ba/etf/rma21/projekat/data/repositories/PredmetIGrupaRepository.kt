package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PredmetIGrupaRepository {
    suspend fun getPredmeti():List<Predmet>{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getPredmeti()
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun getPredmetById(id : Int):Predmet{
        return withContext(Dispatchers.IO) {
            val response = ApiAdapter.retrofit.getPredmet(id)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun getGrupe():List<Grupa>{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getGrupe()
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun getGrupeZaPredmet(idPredmeta:Int):List<Grupa>{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getGrupeZaPredmet(idPredmeta)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun getUpisaneGrupe():List<Grupa>{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getGrupeZaStudenta()
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun upisiUGrupu(idGrupa:Int):Boolean{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.upisiUGrupu(idGrupa)
            val responseBody = response.body()
            if (responseBody != null) {
                return@withContext responseBody.dajPoruku().contains("je dodan u grupu");
            }
            return@withContext false;
        }
    }

    suspend fun getPredmetByKvizId(idKviz : Int):Predmet?{
        return withContext(Dispatchers.IO) {
            var grupa = ApiAdapter.retrofit.getGrupeByKvizId(idKviz).body() ?: return@withContext null;
            var predmet = ApiAdapter.retrofit.getPredmet(grupa[grupa.size-1].PredmetId).body() ?: return@withContext null;
            return@withContext predmet
        }
    }


}