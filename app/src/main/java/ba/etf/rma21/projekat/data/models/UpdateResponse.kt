package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

data class UpdateResponse(
    @SerializedName("changed") val izmjena : String
) {
}