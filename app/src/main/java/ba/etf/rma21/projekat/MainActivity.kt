package ba.etf.rma21.projekat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.fragments.FragmentKvizovi
import ba.etf.rma21.projekat.fragments.FragmentPredmeti
import ba.etf.rma21.projekat.view.QuizAdapter
import ba.etf.rma21.projekat.viewmodel.QuizListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    val fragmentKvizovi = FragmentKvizovi()
//    val fragmentPredmeti = FragmentPredmeti()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        odaberiTrenutniFragment(fragmentKvizovi)

//        bottomNav.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
////                R.id.kvizovi -> odaberiTrenutniFragment(fragmentKvizovi)
////                R.id.predmeti -> odaberiTrenutniFragment(fragmentPredmeti)
//            }
//            true
//        }

    }

    private fun odaberiTrenutniFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fragment, fragment)
            commit()
        }

    }

//    override fun onResume() {
//        super.onResume()
//        if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[0].toString())
//            kvizAdapter.updateQuizes(kvizViewModel.getMojiKvizovi())
//        if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[1].toString())
//            kvizAdapter.updateQuizes(kvizViewModel.getSviKvizovi())
//        if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[2].toString())
//            kvizAdapter.updateQuizes(kvizViewModel.getUradjeniKvizovi())
//        if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[3].toString())
//            kvizAdapter.updateQuizes(kvizViewModel.getBuduciKvizovi())
//        if (spinner.selectedItem.toString() == resources.getStringArray(R.array.spinnerOpcije)[4].toString())
//            kvizAdapter.updateQuizes(kvizViewModel.getNeuradjeniKvizovi())
//    }


}
