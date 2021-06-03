package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

data class Poruka(
    @SerializedName("message") val message : String
) {
    fun dajPoruku() : String{
        return message;
    }
}