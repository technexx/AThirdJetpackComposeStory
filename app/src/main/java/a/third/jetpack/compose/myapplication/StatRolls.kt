package a.third.jetpack.compose.myapplication

class StatRolls {
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

    private fun pointsTotal() : Int { return playerStrength + playerDexterity + playerIntellect + playerWillpower}

    private fun initialStatIteration() {
        if (pointsTotal() < 40 ) while (pointsTotal() < 40) addOrSubtractInitialStats(true)
        if (pointsTotal() > 40)  while (pointsTotal() > 40) addOrSubtractInitialStats(false)
    }

    private fun addOrSubtractInitialStats(isAdding: Boolean) {
        if (statPopulationIterator > 5) statPopulationIterator = 1
        modifyStatByOne(statPopulationIterator, isAdding)

        statPopulationIterator++
    }

    private fun modifyStatByOne(iterator: Int, adding: Boolean) {
        if (iterator == 1) if (adding) playerStrength ++ else playerStrength --
        if (iterator == 2) if (adding) playerDexterity ++ else playerDexterity --
        if (iterator == 3) if (adding) playerIntellect ++ else playerIntellect --
        if (iterator == 4) if (adding) playerWillpower ++ else playerWillpower --
    }

    private fun initialPlayerStatsRoll() : Int { return (5..15).random() }

    data class EnemyStatValues(val creature: String, val strength: Int, val dexterity: Int, val intellect: Int, val willpower: Int)

    val enemyList = listOf(
        EnemyStatValues("child", rolledStrength(2, 3), rolledDexterity(1, 1), rolledIntellect(1, 1), rolledWillpower(2, 3)),
        EnemyStatValues("teacup chihuahua", rolledStrength(1, 1), rolledDexterity(2, 3), rolledIntellect(1, 2), rolledWillpower(1, 1)),
        EnemyStatValues("bike wheel", rolledStrength(2, 3), rolledDexterity(2, 3), rolledIntellect(100, 100), rolledWillpower(1, 1))
    )


    private fun rolledStrength(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledDexterity(min: Int, max: Int) : Int{ return rollStat(min, max)}

    protected fun rolledIntellect(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledWillpower(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rollStat(min: Int, max: Int) : Int { return (min..max).random() }

}