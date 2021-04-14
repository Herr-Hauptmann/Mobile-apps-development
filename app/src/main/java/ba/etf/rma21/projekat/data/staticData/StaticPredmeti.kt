package ba.etf.rma21.projekat.data.staticData

import ba.etf.rma21.projekat.data.models.Predmet

class StaticPredmeti {
    companion object {
        fun dajPredmete(godina: Int): List<Predmet> {
            if (godina == 1) {
                return listOf(
                    Predmet("IM2", 1),
                    Predmet("MLTI", 1),
                    Predmet("OE", 1)
                )
            }

            if (godina == 2) {
                return listOf(
                    Predmet("OOAD", 2),
                    Predmet("RA", 2),
                    Predmet("US", 2)
                )
            }

            if (godina == 3) {
                return listOf(
                    Predmet("OIS", 3),
                    Predmet("WT", 3),
                    Predmet("OOI", 3)
                )
            }

            if (godina == 4) {
                return listOf(
                    Predmet("RV", 4),
                    Predmet("MU", 4),
                    Predmet("NASP", 4)
                )
            }

            if (godina == 5) {
                return listOf(
                    Predmet("MPVI", 5),
                    Predmet("NSI", 5),
                    Predmet("NBP", 5)
                )
            }
            return emptyList()
        }
    }
}