package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import ba.etf.rma21.projekat.data.repositories.TakeKvizRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class KvizTakenViewModel {
    val scope : CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)
    fun getSviPokusaji(onSuccess: (zapocetiKvizovi : List<KvizTaken>?) -> Unit, onError: () -> Unit){
        scope.launch{
            when (val result = TakeKvizRepository.getPocetiKvizovi()) {
                else -> onSuccess.invoke(result)
            }
        }
    }
}