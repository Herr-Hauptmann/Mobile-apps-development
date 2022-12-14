package ba.etf.rma21.projekat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction1

class PredmetViewModel : ViewModel() {
    val scope : CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    val trenutnaGodina : MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }
    val trenutniPredmet : MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }
    val trenutnaGrupa : MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
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

    fun getPredmeti(onSuccess : (predmeti : List<Predmet>)-> Unit, onError:()->Unit){
        scope.launch{
            when (val result = PredmetIGrupaRepository.getPredmeti()) {
                else -> onSuccess.invoke(result)
            }
        }
    }

    fun getPredmetWithId(predmetId : Int, onSuccess: (predmet : Predmet ) -> Unit, onError: () -> Unit){
        scope.launch{
            when (val result = PredmetIGrupaRepository.getPredmetById(predmetId)) {
                else -> onSuccess.invoke(result)
            }
        }
    }

    fun getPredmetWithKviz(kvizId: Int, onSuccess: (predmet : Predmet ) -> Unit, onError: () -> Unit){
        scope.launch{
            when (val result = PredmetIGrupaRepository.getPredmetByKvizId(kvizId)) {
                else -> result?.let { onSuccess.invoke(it) }
            }
        }
    }

    fun getPredmetWithGodina(godina : Int, onSuccess: (predmet : List<Predmet> ) -> Unit, onError: () -> Unit){
        scope.launch{
            when (val result = PredmetIGrupaRepository.getPredmetByGodina(godina)) {
                else -> onSuccess.invoke(result)
            }
        }
    }

}