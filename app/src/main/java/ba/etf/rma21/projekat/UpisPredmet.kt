package ba.etf.rma21.projekat

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.PredmetRepository

class UpisPredmet : AppCompatActivity(){
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

        ArrayAdapter.createFromResource(
            this,
            R.array.godine,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            odabirGodine.adapter = adapter
        }

        var adapterPredmeti = ArrayAdapter(this, android.R.layout.simple_spinner_item,  PredmetRepository.getPredmetsByGodina(1))
            .also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            odabirPredmeta.adapter = adapter
        }

        var adapterGrupe = ArrayAdapter(this, android.R.layout.simple_spinner_item,  GrupaRepository.getGroupsByPredmet("OE"))
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                odabirPredmeta.adapter = adapter
            }
    }
}