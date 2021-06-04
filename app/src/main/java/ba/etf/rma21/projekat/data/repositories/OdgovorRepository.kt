package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Odgovor

object OdgovorRepository {
    suspend fun getOdgovoriKviz(idKviza:Int):List<Odgovor>{
        var response = ApiAdapter.retrofit.dajOdgovore(idKviza)
        val responseBody = response.body()
        return responseBody!!
    }

    suspend fun postaviOdgovorKviz(idKvizTaken:Int,idPitanje:Int,odgovor:Int):Int{
        var response = ApiAdapter.retrofit.unesiOdgovor(idKvizTaken, odgovor, idPitanje, 1);
        if (response.body()!!.odgovoreno == odgovor)
                return 1
        return -1
    }
}