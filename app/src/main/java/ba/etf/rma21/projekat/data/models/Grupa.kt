package ba.etf.rma21.projekat.data.models

data class Grupa(val naziv: String, val nazivPredmeta: String) {
    override operator fun equals(other: Any?): Boolean {
        if (other == null)
            return false;
        if (this::class != other!!::class)
            return false
        return this.naziv == (other as Grupa).naziv && this.nazivPredmeta == (other as Grupa).nazivPredmeta
    }
}