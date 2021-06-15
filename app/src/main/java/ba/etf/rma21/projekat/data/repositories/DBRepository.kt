package ba.etf.rma21.projekat.data.repositories
import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.models.UpdateResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class DBRepository {
    companion object{
        private lateinit var context:Context
        fun setContext(_context: Context){
            context=_context
        }
        suspend fun updateNow():Boolean{
            return withContext(Dispatchers.IO) {
                val dao = AppDatabase.getInstance(context).accountDao()
                val repo = AccountRepository(dao);
                val acc = repo.getAccount();
                if (acc == null || acc.lastUpdate == "" || acc.uid == null || acc.lastUpdate == null){
                    dao.dodajKorisnickiAcc(ba.etf.rma21.projekat.data.models.Account(1, AccountRepository.getHash(), AccountRepository.formatirajVrijeme(
                        Calendar.getInstance().time)))
                    return@withContext true;
                }
                val odg : UpdateResponse = ApiAdapter.retrofit.dajUpdate(acc.acHash,acc.lastUpdate).body()!!;

                return@withContext odg.izmjena == "true";
            }

        }
    }
}