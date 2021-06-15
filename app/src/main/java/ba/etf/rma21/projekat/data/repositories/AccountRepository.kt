package ba.etf.rma21.projekat.data.repositories
import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.dao.AccountDAO
import ba.etf.rma21.projekat.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class AccountRepository(private val accDao : AccountDAO){
    companion object{
        var companionHash : String = "8861bc6f-c600-4d3a-93e8-fd12ef85c979";
        @JvmName("getHash1")
        fun getHash() : String
        {
            return companionHash;
        }
        private lateinit var context:Context
        fun setContext(_context: Context){
            context=_context
        }

        suspend fun postaviHash(acHash: String): Boolean {
            companionHash = acHash
            return withContext(Dispatchers.IO){
                val vrijeme : Date = getCurrentDateTime()
                val formatiranoVrijeme : String = formatirajVrijeme(vrijeme)
                val accDao = AppDatabase.getInstance(context).accountDao()
                val dao1 = AppDatabase.getInstance(context).grupaDao()
                val dao2 = AppDatabase.getInstance(context).kvizDao()
                val dao3 = AppDatabase.getInstance(context).odgovorDao()
                val dao4 = AppDatabase.getInstance(context).pitanjeDao()
                val dao5 = AppDatabase.getInstance(context).predmetDao()
                dao1.izbrisiSveGrupe()
                dao2.izbrisiSveKvizove()
                dao3.izbrisiSveOdgovore()
                dao4.izbrisiSvaPitanja()
                dao5.izbrisiSvePredmete()
                accDao.izbrisiRacune()
                accDao.dodajKorisnickiAcc(Account(1,acHash, formatiranoVrijeme))
                return@withContext true;
            }
        }

        private fun getCurrentDateTime(): Date {
            return Calendar.getInstance().time
        }

        public fun formatirajVrijeme(datum: Date): String {
            val godina = (datum.year + 1900).toString()
            var mjesec = (datum.month+1).toString()
            if (mjesec.length == 1)
                mjesec = "0$mjesec";
            var dan = (datum.day+1).toString()
            if (dan.length == 1)
                dan = "0$dan";
            var sat = datum.hours.toString()
            if (sat.length == 1)
                sat = "0$sat";
            var minut = datum.minutes.toString()
            if (minut.length == 1)
                minut = "0$minut";
            var sekunda = datum.seconds.toString()
            if (sekunda.length == 1)
                sekunda = "0$sekunda";
            return ("$godina-$mjesec-${dan}T$sat:$minut:$sekunda")
        }
    }

    private fun setLocalHash(hash : String){
        companionHash = hash;
    }


    suspend fun staviHash(acHash: String): Boolean {
        return withContext(Dispatchers.IO){
            val vrijeme : Date = getCurrentDateTime()
            val formatiranoVrijeme : String = formatirajVrijeme(vrijeme)
            setLocalHash(acHash)
            accDao.izbrisiRacune()
            accDao.dodajKorisnickiAcc(Account(1,acHash, formatiranoVrijeme))
            return@withContext true;
        }
    }

    suspend fun getAccount() : Account{
        return withContext(Dispatchers.IO) {
            return@withContext accDao.getAccount()
        }
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun formatirajVrijeme(datum: Date): String {
        val godina = (datum.year + 1900).toString()
        var mjesec = (datum.month+1).toString()
        if (mjesec.length == 1)
            mjesec = "0$mjesec";
        var dan = (datum.day+1).toString()
        if (dan.length == 1)
            dan = "0$dan";
        var sat = datum.hours.toString()
        if (sat.length == 1)
            sat = "0$sat";
        var minut = datum.minutes.toString()
        if (minut.length == 1)
            minut = "0$minut";
        var sekunda = datum.seconds.toString()
        if (sekunda.length == 1)
            sekunda = "0$sekunda";
        return ("$godina-$mjesec-${dan}T$sat:$minut:$sekunda")
    }
}