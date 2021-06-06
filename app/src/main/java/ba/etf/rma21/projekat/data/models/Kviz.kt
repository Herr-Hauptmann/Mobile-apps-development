package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Kviz(
    @SerializedName("id") val id : Int,
    @SerializedName("naziv") var naziv: String,
    @SerializedName("datumPocetak") var datumPocetka: Date,
    @SerializedName("datumKraj") var datumKraj: Date?,
    @SerializedName("trajanje") var trajanje: Int,
    @SerializedName("predmetId") var predmetId: Int
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