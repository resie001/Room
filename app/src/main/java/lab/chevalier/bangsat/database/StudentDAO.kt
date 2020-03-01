package lab.chevalier.bangsat.database

import androidx.room.*

@Dao
interface StudentDAO {
    @Query("SELECT * FROM students")
    fun getAllData() : List<Student>

    @Insert
    fun insert(vararg student: Student)

    @Delete
    fun delete(student: Student)

    @Update
    fun update(vararg student: Student)
}