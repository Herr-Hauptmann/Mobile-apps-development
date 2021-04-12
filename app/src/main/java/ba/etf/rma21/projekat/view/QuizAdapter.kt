package ba.etf.rma21.projekat.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
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
        val nazivKviza : TextView = itemView.findViewById(R.id.nazivPredmeta)
        val datumKviza : TextView = itemView.findViewById(R.id.nazivPredmeta)
        val trajanje : TextView = itemView.findViewById(R.id.nazivPredmeta)
        val osvojeniBodovi : TextView = itemView.findViewById(R.id.nazivPredmeta)
        val status : ImageView = itemView.findViewById(R.id.status)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.nazivKviza.text = kvizovi[position].naziv
        holder.nazivPredmeta.text = kvizovi[position].nazivPredmeta
        holder.datumKviza.text = kvizovi[position].datumPocetka.toString()
        holder.trajanje.text = kvizovi[position].trajanje.toString()


        //TODO: implementirati datume i statuse
//        if (Date().after(strDate)) {
//            catalog_outdated = 1
//        }

    }

    override fun getItemCount(): Int {
        return kvizovi.size
    }

    fun updateQuizes(kvizovi: List<Kviz>)
    {
        this.kvizovi = kvizovi;
        notifyDataSetChanged()
    }
}