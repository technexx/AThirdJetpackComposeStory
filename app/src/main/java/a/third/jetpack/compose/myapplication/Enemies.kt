package a.third.jetpack.compose.myapplication

import android.content.Context

class Enemies(context: Context) {
    private val creatureArrayEasy = context.resources.getStringArray(R.array.enemies_easy)
    var currentEnemy : EnemyStats = EnemyStats("0", "0", 0, 0, 0, 0, 0)

    var startingEnemyHealth = 0

    data class EnemyStats(val creatureLabel: String, val creatureString: String, var health: Int, var strength: Int, var dexterity: Int, var intellect: Int, var willpower: Int)

    private val enemyList = listOf(
        EnemyStats("child", creatureArrayEasy[0], rolledHealth(3, 5), rolledStrength(2, 3), rolledDexterity(1, 1), rolledIntellect(1, 1), rolledWillpower(2, 3)),
        EnemyStats("chihuahua", creatureArrayEasy[1], rolledHealth(3, 5), rolledStrength(1, 1), rolledDexterity(2, 3), rolledIntellect(1, 2), rolledWillpower(1, 1)),
        EnemyStats("bike wheel", creatureArrayEasy[2], rolledHealth(5, 7), rolledStrength(2, 3), rolledDexterity(2, 3), rolledIntellect(100, 100), rolledWillpower(1, 1)),
        EnemyStats("elderly woman", creatureArrayEasy[3], rolledHealth(1, 1), rolledStrength(4, 5), rolledDexterity(1, 1), rolledIntellect(2, 3), rolledWillpower(2, 3)),
        EnemyStats("baby",creatureArrayEasy[4], rolledHealth(3, 5), rolledStrength(2, 3), rolledDexterity(1, 1), rolledIntellect(1, 1), rolledWillpower(2, 3))
    )

    fun assignRandomEnemy() {
        val roll = (enemyList.indices).random()
        currentEnemy = enemyList[roll]

        setStartingEnemyHealth()
    }

    fun damageToEnemy() : Int { return rollDamageToEnemy(1, 2)}

    fun damageFromEnemy() : Int { return rollDamageFromEnemy(1, 2)}

    private fun setStartingEnemyHealth() { startingEnemyHealth = currentEnemy.health }

    fun changeCurrentEnemyHealth (health: Int) { currentEnemy.health = health }

    private fun rolledHealth(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledStrength(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledDexterity(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledIntellect(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledWillpower(min: Int, max: Int) : Int{ return rollStat(min, max)}

    fun rollDamageToEnemy (min: Int, max: Int) : Int{ return rollStat(min, max)}

    fun rollDamageFromEnemy (min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rollStat(min: Int, max: Int) : Int { return (min..max).random() }
}