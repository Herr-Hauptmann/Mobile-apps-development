package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.KvizRepository

class QuizListViewModel {
    fun getSviKvizovi():List<Kviz>{
        return KvizRepository.getMyKvizes();
    }
    fun getMojiKvizovi():List<Kviz>
    {
        return KvizRepository.getAll();
    }
    fun getBuduciKvizovi() : List<Kviz>
    {
        return KvizRepository.getFuture();
    }
    fun getNeuradjeniKvizovi() : List<Kviz>
    {
        return KvizRepository.getNotTaken();
    }
}