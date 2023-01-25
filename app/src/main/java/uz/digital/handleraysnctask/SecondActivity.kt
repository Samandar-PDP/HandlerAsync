package uz.digital.handleraysnctask

import android.app.WallpaperManager
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import uz.digital.handleraysnctask.database.Student
import uz.digital.handleraysnctask.database.StudentDatabase

class SecondActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private val database by lazy { StudentDatabase(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnSet: Button = findViewById(R.id.btnSet)
        textView = findViewById(R.id.textStudent)
        val myAsyncTask = MyAsyncTask()
        val dbTask = MyDbTask()
        btnSet.setOnClickListener {
            //myAsyncTask.execute(R.drawable.img_1)
            dbTask.execute(Student(name = "blah", age = 2))
        }
    }

    private inner class MyDbTask : AsyncTask<Student, Unit, List<Student>>() {
        override fun doInBackground(vararg params: Student): List<Student> {
            database.dao.saveStudent(params[0])
            return database.dao.getStudents()
        }

        override fun onPostExecute(result: List<Student>?) {
            super.onPostExecute(result)
            textView.text = result.toString()
            Toast.makeText(this@SecondActivity, "Saved", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class MyAsyncTask : AsyncTask<Int, Unit, Unit>() {
        override fun doInBackground(vararg params: Int?) {
            val wallpaperManager = getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager
            wallpaperManager.setResource(params[0]!!)
        }

        override fun onPostExecute(result: Unit?) {
            Toast.makeText(this@SecondActivity, "Set", Toast.LENGTH_SHORT).show()
        }

    }
}