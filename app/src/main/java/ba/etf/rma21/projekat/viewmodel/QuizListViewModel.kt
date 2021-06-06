package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class QuizListViewModel {
    private val scope : CoroutineScope = CoroutineScope(Dispatchers.IO)
    fun getSviKvizovi(onSuccess : (kvizovi : List<Kviz>)-> Unit, onError:()->Unit){
        scope.launch{
            when (val result = KvizRepository.getAll()) {
                else -> onSuccess.invoke(result)
            }
        }
    }
//    fun getMojiKvizovi():List<Kviz>
//    {
//        return KvizRepository.getMyKvizes();
//    }
//    fun getBuduciKvizovi() : List<Kviz>
//    {
//        return KvizRepository.getFuture();
//    }
//    fun getNeuradjeniKvizovi() : List<Kviz>
//    {
//        return KvizRepository.getNotTaken();
//    }
//    fun getUradjeniKvizovi(): List<Kviz> {
//        return KvizRepository.getDone()
//    }
}