package ba.etf.rma21.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
object PredmetIGrupaRepository {
    private lateinit var context: Context
    fun setContext(_context: Context){
        context=_context
    }

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

    suspend fun getPredmetByGodina(godina : Int): List<Predmet>{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getPredmeti()
            val responseBody = response.body()
            var predmeti : MutableList<Predmet> = emptyList<Predmet>().toMutableList()
            for (predmet in responseBody!!)
            {
                if (predmet.godina == godina)
                    predmeti.add(predmet)
            }
            return@withContext predmeti!!
        }
    }

}