package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.models.Predmet

@Dao
interface PitanjeDAO {
    @Query("SELECT * FROM Pitanje")
    suspend fun getSvaPitanja() : List<Pitanje>

    @Query("SELECT * FROM Pitanje WHERE id = :pitanjeId")
    suspend fun getPitanjeById(pitanjeId : Int) : Pitanje

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun dodajPitanje(pitanje : Pitanje)

    @Query("DELETE FROM Pitanje")
    suspend fun izbrisiSvaPitanja()
}