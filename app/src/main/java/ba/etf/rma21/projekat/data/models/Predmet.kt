package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Predmet")
data class Predmet(
    @PrimaryKey @ColumnInfo(name="id") @SerializedName("id") val id : Int,
    @ColumnInfo(name="naziv") @SerializedName("naziv") val naziv: String,
    @ColumnInfo(name="godina") @SerializedName("godina") val godina: Int) {
    override operator fun equals(other: Any?): Boolean {
        if (other == null)
            return false;
        if (this::class != other!!::class)
            return false
        return this.naziv == (other as Predmet).naziv
    }
}