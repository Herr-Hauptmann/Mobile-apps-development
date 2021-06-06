package ba.etf.rma21.projekat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.viewmodel.KvizTakenViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetViewModel
import ba.etf.rma21.projekat.viewmodel.QuizListViewModel
import java.util.*

class QuizAdapter(private var kvizovi: List<Kviz>) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {
    var globalniHolder : QuizViewHolder? = null
    var globalniKviz : Kviz? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.quiz_item, parent, false)
        return QuizViewHolder(view)
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nazivPredmeta : TextView = itemView.findViewById(R.id.nazivPredmeta)
        val nazivKviza : TextView = itemView.findViewById(R.id.nazivKviza)
        val datumKviza : TextView = itemView.findViewById(R.id.datumKviza)
        val trajanje : TextView = itemView.findViewById(R.id.trajanje)
        val osvojeniBodovi : TextView = itemView.findViewById(R.id.osvojeniBodovi)
        val status : ImageView = itemView.findViewById(R.id.status)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val kviz:Kviz = kvizovi[position]

        holder.nazivKviza.text = kviz.naziv

        pronadjiPredmet(kviz, holder)

        var datumZaPrikaz:Date = kviz.datumPocetka

        provjeriTip(kviz, holder)

        val dan = datumZaPrikaz!!.date
        val mjesec = datumZaPrikaz!!.month+1
        val godina = datumZaPrikaz!!.year+1900

        holder.datumKviza.text = "$dan.$mjesec.$godina"
        holder.trajanje.text = kviz.trajanje.toString() + " min"

    }

    override fun getItemCount(): Int {
        return kvizovi.size
    }

    fun updateQuizes(kvizovi: List<Kviz>)
    {
        this.kvizovi = kvizovi.sorted();
        notifyDataSetChanged()
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun pronadjiPredmet(kviz : Kviz, holder: QuizViewHolder){
        val novi = PredmetViewModel();
        globalniHolder = holder;
        novi.getPredmetWithKviz(kviz.id, onSuccess = ::dajString, onError = ::neRadi)
    }

    private fun provjeriTip(kviz : Kviz, holder: QuizViewHolder){
        val novi = KvizTakenViewModel();
        globalniHolder = holder;
        globalniKviz = kviz;
        novi.getSviPokusaji(onSuccess = ::obleti, onError = ::neRadi)
    }

    private fun obleti(pokusaji : List<KvizTaken>?){
        if (pokusaji == null)
            return
        for (pokusaj in pokusaji)
            if (pokusaj.KvizId == globalniKviz!!.id){
                globalniHolder!!.status.setImageResource(globalniHolder!!.status.context.resources.getIdentifier("plava", "drawable", globalniHolder!!.status.context.packageName))
                globalniHolder!!.osvojeniBodovi.text = pokusaj.osvojeniBodovi.toString()
                return
            }
        if (globalniKviz!!.datumPocetka >= getCurrentDateTime()){
            if(globalniKviz!!.datumKraj == null || globalniKviz!!.datumKraj!! < getCurrentDateTime())
                globalniHolder!!.status.setImageResource(globalniHolder!!.status.context.resources.getIdentifier("zelena", "drawable", globalniHolder!!.status.context.packageName))
            else
                globalniHolder!!.status.setImageResource(globalniHolder!!.status.context.resources.getIdentifier("crvena", "drawable", globalniHolder!!.status.context.packageName))
        }
        else
            globalniHolder!!.status.setImageResource(globalniHolder!!.status.context.resources.getIdentifier("zuta", "drawable", globalniHolder!!.status.context.packageName))

        globalniHolder!!.osvojeniBodovi.text = "";
    }

    private fun dajString(predmet : Predmet){
        globalniHolder!!.nazivPredmeta.text = predmet.naziv
    }

    private fun neRadi(){}


}