package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    //PREDMETI
    @GET("/predmet")
    suspend fun getPredmeti(): Response<List<Predmet>>

    @GET("/predmet/{id}")
    suspend fun getPredmet(): Response<Predmet>

    //GRUPE
    @GET("/grupa")
    suspend fun getGrupe(): Response<List<Grupa>>
//    @GET("/predmet/{id}/grupa")
//    suspend fun getGrupeZaPredmet(idPredmeta : Int) : Response <List<Grupa>>

}
