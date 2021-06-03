package ba.etf.rma21.projekat.data.repositories

object AccountRepository{
    var acHash : String = "8861bc6f-c600-4d3a-93e8-fd12ef85c979"

    fun postaviHash(acHash:String):Boolean{
        this.acHash = acHash;
        return (acHash == acHash)
    }
    @JvmName("getHash1")
    fun getHash():String{
        return acHash
    }



}