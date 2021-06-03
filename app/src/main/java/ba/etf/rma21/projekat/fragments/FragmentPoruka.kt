//package ba.etf.rma21.projekat.fragments
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.activity.OnBackPressedCallback
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import ba.etf.rma21.projekat.MainActivity
//import ba.etf.rma21.projekat.R
//import ba.etf.rma21.projekat.viewmodel.PredmetViewModel
//
//class FragmentPoruka : Fragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_poruka, container, false)
//        val callback = object : OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//                (context as MainActivity).odaberiTrenutniFragment(FragmentKvizovi())
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(callback)
//        return view
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val model = ViewModelProvider(requireActivity()).get(PredmetViewModel::class.java)
//        val tvPoruka = view.findViewById<TextView>(R.id.tvPoruka)
//        model.trenutniPredmet.observe(viewLifecycleOwner, Observer {
//            tvPoruka.text = "Uspješno ste upisani u grupu ${model.posljednjaGrupa.value} predmeta ${model.posljednjiPredmet.value}!"
//        })
//        model.trenutnaGrupa.observe(viewLifecycleOwner, Observer {
//            tvPoruka.text = "Uspješno ste upisani u grupu ${model.posljednjaGrupa.value} predmeta ${model.posljednjiPredmet.value}!"
//        })
//    }
//}