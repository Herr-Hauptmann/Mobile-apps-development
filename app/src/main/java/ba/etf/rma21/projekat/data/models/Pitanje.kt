package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import ba.etf.rma21.projekat.data.repositories.ApiAdapter
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class Pitanje(
    @PrimaryKey @ColumnInfo(name="id") @SerializedName("id") var id : Int,
    @ColumnInfo(name="naziv") @SerializedName("naziv") var naziv : String,
    @ColumnInfo(name="tekstPitanja")@SerializedName("tekstPitanja") var tekstPitanja : String,
    @ColumnInfo(name="opcije")@SerializedName("opcije") val opcije: List<String>,
    @ColumnInfo(name="tacan")@SerializedName("tacan") val tacan : Int){
}