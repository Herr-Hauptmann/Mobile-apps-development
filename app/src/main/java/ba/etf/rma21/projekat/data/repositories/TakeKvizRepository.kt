package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.KvizTaken

object TakeKvizRepository {
    suspend fun zapocniKviz(idKviza: Int): KvizTaken? {
        var response = ApiAdapter.retrofit.zapoocniKvizZaStudenta(idKviza)
        if (response.body()!!.student==null) //NE SIMPLIFIKOVATI!
            return null;
        return response.body();
    }
    suspend fun getPocetiKvizovi():List<KvizTaken>?{
        var response = ApiAdapter.retrofit.dajPokusaje()
        if (response.body()!!.isEmpty())
            return null
        return response.body();
    }
}
