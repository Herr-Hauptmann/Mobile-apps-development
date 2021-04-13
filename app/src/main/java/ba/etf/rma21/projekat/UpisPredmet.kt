package ba.etf.rma21.projekat

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Korisnik
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.PredmetRepository


class UpisPredmet : AppCompatActivity() {
    private lateinit var odabirGodine: Spinner
    private lateinit var odabirPredmeta: Spinner
    private lateinit var odabirGrupe: Spinner
    private lateinit var dugme: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upis_predmet)

        odabirGodine = findViewById(R.id.odabirGodina)
        odabirPredmeta = findViewById(R.id.odabirPredmet)
        odabirGrupe = findViewById(R.id.odabirGrupa)
        dugme = findViewById(R.id.dodajPredmetDugme)

        val predmet: Predmet? = null
        val grupeGlobal : List<Grupa>? = null
        ArrayAdapter.createFromResource(
            this,
            R.array.godine,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            odabirGodine.adapter = adapter
        }

        odabirGodine.setSelection(Korisnik.godina)
        odabirGodine.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0){
                    Korisnik.godina = position
                    val predmeti = PredmetRepository.getPredmetsByGodina(Korisnik.godina)
                    predmeti.toMutableList().removeAll(Korisnik.predmeti)
                    val stringovi = emptyList<String>().toMutableList()
                    for(predmet in predmeti)
                        stringovi.add(predmet.naziv)
                    popuniSpinner(odabirPredmeta, stringovi)
                    odabirPredmeta.isEnabled = true
                    odabirGrupe.isEnabled = false
                }
                else
                {
                    odabirPredmeta.isEnabled = false
                    odabirGrupe.isEnabled = false
                    dugme.isEnabled = false
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Ne treba nista
            }

        }

        odabirPredmeta.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val predmet = odabirPredmeta.selectedItem
                val grupe = GrupaRepository.getGroupsByPredmet(predmet as String)
                val stringovi = emptyList<String>().toMutableList()
                for (grupa in grupe)
                    stringovi.add(grupa.naziv)
                popuniSpinner(odabirGrupe, stringovi)
                odabirGrupe.isEnabled = true
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Ne treba nista
            }
        }

        odabirPredmeta.isEnabled = false
        odabirGrupe.isEnabled = false
        dugme.isEnabled = false
    }

    fun popuniSpinner(spinner: Spinner, lista: List<String>)
    {
        var adapterPredmeti = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            lista
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

}