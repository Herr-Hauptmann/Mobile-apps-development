package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Poruka
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.AccountRepository.getHash
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    //PREDMETI
    @GET("/predmet")
    suspend fun getPredmeti(): Response<List<Predmet>>
    @GET("/predmet/{id}")
    suspend fun getPredmet(): Response<Predmet>

    //GRUPE
    @GET("/grupa")
    suspend fun getGrupe(): Response<List<Grupa>>
    @GET("/predmet/{id}/grupa")
    suspend fun getGrupeZaPredmet(@Path("id") idPredmeta : Int) : Response <List<Grupa>>
    @GET("/student/{id}/grupa")
    suspend fun getGrupeZaStudenta(@Path("id") hashStudenta: String = getHash()) : Response<List<Grupa>>
    @POST("/grupa/{gid}/student/{id}")
    suspend fun upisiUGrupu(@Path("gid") idGrupa:Int, @Path("id") id : String = getHash()) : Response<Poruka>
}
