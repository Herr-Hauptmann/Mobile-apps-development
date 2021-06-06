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
    private val scope : CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)
    fun getSviKvizovi(onSuccess : (kvizovi : List<Kviz>)-> Unit, onError:()->Unit){
        scope.launch{
            when (val result = KvizRepository.getAll()) {
                else -> onSuccess.invoke(result)
            }
        }
    }
    fun getMojiKvizovi(onSuccess : (kvizovi : List<Kviz>)-> Unit, onError:()->Unit)
    {
        scope.launch{
            when (val result = KvizRepository.getUpisani()) {
                else -> onSuccess.invoke(result)
            }
        }
    }
    fun getBuduciKvizovi(onSuccess : (kvizovi : List<Kviz>)-> Unit, onError:()->Unit)
    {
        scope.launch{
            when (val result = KvizRepository.getBuduci()) {
                else -> onSuccess.invoke(result)
            }
        }
    }
    fun getNeuradjeniKvizovi(onSuccess : (kvizovi : List<Kviz>)-> Unit, onError:()->Unit)
    {
        scope.launch{
            when (val result = KvizRepository.getNeRadjeni()) {
                else -> onSuccess.invoke(result)
            }
        }
    }
    fun getUradjeniKvizovi(onSuccess : (kvizovi : List<Kviz>)-> Unit, onError:()->Unit){
        scope.launch{
            when (val result = KvizRepository.getRadjeni()) {
                else -> onSuccess.invoke(result)
            }
        }
    }
}