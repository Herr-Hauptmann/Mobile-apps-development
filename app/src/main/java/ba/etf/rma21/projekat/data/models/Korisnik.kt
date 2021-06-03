package ba.etf.rma21.projekat.data.models

object Korisnik{
    var predmeti : MutableList<Predmet> = mutableListOf<Predmet>()
    var grupe: MutableList<Grupa> = mutableListOf<Grupa>()
    var godina:Int = 0

    fun dodajPredmet(predmet: Predmet){
        predmeti.add(predmet)
    }
    fun daLiJeUpisan(predmet: Predmet) : Boolean{
        return predmeti.contains(predmet)
    }

}