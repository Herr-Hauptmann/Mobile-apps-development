package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class OdgovorSlanje(
    @SerializedName("odgovor") val odgovor : Int,
    @SerializedName("pitanje") val  pitanjeId : Int,
    @SerializedName("bodovi") val bodovi : Int
) {
}