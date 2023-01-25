package uz.digital.handleraysnctask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {
    abstract val dao: StudentDao

    companion object {
        @Volatile
        private var instance: StudentDatabase? = null
        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context): StudentDatabase {
            return Room.databaseBuilder(
                context,
                StudentDatabase::class.java,
                "Student.db"
            ).build()
        }
    }
}