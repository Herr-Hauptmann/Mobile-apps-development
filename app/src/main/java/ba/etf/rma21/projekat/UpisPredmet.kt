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

        var godina: Int? = null
        var predmet: Predmet? = null
        var grupa : Grupa? = null
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
                    godina = position
                    Korisnik.godina = position
                    val predmeti = PredmetRepository.getPredmetsByGodina(Korisnik.godina)
                    val stringovi = emptyList<String>().toMutableList()
                    stringovi.add(getString(R.string.predmetSpinner))
                    for(predmet in predmeti)
                        if (!Korisnik.predmeti.contains(Predmet(predmet.naziv, predmet.godina)))
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
                odabirPredmeta.isEnabled=false
                odabirGrupe.isEnabled=false
                dugme.isEnabled=false
            }

        }

        odabirPredmeta.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0){
                    val nazivPredmeta: String = odabirPredmeta.selectedItem.toString()
                    predmet = Predmet(nazivPredmeta, godina!!)
                    val grupe = GrupaRepository.getGroupsByPredmet(nazivPredmeta)
                    val stringovi = emptyList<String>().toMutableList()
                    stringovi.add(getString(R.string.grupaSpinner))
                    for (grupa in grupe)
                        stringovi.add(grupa.naziv)
                    popuniSpinner(odabirGrupe, stringovi)
                    odabirGrupe.isEnabled = true
                }
                else
                {
                    odabirGrupe.isEnabled = false
                    dugme.isEnabled = false
                }


            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                odabirGrupe.isEnabled=false
                dugme.isEnabled=false
            }
        }

        odabirGrupe.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position!=0) {
                    grupa = Grupa(odabirGrupe.selectedItem.toString(), predmet!!.naziv)
                    dugme.isEnabled = true
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                dugme.isEnabled=false
            }
        }

        dugme.setOnClickListener {
            Korisnik.predmeti.add(predmet!!)
            Korisnik.grupe.add(grupa!!)
            finish()
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