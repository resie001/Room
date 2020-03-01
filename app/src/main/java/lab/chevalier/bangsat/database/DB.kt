package lab.chevalier.bangsat.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class DB : RoomDatabase() {
    abstract fun services(): StudentDAO

    companion object {

        @Volatile private var INSTANCE : DB? = null

        fun getDatabase (context: Context) : DB {
            val tempsIntance = INSTANCE
            if (tempsIntance != null){
                return tempsIntance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "bangsat.db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}