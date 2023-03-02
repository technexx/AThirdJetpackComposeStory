package a.third.jetpack.compose.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatsViewModel : ViewModel() {
    val mutableEnergyValue = MutableLiveData<Int>()
    val mutableMoodValue = MutableLiveData<Int>()
    val mutablePhysicalValue = MutableLiveData<Int>()
    val mutableMentalValue = MutableLiveData<Int>()

    val energyValue : LiveData<Int> get() = mutableEnergyValue
    val moodValue : LiveData<Int> get() = mutableMoodValue
    val physicalValue : LiveData<Int> get() = mutablePhysicalValue
    val mentalValue : LiveData<Int> get() = mutableMentalValue


    fun setStatValue(stat: String, value: Int) {
        if (stat == "energy") mutableEnergyValue.value = value
        if (stat == "mood") mutableMoodValue.value = value
        if (stat == "physical") mutablePhysicalValue.value = value
        if (stat == "mental") mutableMentalValue.value = value
    }

    fun getStatValue(stat: String) : Int{
        if (stat == "energy") return energyValue.value!!
        if (stat == "mood") return moodValue.value!!
        if (stat == "physical") return physicalValue.value!!
        if (stat == "mental") return mentalValue.value!!

        return 0
    }

    fun setEnergyValue(valueToAssign: Int) { mutableEnergyValue.value = valueToAssign }
    fun setMoodValue(valueToAssign: Int) { mutableMoodValue.value = valueToAssign }
    fun setPhysicalValue(valueToAssign: Int) { mutablePhysicalValue.value = valueToAssign }
    fun setMentalValue(valueToAssign: Int) { mutableMentalValue.value = valueToAssign }

    fun getEnergyValue() : Int { return energyValue.value!!}
    fun getMoodValue() : Int { return moodValue.value!! }
    fun getPhysicalValue() : Int { return physicalValue.value!!}
    fun getMentalValue() : Int { return mentalValue.value!!}
}