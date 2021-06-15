package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "Kviz")
data class Kviz(
    @PrimaryKey @ColumnInfo(name="id")@SerializedName("id") val id : Int,
    @ColumnInfo(name="naziv")@SerializedName("naziv") var naziv: String,
    @ColumnInfo(name="datumPocetka")@SerializedName("datumPocetak") var datumPocetka: Date,
    @ColumnInfo(name="datumKraj")@SerializedName("datumKraj") var datumKraj: Date?,
    @ColumnInfo(name="trajanje")@SerializedName("trajanje") var trajanje: Int,
    @ColumnInfo(name="predmetId")@SerializedName("predmetId") var predmetId: Int,
    @ColumnInfo(name="predan") var predan: Boolean = false,
) : Comparable<Kviz>{
    override fun compareTo(kviz:Kviz): Int {
        if (kviz.datumPocetka == null || this.datumPocetka == null)
            return 0
        if (this.datumPocetka < kviz.datumPocetka)
            return -1
        if (this.datumPocetka > kviz.datumPocetka)
            return 1
        return 0
    }
}