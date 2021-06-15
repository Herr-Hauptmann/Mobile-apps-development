package ba.etf.rma21.projekat.data.repositories
import android.content.Context
import ba.etf.rma21.projekat.data.dao.AccountDAO
import ba.etf.rma21.projekat.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
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
    }
    var acHash: String = "8861bc6f-c600-4d3a-93e8-fd12ef85c979"
    var id : Int = 105;

    private fun setLocalHash(hash : String){
        acHash = hash;
        companionHash = hash;
    }


    suspend fun postaviHash(acHash: String): Boolean {
        return withContext(Dispatchers.IO){
            setLocalHash(acHash)
            val sad = Calendar.getInstance().time
            var sdf = SimpleDateFormat("yyyy-mm-ddThh:mm:ss")
            sdf.format(sad).toString()
            accDao.izbrisiRacune()
            accDao.dodajKorisnickiAcc(Account(1,acHash, sdf.toString()))
            return@withContext true;
        }
    }
}