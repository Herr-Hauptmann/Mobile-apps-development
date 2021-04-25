package ba.etf.rma21.projekat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PredmetViewModel : ViewModel() {
    val trenutnaGodina : MutableLiveData<Int> by lazy{
        MutableLiveData<Int>(0)
    }
    val trenutniPredmet : MutableLiveData<Int> by lazy{
        MutableLiveData<Int>(0)
    }
    val trenutnaGrupa : MutableLiveData<Int> by lazy{
        MutableLiveData<Int>(0)
    }

    val posljednjiPredmet : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val posljednjaGrupa : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    fun postaviGodinu(godina:Int){
        trenutnaGodina.value = godina
    }
    fun postaviPredmet(predmet:Int){
        trenutniPredmet.value = predmet
    }
    fun postaviGrupu(grupa:Int){
        trenutnaGrupa.value = grupa
    }

    fun restart(predmet: String, grupa:String){
        posljednjaGrupa.value = grupa
        posljednjiPredmet.value = predmet
        trenutnaGrupa.value = 0
        trenutniPredmet.value = 0
    }

}