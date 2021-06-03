package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

data class Grupa(
    @SerializedName("id") val id : Long,
    @SerializedName("naziv") val naziv : String) {
    override operator fun equals(other: Any?): Boolean {
        if (other == null)
            return false;
        if (this::class != other!!::class)
            return false
        return this.id == (other as Grupa).id && this.naziv == (other as Grupa).naziv
    }
}