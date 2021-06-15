package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Grupa")
data class Grupa(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id") val id : Int,
    @ColumnInfo(name = "naziv") @SerializedName("naziv") val naziv : String,
    @ColumnInfo(name = "PredmetId") @SerializedName("PredmetId") val PredmetId : Int){
    override operator fun equals(other: Any?): Boolean {
        if (other == null)
            return false;
        if (this::class != other!!::class)
            return false
        return this.id == (other as Grupa).id && this.naziv == (other as Grupa).naziv
    }
}