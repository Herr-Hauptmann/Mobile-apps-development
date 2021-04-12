package ba.etf.rma21.projekat.data.models

object Korisnik{
    var predmeti : MutableList<Predmet> = mutableListOf<Predmet>()
    var godina:Int = 1
    var grupa: Grupa? = null;

    fun dodajPredmet(predmet: Predmet){
        predmeti.add(predmet)
    }
    fun daLiJeUpisan(predmet: Predmet) : Boolean{
        return predmeti.contains(predmet)
    }

}