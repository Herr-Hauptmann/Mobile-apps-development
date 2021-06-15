package ba.etf.rma21.projekat.data.dao

import androidx.room.*
import ba.etf.rma21.projekat.data.models.Account

@Dao
interface AccountDAO {
        @Query("SELECT * FROM Account WHERE uid = 1")
        suspend fun getAccount(): Account

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun dodajKorisnickiAcc(korisnik: Account)

        @Query("DELETE FROM Account")
        suspend fun izbrisiRacune()
}
