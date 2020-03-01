package lab.chevalier.bangsat.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "students")
data class Student (
    val name: String,
    val nim: String,
    val kelas: String,
    @PrimaryKey(autoGenerate = true) var id : Long = 0
) : Parcelable