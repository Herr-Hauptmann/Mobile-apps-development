package ba.etf.rma21.projekat

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MySpirala2AndroidTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun backButtonOnKviz() {
        //Back na prikazu kvizova treba da ostane na prikazu kvizova
        Espresso.pressBack()
        assertTrue(Espresso.onView(ViewMatchers.withId(R.id.filterKvizova))!=null)
    }

    @Test
    fun backButtonOnPredmet() {
        //Back dugme na predmetu treba da se vrati na prikaz kvizova
        Espresso.onView(ViewMatchers.withId(R.id.predmeti)).perform(ViewActions.click())
        Espresso.pressBack()
        assertTrue(Espresso.onView(ViewMatchers.withId(R.id.filterKvizova))!=null)
    }

    @Test
    fun backButtonOnPoruka() {
        //Back dugme nakon upisa predmeta back dugme treba da nas vrati na prikaz kvizova
        Espresso.onView(ViewMatchers.withId(R.id.predmeti)).perform(ViewActions.click())

        //Upisujem MLTi
        Espresso.onView(ViewMatchers.withId(R.id.odabirGodina)).perform(ViewActions.click())
        Espresso.onView(withText("1")).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.odabirPredmet)).perform(ViewActions.click())
        Espresso.onView(withText("MLTI")).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.odabirGrupa)).perform(ViewActions.click())
        Espresso.onView(withText("Grupa 1")).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.dodajPredmetDugme)).perform(ViewActions.click())

        //Vrati se nazad
        Espresso.pressBack()
        assertTrue(Espresso.onView(ViewMatchers.withId(R.id.filterKvizova))!=null)
    }

}