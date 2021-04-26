package ba.etf.rma21.projekat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.view.QuizAdapter
import ba.etf.rma21.projekat.viewmodel.QuizListViewModel

class FragmentKvizovi : Fragment() {
    private lateinit var listaKvizova: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var kvizAdapter: QuizAdapter
    private var kvizViewModel = QuizListViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kvizovi, container, false)

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (context as MainActivity).odaberiTrenutniFragment(FragmentKvizovi())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        listaKvizova = view.findViewById(R.id.listaKvizova)
        listaKvizova.layoutManager = GridLayoutManager(activity, 2);
        kvizAdapter = QuizAdapter((listOf()))
        listaKvizova.adapter = kvizAdapter
        kvizAdapter.updateQuizes(kvizViewModel.getSviKvizovi())

        spinner = view.findViewById(R.id.filterKvizova)
        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.spinnerOpcije,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
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
                kvizAdapter.updateQuizes(kvizViewModel.getMojiKvizovi())
            }
        }

        return view
    }


}