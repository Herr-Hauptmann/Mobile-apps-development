package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet

@Dao
interface PredmetDAO {
    @Query("SELECT * FROM Predmet")
    suspend fun getSviPredmeti() : List<Predmet>

    @Query("SELECT * FROM Predmet WHERE id = :predmetId")
    suspend fun getPredmedById(predmetId : Int) : Predmet

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun dodajGrupu(predmet : Predmet)

    @Query("DELETE FROM Predmet")
    suspend fun izbrisiSvePredmete()
}