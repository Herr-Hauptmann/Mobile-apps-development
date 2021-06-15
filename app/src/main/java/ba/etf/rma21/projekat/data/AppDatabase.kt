//package ba.etf.rma21.projekat.data
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import ba.etf.rma21.projekat.data.models.*
////Kviz::class, Pitanje::class,Odgovor::class, Grupa::class,Predmet::class,
//@Database(entities = [Account::class], version = 1,exportSchema = false)
//abstract class AppDatabase: RoomDatabase() {
//    abstract fun accountDao() : AccountDAO
//
//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//        fun getInstance(context: Context): AppDatabase {
//            if (INSTANCE == null) {
//                synchronized(AppDatabase::class) {
//                    INSTANCE = buildRoomDB(context)
//                }
//            }
//            return INSTANCE!!
//        }
//        fun setInstance(appdb:AppDatabase):Unit{
//            INSTANCE=appdb
//        }
//        private fun buildRoomDB(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java,
//                "RMA21DB"
//            ).build()
//    }
//}