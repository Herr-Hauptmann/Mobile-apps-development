package ba.etf.rma21.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.dao.AccountDAO
import ba.etf.rma21.projekat.data.dao.OdgovorDAO
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.OdgovorSlanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Appendable
import java.lang.Exception
import kotlin.math.roundToInt

data class OdgovorRepository(private val dao : OdgovorDAO) {
    companion object{
        @SuppressLint("StaticFieldLeak")
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

        suspend fun postaviOdgovorKviz(idKvizTaken:Int, idPitanje: Int, odgovor: Int) : Int
        {
            var uspjelo = true;
            withContext(Dispatchers.IO) {
                try {
                    val dao = AppDatabase.getInstance(context).odgovorDao()
                    var posljednji: Int? = dao.getNajveciId()
                    if (posljednji == null)
                            posljednji = 0;
                    else {
                        if (Odgovor(posljednji, idKvizTaken, idPitanje, odgovor) != dao.getOdgovorById(posljednji))
                            dao.dodajOdgovor(Odgovor(posljednji + 1, idKvizTaken, idPitanje, odgovor))
                    }
                    if (posljednji == 0)
                        dao.dodajOdgovor(Odgovor(posljednji + 1, idKvizTaken, idPitanje, odgovor))

                } catch (e: Exception)
                {
                    uspjelo = false;
                }
            }
            if (!uspjelo)
                return -1;

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

            return ((tacni.toDouble()/pitanja.size.toDouble()*100.0)).roundToInt()
        }
        suspend fun predajOdgovore(idKviz : Int):Int {
            return 1;
        }
    }
}