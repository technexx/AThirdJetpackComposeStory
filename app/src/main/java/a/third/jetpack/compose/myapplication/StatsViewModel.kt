package a.third.jetpack.compose.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatsViewModel : ViewModel() {
    val mutableStrengthValue = MutableLiveData<Int>()
    val mutableDexterityValue = MutableLiveData<Int>()
    val mutableIntellectValue = MutableLiveData<Int>()
    val mutableWillpowerValue = MutableLiveData<Int>()

    val strengthValue : LiveData<Int> get() = mutableStrengthValue
    val dexterityValue : LiveData<Int> get() = mutableDexterityValue
    val intellectValue : LiveData<Int> get() = mutableIntellectValue
    val willpowerValue : LiveData<Int> get() = mutableWillpowerValue


    fun setStatValue(stat: String, value: Int) {
        if (stat == "strength") mutableStrengthValue.value = value
        if (stat == "dexterity") mutableDexterityValue.value = value
        if (stat == "intellect") mutableIntellectValue.value = value
        if (stat == "willpower") mutableWillpowerValue.value = value
    }

    fun getStatValue(stat: String) : Int{
        if (stat == "strength") return strengthValue.value!!
        if (stat == "dexterity") return dexterityValue.value!!
        if (stat == "intellect") return intellectValue.value!!
        if (stat == "willpower") return willpowerValue.value!!

        return 0
    }

    fun setStrengthValue(valueToAssign: Int) { mutableStrengthValue.value = valueToAssign }
    fun setDexterityValue(valueToAssign: Int) { mutableDexterityValue.value = valueToAssign }
    fun setIntellectValue(valueToAssign: Int) { mutableIntellectValue.value = valueToAssign }
    fun setWillpowerValue(valueToAssign: Int) { mutableWillpowerValue.value = valueToAssign }

    fun getStrengthValue() : Int { return strengthValue.value!! }
    fun getDexterityValue() : Int { return dexterityValue.value!! }
    fun getIntellectValue() : Int { return intellectValue.value!! }
    fun getWillpowerValue() : Int { return willpowerValue.value!! }
}