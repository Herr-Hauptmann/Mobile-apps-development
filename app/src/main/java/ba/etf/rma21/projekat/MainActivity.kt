package ba.etf.rma21.projekat

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.view.QuizAdapter
import ba.etf.rma21.projekat.viewmodel.QuizListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var listaKvizova: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var kvizAdapter: QuizAdapter
    private var kvizViewModel = QuizListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listaKvizova = findViewById(R.id.listaKvizova)
        listaKvizova.layoutManager = GridLayoutManager(this, 2);
        kvizAdapter = QuizAdapter((listOf()))
        listaKvizova.adapter = kvizAdapter
        kvizAdapter.updateQuizes(kvizViewModel.getSviKvizovi())

        spinner = findViewById(R.id.filterKvizova)
        ArrayAdapter.createFromResource(
            this,
            R.array.spinnerOpcije,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.setSelection(1)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[0].toString())
                    kvizAdapter.updateQuizes(kvizViewModel.getMojiKvizovi())
                if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[1].toString())
                    kvizAdapter.updateQuizes(kvizViewModel.getSviKvizovi())
                if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[2].toString())
                    kvizAdapter.updateQuizes(kvizViewModel.getUradjeniKvizovi())
                if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[3].toString())
                    kvizAdapter.updateQuizes(kvizViewModel.getBuduciKvizovi())
                if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[4].toString())
                    kvizAdapter.updateQuizes(kvizViewModel.getNeuradjeniKvizovi())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                kvizAdapter.updateQuizes(kvizViewModel.getSviKvizovi())
            }
        }

    }
}
