package uz.digital.handleraysnctask.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveStudent(student: Student)

    @Query("select * from student")
    fun getStudents(): List<Student>
}