package lab.chevalier.bangsat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import lab.chevalier.bangsat.database.DB
import lab.chevalier.bangsat.database.Student
import lab.chevalier.bangsat.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFormBinding
    private lateinit var db : DB
    private lateinit var data : Student

    private val INSERT = 1
    private val UPDATE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DB.getDatabase(this)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tambah Data"

        if (intent.getIntExtra("type",0) == 2){

            supportActionBar?.title = "Update Data"

            binding.btnInsert.visibility = View.GONE
            binding.btnDelete.visibility = View.VISIBLE
            binding.btnUpdate.visibility = View.VISIBLE
            binding.view.visibility = View.VISIBLE

            data = intent.getParcelableExtra("data") as Student
            binding.edNama.hint = data.name
            binding.edKelas.hint = data.kelas
            binding.edNim.hint = data.nim

        }

        binding.btnInsert.setOnClickListener { checkInput(INSERT) }
        binding.btnUpdate.setOnClickListener { checkInput(UPDATE) }
        binding.btnDelete.setOnClickListener { deleteData() }
    }

    private fun checkInput(request : Int){
        when {
            binding.edNama.text.isEmpty() -> {
                Toast.makeText(this, "Nama Harus Diisin!", Toast.LENGTH_SHORT).show()
            }
            binding.edNim.text.isEmpty() -> {
                Toast.makeText(this, "NIM Harus Diisin!", Toast.LENGTH_SHORT).show()
            }
            binding.edKelas.text.isEmpty() -> {
                Toast.makeText(this, "Kelas Harus Diisin!", Toast.LENGTH_SHORT).show()
            }
            else -> {
                when (request){
                    INSERT -> insertData()
                    else -> updateData()
                }
            }
        }
    }

    private fun insertData(){
        db.services().insert(Student(
            binding.edNama.text.toString(),
            binding.edNim.text.toString(),
            binding.edKelas.text.toString()
        ))
        finish()
    }

    private fun deleteData(){
        db.services().delete(Student(
            data.name,
            data.nim,
            data.kelas
        ))
        finish()
    }

    private fun updateData(){
        db.services().update(Student(
            binding.edNama.text.toString(),
            binding.edNim.text.toString(),
            binding.edKelas.text.toString()
        ))
        finish()
    }
}
