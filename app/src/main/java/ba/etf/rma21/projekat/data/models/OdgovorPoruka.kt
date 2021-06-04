package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

data class OdgovorPoruka (
    @SerializedName("id") val id : Int ,
    @SerializedName("odgovoreno") val odgovoreno : Int)
{
}