package ba.etf.rma21.projekat.data.models

import ba.etf.rma21.projekat.data.repositories.ApiAdapter
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class Pitanje(
    @SerializedName("id") var id : Long,
    @SerializedName("naziv") var naziv : String,
    @SerializedName("tekstPitanja") var tekstPitanja : String,
    @SerializedName("opcije") val opcije: List<String>,
    @SerializedName("tacan") val tacan : Boolean){

}