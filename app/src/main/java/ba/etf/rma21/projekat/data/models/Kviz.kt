package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Kviz(
    @SerializedName("id") val id : Long,
    @SerializedName("naziv") var naziv: String,
    @SerializedName("datumPocetka") var datumPocetka: Date,
    @SerializedName("datumKraj") var datumKraj: Date,
    @SerializedName("trajanje") var trajanje: Int
) : Comparable<Kviz>{
    override fun compareTo(kviz:Kviz): Int {
        if (this.datumPocetka < kviz.datumPocetka)
            return -1
        if (this.datumPocetka > kviz.datumPocetka)
            return 1
        return 0
    }
}