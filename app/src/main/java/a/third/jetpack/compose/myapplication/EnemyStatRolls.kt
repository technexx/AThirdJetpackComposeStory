package a.third.jetpack.compose.myapplication

class EnemyStatRolls {

    data class EnemyStatValues(val creature: String, val strength: Int, val dexterity: Int, val intellect: Int, val willpower: Int)

    val enemyList = listOf(
        EnemyStatValues("child", rolledStrength(2, 3), rolledDexterity(1, 1), rolledIntellect(1, 1), rolledWillpower(2, 3)),
        EnemyStatValues("teacup chihuahua", rolledStrength(1, 1), rolledDexterity(2, 3), rolledIntellect(1, 2), rolledWillpower(1, 1)),
        EnemyStatValues("bike wheel", rolledStrength(2, 3), rolledDexterity(2, 3), rolledIntellect(100, 100), rolledWillpower(1, 1)),
        EnemyStatValues("elderly woman", rolledStrength(1, 1), rolledDexterity(1, 1), rolledIntellect(2, 3), rolledWillpower(2, 3)),
        EnemyStatValues("baby", rolledStrength(2, 3), rolledDexterity(1, 1), rolledIntellect(1, 1), rolledWillpower(2, 3))

    )

    private fun rolledStrength(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledDexterity(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledIntellect(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rolledWillpower(min: Int, max: Int) : Int{ return rollStat(min, max)}

    private fun rollStat(min: Int, max: Int) : Int { return (min..max).random() }

    fun getRandomEnemy() : EnemyStatValues {
        val roll = (enemyList.indices).random()
        return enemyList[roll]
    }
}