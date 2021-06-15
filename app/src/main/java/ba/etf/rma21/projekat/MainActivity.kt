package ba.etf.rma21.projekat

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import ba.etf.rma21.projekat.fragments.FragmentKvizovi
import ba.etf.rma21.projekat.fragments.FragmentPredmeti
//import androidx.recyclerview.widget.RecyclerView
//import ba.etf.rma21.projekat.fragments.FragmentKvizovi
//import ba.etf.rma21.projekat.fragments.FragmentPredmeti
//import ba.etf.rma21.projekat.view.QuizAdapter
//import ba.etf.rma21.projekat.viewmodel.PredmetViewModel
//import ba.etf.rma21.projekat.viewmodel.QuizListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Navigacioni meni
        odaberiTrenutniFragment(FragmentKvizovi())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.kvizovi -> odaberiTrenutniFragment(FragmentKvizovi())
                R.id.predmeti -> odaberiTrenutniFragment(FragmentPredmeti())
            }
            true
        }
    }

    public fun odaberiTrenutniFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fragment, fragment)
            commit()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val data = intent?.data.toString().orEmpty()
        val hash = data.substringAfterLast("androidDone/").substringBeforeLast("/")
//        val dao = AppDatabase.getInstance().accountDao()
//        val repo = AccountRepository(dao)
//        val scope : CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)
//        scope.launch{
//            repo.postaviHash(hash);
//        }
    }
}
