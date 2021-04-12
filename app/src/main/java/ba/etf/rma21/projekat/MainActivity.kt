package ba.etf.rma21.projekat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.view.QuizAdapter
import ba.etf.rma21.projekat.viewmodel.QuizListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var listaKvizova : RecyclerView
    private lateinit var kvizAdapter : QuizAdapter
    private var kvizViewModel = QuizListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listaKvizova = findViewById(R.id.listaKvizova)
        listaKvizova.layoutManager = GridLayoutManager(this, 2);
        kvizAdapter = QuizAdapter((listOf()))
        listaKvizova.adapter = kvizAdapter
        kvizAdapter.updateQuizes(kvizViewModel.getSviKvizovi())
    }
}

