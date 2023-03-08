package a.third.jetpack.compose.myapplication

class PlayerStatRolls {
    var playerStrength = 0
    var playerDexterity = 0
    var playerIntellect = 0
    var playerWillpower = 0
    var statPopulationIterator = 1

    fun rollPlayerStats() {
        playerStrength = initialPlayerStatsRoll()
        playerDexterity = initialPlayerStatsRoll()
        playerIntellect = initialPlayerStatsRoll()
        playerWillpower = initialPlayerStatsRoll()

        initialStatIteration()
    }

    private fun initialPlayerStatsRoll() : Int { return (5..15).random() }

    private fun initialStatIteration() {
        if (pointsTotal() < 40 ) while (pointsTotal() < 40) addToOrSubtractFromStats(true)
        if (pointsTotal() > 40)  while (pointsTotal() > 40) addToOrSubtractFromStats(false)
    }

    private fun pointsTotal() : Int { return playerStrength + playerDexterity + playerIntellect + playerWillpower}

    private fun addToOrSubtractFromStats(isAdding: Boolean) {
        if (statPopulationIterator == 5) statPopulationIterator = 1
        modifyStatByOne(statPopulationIterator, isAdding)

        statPopulationIterator++
    }

    private fun modifyStatByOne(iterator: Int, adding: Boolean) {
        if (iterator == 1) if (adding) playerStrength++ else playerStrength--
        if (iterator == 2) if (adding) playerDexterity++ else playerDexterity--
        if (iterator == 3) if (adding) playerIntellect++ else playerIntellect--
        if (iterator == 4) if (adding) playerWillpower++ else playerWillpower--
    }
}