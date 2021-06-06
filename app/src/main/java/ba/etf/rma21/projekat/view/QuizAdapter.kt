package ba.etf.rma21.projekat.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import ba.etf.rma21.projekat.viewmodel.PredmetViewModel
import kotlinx.coroutines.Dispatchers
import java.util.*

class QuizAdapter(private var kvizovi: List<Kviz>) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {
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
//        val kviz:Kviz = kvizovi[position]

        holder.nazivKviza.text = "Nebitno"
        holder.nazivPredmeta.text = "IM1"
//        holder.datumKviza.text = "1.1.1999"
        holder.trajanje.text = "30"

//        holder.nazivPredmeta.text = PredmetViewModel().getPredmetWithId(kviz.predmetId).
//            kviz.nazivPredmeta

//        var datumZaPrikaz:Date
//        var boja:String
//        val danasnjiDatum :Date = getCurrentDateTime()
//        if (kviz.osvojeniBodovi != null)
//        {
//            datumZaPrikaz = kviz.datumRada!!
//            boja = "plava"
//            holder.osvojeniBodovi.text = kviz.osvojeniBodovi.toString()
//        }
//        if(kviz.datumPocetka < danasnjiDatum && kviz.datumKraj > danasnjiDatum)
//        {
//            datumZaPrikaz = kviz.datumKraj
//            boja = "zelena"
//            holder.osvojeniBodovi.text=""
//        }
//        else if(kviz.datumKraj < danasnjiDatum)
//        {
//            datumZaPrikaz = kviz.datumKraj
//            boja = "crvena"
//            holder.osvojeniBodovi.text=""
//        }
//        else
//        {
//            datumZaPrikaz = kviz.datumPocetka
//            boja = "zuta"
//            holder.osvojeniBodovi.text=""
//        }

//        val dan = datumZaPrikaz!!.date
//        val mjesec = datumZaPrikaz!!.month
//        val godina = datumZaPrikaz!!.year

//        holder.datumKviza.text = "$dan.$mjesec.$godina"
//        holder.trajanje.text = kviz.trajanje.toString() + " min"
//
//        val context : Context = holder.status.context
//        var id: Int = context.resources.getIdentifier(boja, "drawable", context.packageName)
//
//        holder.status.setImageResource(id)
    }

    override fun getItemCount(): Int {
        return kvizovi.size
    }

    fun updateQuizes(kvizovi: List<Kviz>)
    {
        this.kvizovi = kvizovi.sorted();
        notifyDataSetChanged()
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}