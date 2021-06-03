package ba.etf.rma21.projekat.data.repositories

import java.net.URL

object ApiConfig {
    var baseURL = "https://rma21-etf.herokuapp.com"
    fun postaviBaseURL(baseUrl:String):Unit{
        this.baseURL = baseUrl;
    }
    fun dajBaseURL() : String{
        return baseURL;
    }
}