package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Predmet

@Dao
interface KvizDAO {
    @Query("SELECT * FROM Kviz")
    suspend fun getSviKvizovi() : List<Kviz>

    @Query("SELECT * FROM Kviz WHERE id = :kvizId")
    suspend fun getKvizoviById(kvizId : Int) : Kviz

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun dodajKviz(kviz : Kviz)

    @Query("DELETE FROM Kviz")
    suspend fun izbrisiSveKvizove()
}