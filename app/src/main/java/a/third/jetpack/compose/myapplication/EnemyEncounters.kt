package a.third.jetpack.compose.myapplication

import android.content.Context

class EnemyEncounters(context: Context) {
    private val creatureArrayEasy = context.resources.getStringArray(R.array.enemies_easy)
    lateinit var currentEnemy : EnemyStats

    data class EnemyStats(val creatureLabel: String, val creatureString: String, val strength: Int, val dexterity: Int, val intellect: Int, val willpower: Int)

    private val enemyList = listOf(
        EnemyStats("child", creatureArrayEasy[0], rolledStrength(2, 3), rolledDexterity(1, 1), rolledIntellect(1, 1), rolledWillpower(2, 3)),
        EnemyStats("chihuahua", creatureArrayEasy[1], rolledStrength(1, 1), rolledDexterity(2, 3), rolledIntellect(1, 2), rolledWillpower(1, 1)),
        EnemyStats("bike wheel", creatureArrayEasy[2], rolledStrength(2, 3), rolledDexterity(2, 3), rolledIntellect(100, 100), rolledWillpower(1, 1)),
        EnemyStats("elderly woman", creatureArrayEasy[3], rolledStrength(1, 1), rolledDexterity(1, 1), rolledIntellect(2, 3), rolledWillpower(2, 3)),
        EnemyStats("baby",creatureArrayEasy[4], rolledStrength(2, 3), rolledDexterity(1, 1), rolledIntellect(1, 1), rolledWillpower(2, 3))

    )

    private fun rolledStrength(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledDexterity(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledIntellect(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledWillpower(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rollStat(min: Int, max: Int) : Int { return (min..max).random() }

    fun assignRandomEnemy() {
        val roll = (enemyList.indices).random()
        currentEnemy = enemyList[roll]
    }
}