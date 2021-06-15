package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.Pitanje

@Dao
interface OdgovorDAO {
    @Query("SELECT * FROM Odgovor")
    suspend fun getSviOdgovori() : List<Odgovor>

    @Query("SELECT max(id) FROM Odgovor")
    suspend fun getNajveciId() : Int

    @Query("SELECT * FROM Odgovor WHERE id = :odgovorId")
    suspend fun getOdgovorById(odgovorId : Int) : Odgovor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun dodajOdgovor(odgovor : Odgovor)

    @Query("DELETE FROM Odgovor")
    suspend fun izbrisiSveOdgovore()
}