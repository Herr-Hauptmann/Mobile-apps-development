package ba.etf.rma21.projekat.data.staticData

import ba.etf.rma21.projekat.data.models.Predmet

class StaticPredmeti {
    companion object {
        fun dajPredmete(godina: Int): List<Predmet> {
            if (godina == 1) {
                return listOf(
                    Predmet("IM2", 1),
                    Predmet("MLTI", 1),
                    Predmet("OE", 1),
                    Predmet("OS", 1),
                    Predmet("TP", 1),
                    Predmet("OR", 1),
                    Predmet("VIS", 1),
                    Predmet("IF1", 1)
                )
            }

            if (godina == 2) {
                return listOf(
                    Predmet("OOAD", 2),
                    Predmet("RA", 2),
                    Predmet("US", 2),
                    Predmet("IEK", 2),
                    Predmet("ORM", 2),
                    Predmet("CCI", 2),
                    Predmet("AFJ", 2),
                    Predmet("DPS", 2),
                    Predmet("OTK", 2)
                )
            }

            if (godina == 3) {
                return listOf(
                    Predmet("OIS", 3),
                    Predmet("WT", 3),
                    Predmet("RG", 3),
                    Predmet("OOI", 3),
                    Predmet("PJP", 3),
                    Predmet("VVS", 3),
                    Predmet("PVS", 3)
                )
            }
            return emptyList()
        }
    }
}