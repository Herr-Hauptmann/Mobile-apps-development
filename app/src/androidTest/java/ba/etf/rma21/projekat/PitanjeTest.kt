//package ba.etf.rma21.projekat
//
//import androidx.test.espresso.intent.rule.IntentsTestRule
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import org.junit.Rule
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class PitanjeTest {
//    @get:Rule
//    val intentsTestRule = IntentsTestRule<MainActivity>(MainActivity::class.java)
//
////    @Test
////    fun ucitajuSePitanja() {
////        onView(withId(R.id.filterKvizova)).perform(click())
////        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("Svi moji kvizovi"))).perform(click())
////        val kvizovi = KvizRepository.getMyKvizes()
////        onView(withId(R.id.listaKvizova)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(CoreMatchers.allOf(hasDescendant(withText(kvizovi[0].naziv)),
////            hasDescendant(withText(kvizovi[0].nazivPredmeta))), click()))
////        onView(withId(R.id.navigacijaPitanja)).check(matches(isDisplayed()))
////        onView(withId(R.id.navigacijaPitanja)).check(matches(isDisplayed()))
////        val pitanja = PitanjeKvizRepository.getPitanja(kvizovi[0].naziv, kvizovi[0].nazivPredmeta)
////        var indeks = 0
////        for (pitanje in pitanja) {
////            onView(withId(R.id.navigacijaPitanja)).perform(NavigationViewActions.navigateTo(indeks))
////            onView(withId(R.id.tekstPitanja)).check(matches(withText(pitanja[indeks].tekst)))
////            indeks++
////        }
////    }
//
////    @Test
////    fun testPredajKviz() {
////        onView(withId(R.id.filterKvizova)).perform(click())
////        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("Svi moji kvizovi"))).perform(click())
////        val kvizovi = KvizRepository.getMyKvizes()
////        onView(withId(R.id.listaKvizova)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(CoreMatchers.allOf(hasDescendant(withText(kvizovi[0].naziv)),
////            hasDescendant(withText(kvizovi[0].nazivPredmeta))), click()))
////        onView(withId(R.id.navigacijaPitanja)).check(matches(isDisplayed()))
////        onView(withId(R.id.predajKviz)).perform(click())
////        onView(withSubstring("Završili ste kviz")).check(matches(isDisplayed()))
////    }
//
////    @Test
////    fun prikazujeSeIspravanBottomNav() {
////        onView(withId(R.id.filterKvizova)).perform(click())
////        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("Svi moji kvizovi"))).perform(click())
////        val kvizovi = KvizRepository.getMyKvizes()
////        onView(withId(R.id.listaKvizova)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(CoreMatchers.allOf(hasDescendant(withText(kvizovi[0].naziv)),
////            hasDescendant(withText(kvizovi[0].nazivPredmeta))), click()))
////        onView(withId(R.id.predajKviz)).check(matches(isDisplayed()))
////        onView(withId(R.id.zaustaviKviz)).check(matches(isDisplayed()))
////        onView(withId(R.id.kvizovi)).check(matches(not(isDisplayed())))
////        onView(withId(R.id.predmeti)).check(matches(not(isDisplayed())))
////    }
//}