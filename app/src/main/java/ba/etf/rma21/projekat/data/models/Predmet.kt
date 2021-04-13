package ba.etf.rma21.projekat.data.models

data class Predmet(val naziv: String, val godina: Int) {
    override operator fun equals(other: Any?): Boolean {
        if (other == null)
            return false;
        if (this::class != other!!::class)
            return false
        return this.naziv == (other as Predmet).naziv
    }
}