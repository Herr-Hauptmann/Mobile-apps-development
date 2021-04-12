package ba.etf.rma21.projekat.data.models

import java.util.*

data class Kviz(
    val naziv: String, val nazivPredmeta: String, val datumPocetka: Date, val datumKraj: Date,
    val datumRada: Date?, val trajanje: Int, val nazivGrupe: String, val osvojeniBodovi: Float?
) : Comparable<Kviz>{
    override fun compareTo(kviz:Kviz): Int {
        if (this.datumPocetka < kviz.datumPocetka)
            return -1
        if (this.datumPocetka > kviz.datumPocetka)
            return 1
        return 0
    }
}