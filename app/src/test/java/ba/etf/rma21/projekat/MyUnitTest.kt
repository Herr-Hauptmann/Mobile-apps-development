package ba.etf.rma21.projekat


import ba.etf.rma21.projekat.data.models.*
import ba.etf.rma21.projekat.data.repositories.*
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.net.URL


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MyUnitTest {
    suspend fun obrisi(){
        var client: OkHttpClient = OkHttpClient()
        var builder: Request.Builder = Request.Builder()
            .url(URL(ApiConfig.baseURL + "/student/" + AccountRepository.acHash + "/upisugrupeipokusaji"))
            .delete()
        var request: Request = builder.build()
        withContext(Dispatchers.IO) {
            var response: Response = client.newCall(request).execute()
            var odgovor: String = response.body().toString()
        }
    }


    @Test
    fun a0_pripremiPocetak() = runBlocking {
        obrisi()
    }

    @Test
    fun provjeraKvizIPredmetVeze() = runBlocking{
        val kvizovi = KvizRepository.getAll()
        val predmet = PredmetIGrupaRepository.getPredmetById(kvizovi[kvizovi.size-1].predmetId)
        assertNotNull(predmet)
    }

    @Test
    fun a1_dajPrazneZapoceteKvizove() = runBlocking {
        var kvizovi = TakeKvizRepository.getPocetiKvizovi()
        assertNull(kvizovi)
    }


    @Test
    fun a2_zapocniUpisaniKviz() = runBlocking {
        var kviz = TakeKvizRepository.zapocniKviz(-1);
        assertNull(kviz)
    }

}
