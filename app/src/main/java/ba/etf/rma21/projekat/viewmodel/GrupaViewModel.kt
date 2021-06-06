package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GrupaViewModel {
    val scope : CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)
    fun getSveGrupe(onSuccess : (grupe : List<Grupa>)-> Unit, onError:()->Unit){
        scope.launch{
            when (val result = PredmetIGrupaRepository.getGrupe()) {
                else -> onSuccess.invoke(result)
            }
        }
    }

}