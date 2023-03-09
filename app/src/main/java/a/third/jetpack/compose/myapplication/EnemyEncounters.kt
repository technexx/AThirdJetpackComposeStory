package a.third.jetpack.compose.myapplication

import android.content.Context

class EnemyEncounters(context: Context) {
    private val creatureArrayEasy = context.resources.getStringArray(R.array.enemies_easy)
    lateinit var currentEnemy : EnemyStats

    var startingEnemyHealth = 0

    data class EnemyStats(val creatureLabel: String, val creatureString: String, val health: Int, val strength: Int, val dexterity: Int, val intellect: Int, val willpower: Int)

    private val enemyList = listOf(
        EnemyStats("child", creatureArrayEasy[0], rolledHealth(3, 5), rolledStrength(2, 3), rolledDexterity(1, 1), rolledIntellect(1, 1), rolledWillpower(2, 3)),
        EnemyStats("chihuahua", creatureArrayEasy[1], rolledHealth(3, 5), rolledStrength(1, 1), rolledDexterity(2, 3), rolledIntellect(1, 2), rolledWillpower(1, 1)),
        EnemyStats("bike wheel", creatureArrayEasy[2], rolledHealth(5, 7), rolledStrength(2, 3), rolledDexterity(2, 3), rolledIntellect(100, 100), rolledWillpower(1, 1)),
        EnemyStats("elderly woman", creatureArrayEasy[3], rolledHealth(1, 1), rolledStrength(4, 5), rolledDexterity(1, 1), rolledIntellect(2, 3), rolledWillpower(2, 3)),
        EnemyStats("baby",creatureArrayEasy[4], rolledHealth(3, 5), rolledStrength(2, 3), rolledDexterity(1, 1), rolledIntellect(1, 1), rolledWillpower(2, 3))
    )

    private fun rolledHealth(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledStrength(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledDexterity(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledIntellect(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledWillpower(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rollStat(min: Int, max: Int) : Int { return (min..max).random() }

    fun assignRandomEnemy() {
        val roll = (enemyList.indices).random()
        currentEnemy = enemyList[roll]

        setStartingEnemyHealth()
    }

    fun setStartingEnemyHealth() { startingEnemyHealth = currentEnemy.health }
}