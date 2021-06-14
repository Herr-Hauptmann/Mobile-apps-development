package ba.etf.rma21.projekat.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
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
        checkExternalWritePermissions()
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
        spinner.setSelection(2)
        spinner.setSelection(1)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[0].toString())
                    kvizViewModel.getMojiKvizovi(
                        onSuccess = ::uspjesno,
                        onError = ::neuspjesno
                    )
                if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[1].toString()){
                    kvizViewModel.getSviKvizovi(
                        onSuccess = ::uspjesno,
                        onError = ::neuspjesno
                    )
                }
                if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[2].toString())
                    kvizViewModel.getUradjeniKvizovi(
                        onSuccess = ::uspjesno,
                        onError = ::neuspjesno)
                if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[3].toString())
                    kvizViewModel.getBuduciKvizovi(
                        onSuccess = ::uspjesno,
                        onError = ::neuspjesno
                    )
                if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[4].toString())
                    kvizViewModel.getBuduciKvizovi(
                        onSuccess = ::uspjesno,
                        onError = ::neuspjesno
                    )
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        return view
    }

    private fun uspjesno(kvizovi : List<Kviz>){
        kvizAdapter.updateQuizes(kvizovi)
    }
    private fun neuspjesno(){

    }

    private fun checkExternalWritePermissions(){

        if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.INTERNET)
            == PackageManager.PERMISSION_DENIED){
            var permissions: Array<String> = arrayOf(Manifest.permission.INTERNET)
            requestPermissions(permissions, 101)
        }else {
            kvizViewModel.getSviKvizovi(
                onSuccess = ::uspjesno,
                onError = ::neuspjesno
            )
        }
    }

    // Handle permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            101 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    kvizViewModel.getSviKvizovi(
                        onSuccess = ::uspjesno,
                        onError = ::neuspjesno
                    )
                } else {
                    Toast
                        .makeText(requireContext(), "Ne mogu meda", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}