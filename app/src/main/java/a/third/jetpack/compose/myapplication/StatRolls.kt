package a.third.jetpack.compose.myapplication

class StatRolls {

    data class StatValues(val strength: Int, val dexterity: Int, val intellect: Int, val willpower: Int)

    object EnemyStats {
        val enemyList = listOf(
            StatValues()
        )
    }

    private fun rollStat(min: Int, max: Int) : Int { return (min..max).random() }

    fun rolledStrength(min: Int, max: Int) : Int{ return rollStat(min, max)}

    fun rolledDexterity(min: Int, max: Int) : Int{ return rollStat(min, max)}

    fun rolledIntellect(min: Int, max: Int) : Int{ return rollStat(min, max)}

    fun rolledWillpower(min: Int, max: Int) : Int{ return rollStat(min, max)}

}