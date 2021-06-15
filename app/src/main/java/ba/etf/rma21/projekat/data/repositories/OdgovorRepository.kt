package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import android.util.Log
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.OdgovorSlanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

object OdgovorRepository {
    private lateinit var context: Context
    fun setContext(_context:Context){
        context=_context
    }

    suspend fun getOdgovoriKviz(idKviza:Int):List<Odgovor>{
        return withContext(Dispatchers.IO) {
            val poceti = ApiAdapter.retrofit.dajPokusaje().body();
            val kviz = poceti!!.find{it.KvizId == idKviza} ?: return@withContext emptyList<Odgovor>();
            var response = ApiAdapter.retrofit.dajOdgovore(kviz.id)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun postaviOdgovorKviz(idKvizTaken:Int,idPitanje:Int,odgovor:Int):Int {

         val radjeniKvizovi = TakeKvizRepository.getPocetiKvizovi() ?: return -1
         val kvizId = radjeniKvizovi.find{it.id == idKvizTaken}?.KvizId ?: return -1
         val pitanja = PitanjeKvizRepository.getPitanja(kvizId)?:return -1
         val odgovori = getOdgovoriKviz(kvizId)

         var tacni : Int = 0;
         for(pitanje in pitanja){
             if (pitanje.id == idPitanje && pitanje.tacan == odgovor)
                 tacni++
             for (odgovor in odgovori)
                if (odgovor.PitanjeId == pitanje.id && odgovor.odgovoreno == pitanje.tacan)
                    tacni++
         }

         val bodovi : Int = ((tacni.toDouble()/pitanja.size.toDouble()*100.0)).roundToInt()
        withContext(Dispatchers.IO) {
             val nesto = ApiAdapter.retrofit.unesiOdgovor(idKvizTaken, OdgovorSlanje(odgovor, idPitanje, bodovi)).body()

        }
        return bodovi
    }
}