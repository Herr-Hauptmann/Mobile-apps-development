package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class KvizTaken(
    @SerializedName("id") var id : Int,
    @SerializedName("student") var student : String,
    @SerializedName("datumRada") var datumRada : Date,
    @SerializedName("osvojeniBodovi") var osvojeniBodovi : Int,
    @SerializedName("KvizId") var KvizId : Int
) {
}