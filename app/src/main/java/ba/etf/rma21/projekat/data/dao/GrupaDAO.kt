package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Account
import ba.etf.rma21.projekat.data.models.Grupa

@Dao
interface GrupaDAO {
    @Query("SELECT * FROM Grupa")
    suspend fun getSveGrupe(): List<Grupa>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun dodajGrupu(grupa : Grupa)

    @Query("DELETE FROM Grupa")
    suspend fun izbrisiSveGrupe()
}