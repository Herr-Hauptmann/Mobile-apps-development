package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.*
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

    //KVIZOVI
    @GET("/kviz")
    suspend fun getKvizovi() : Response <List<Kviz>>
    @GET("/kviz/{id}")
    suspend fun getKvizById(@Path("id") id : Int) : Response<Kviz>
    @GET("/grupa/{id}/kvizovi")
    suspend fun getKvizByGrupaId(@Path("id") idGrupe : Int) : Response<List<Kviz>>

    //KVIZ TAKEN
    @POST("/student/{id}/kviz/{kid}")
    suspend fun zapoocniKvizZaStudenta(@Path("kid") kvizId : Int, @Path("id") hashStudenta: String = getHash())
    : Response<KvizTaken>
    @GET("/student/{id}/kviztaken")
    suspend fun dajPokusaje(@Path("id") id : String = getHash()) : Response<List<KvizTaken>>
}
