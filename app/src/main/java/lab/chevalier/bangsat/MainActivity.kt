package lab.chevalier.bangsat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import lab.chevalier.bangsat.database.DB
import lab.chevalier.bangsat.database.Student
import lab.chevalier.bangsat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: ListAdapter
    private lateinit var db: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListAdapter { student: Student -> clicked(student) }
        db = DB.getDatabase(this)

        setupRecyclerView()

        getAllData()

        binding.btnAddStudent.setOnClickListener { startActivity(Intent(this, FormActivity::class.java).putExtra("type",1)) }
    }

    private fun setupRecyclerView(){
        binding.rvStudent.layoutManager = LinearLayoutManager(this)
        binding.rvStudent.adapter = adapter
    }

    private fun clicked(student: Student){
        val intent = Intent(this, FormActivity::class.java)
        intent.putExtra("data",student)
        intent.putExtra("type",2)
        startActivity(intent)
    }

    private fun getAllData(){
        GlobalScope.launch(Dispatchers.Main) {
            val students = async(Dispatchers.IO){
                db.services().getAllData()
            }
            binding.rvStudent.visibility = View.VISIBLE
            binding.btnAddStudent.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            val listStudents = students.await()
            adapter.item = listStudents
        }
    }

    override fun onResume() {
        getAllData()
        super.onResume()
    }
}
