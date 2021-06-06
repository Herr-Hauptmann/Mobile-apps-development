package ba.etf.rma21.projekat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Korisnik
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import ba.etf.rma21.projekat.viewmodel.GrupaViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetViewModel

class FragmentPredmeti : Fragment() {
    private lateinit var odabirGodine: Spinner
    private lateinit var odabirPredmeta: Spinner
    private lateinit var odabirGrupe: Spinner
    private lateinit var dugme: Button
    private lateinit var godine : MutableList<Int>
    private lateinit var Predmeti : MutableList<Predmet>
    private lateinit var grupe : MutableList<Grupa>
    private lateinit var modelGlobal : PredmetViewModel;

    var godina: Int = 0
    var predmet: Int = 0
    var grupa : Int = 0

    var idPredmeta : MutableList<Int> = emptyList<Int>().toMutableList()
    var idGrupa : MutableList<Int> = emptyList<Int>().toMutableList()

    var nazivPredmeta : String = ""
    var nazivGrupe : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_predmeti, container, false)

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (context as MainActivity).odaberiTrenutniFragment(FragmentKvizovi())
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        odabirGodine = view.findViewById(R.id.odabirGodina)
        odabirPredmeta = view.findViewById(R.id.odabirPredmet)
        odabirGrupe = view.findViewById(R.id.odabirGrupa)
        dugme = view.findViewById(R.id.dodajPredmetDugme)

        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.godine,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                odabirGodine.adapter = adapter
            }
        }
        odabirGodine.setSelection(Korisnik.godina)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var model = ViewModelProvider(requireActivity()).get(PredmetViewModel::class.java)
        modelGlobal = model
        model.trenutnaGodina.observe(viewLifecycleOwner, Observer {
            odabirGodine.setSelection(it)
            if (it != 0)
                odabirPredmeta.isEnabled
        })
        model.trenutniPredmet.observe(viewLifecycleOwner, Observer {
            odabirPredmeta.setSelection(it)
            if (it != 0)
                odabirGrupe.isEnabled
        })
        model.trenutnaGrupa.observe(viewLifecycleOwner, Observer {
            odabirGrupe.setSelection(it)
            if (it != 0)
                dugme.isEnabled
        })

        odabirGodine.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                model.postaviGodinu(position)
                if (position != 0){
                    godina = position
                    Korisnik.godina = position
                    val model1 : PredmetViewModel = PredmetViewModel()
                    model1.getPredmetWithGodina(position, onSuccess = ::uspjeloPredmet, onError = ::nijeUspjelo)
                }
                else
                {
                    odabirPredmeta.isEnabled = false
                    odabirGrupe.isEnabled = false
                    dugme.isEnabled = false
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        odabirPredmeta.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                model.postaviPredmet(position)
                 if(position != 0){
                    predmet = position
                     nazivPredmeta = odabirPredmeta.selectedItem.toString()
                     val model2 : GrupaViewModel = GrupaViewModel()
                     model2.getGrupeByPredmet(idPredmeta[predmet-1], onSuccess = ::uspjeloGrupa, onError = ::nijeUspjelo)
                }
                else
                {
                    odabirGrupe.isEnabled = false
                    dugme.isEnabled = false
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        odabirGrupe.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                model.postaviGrupu(position)
                if (position!=0) {
                    grupa = position
                    nazivGrupe = odabirGodine.selectedItem.toString()
                    dugme.isEnabled = true
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        dugme.setOnClickListener{
            modelGlobal.restart(nazivPredmeta, nazivGrupe)
            var model3 : GrupaViewModel = GrupaViewModel()
            (context as MainActivity).odaberiTrenutniFragment(FragmentPoruka())
            model3.upisiGrupu(idGrupa[grupa-1], onSuccess = ::zavrsi, onError = ::nijeUspjelo)
        }
    }

    fun uspjeloGrupa(grupe : List<Grupa>){
        val stringovi = emptyList<String>().toMutableList()
        stringovi.add(getString(R.string.grupaSpinner))
        for (grupa in grupe){
            stringovi.add(grupa.naziv)
        }

        popuniSpinner(odabirGrupe, stringovi)
        odabirGrupe.isEnabled = true
        if (modelGlobal.trenutnaGrupa.value!=null)
            odabirGrupe.setSelection(modelGlobal.trenutnaGrupa.value!!)
    }

    fun uspjeloPredmet(predmeti : List<Predmet>){
        val stringovi = emptyList<String>().toMutableList()
        stringovi.add(getString(R.string.predmetSpinner))
        for (predmet in predmeti){
            stringovi.add(predmet.naziv)
            idPredmeta.add(predmet.id)
        }

        popuniSpinner(odabirPredmeta, stringovi)
        odabirPredmeta.isEnabled = true
        if(modelGlobal.trenutniPredmet.value!=null)
            odabirPredmeta.setSelection(modelGlobal.trenutniPredmet.value!!)
        if(odabirPredmeta.selectedItemPosition == 0)
            odabirGrupe.isEnabled = false
    }

    fun nijeUspjelo(){
        (context as MainActivity).odaberiTrenutniFragment(FragmentPoruka())
    }

    fun popuniSpinner(spinner: Spinner, lista: List<String>)
    {
        var adapterPredmeti = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                lista
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        }
    }

    fun zavrsi(uspjelo : Boolean){
        (context as MainActivity).odaberiTrenutniFragment(FragmentPoruka())
    }
}