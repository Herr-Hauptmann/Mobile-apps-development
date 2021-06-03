package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

class Odgovor(
    @SerializedName("id") var id : Long,
    @SerializedName("odgovoreno") var odgovoreno : Int
) {

}
