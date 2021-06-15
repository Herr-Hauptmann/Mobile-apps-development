package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Account")
data class Account(
    @PrimaryKey @ColumnInfo(name = "uid") val uid: Int,
    @ColumnInfo(name = "acHash") @SerializedName("acHash") var acHash:String,
    @ColumnInfo(name = "lastUpdate") @SerializedName("lastUpdate") var lastUpdate:String) {
}