package lab.chevalier.bangsat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lab.chevalier.bangsat.database.Student
import lab.chevalier.bangsat.databinding.ItemStudentBinding

class ListAdapter (private val clickListener : (Student) -> Unit) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var item : List<Student> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStudentBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(item[position])

    inner class ViewHolder( val binding: ItemStudentBinding ) : RecyclerView.ViewHolder(binding.root){
        fun bind ( student: Student ) {
            with(binding){
                tvNama.text = student.name
                tvKelas.text = student.kelas
                tvNim.text = student.nim
                itemView.setOnClickListener { clickListener(student) }
            }
        }
    }
}