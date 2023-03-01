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

    fun setEnergyValue(valueToAssign: Int) { mutableEnergyValue.value = valueToAssign }
    fun setMoodValue(valueToAssign: Int) { mutableMoodValue.value = valueToAssign }
    fun setPhysicalValue(valueToAssign: Int) { mutablePhysicalValue.value = valueToAssign }
    fun setMentalValue(valueToAssign: Int) { mutableMentalValue.value = valueToAssign }

    fun getEnergyValue() : Int { return energyValue.value!!}
    fun getMoodValue() : Int { return moodValue.value!! }
    fun getPhysicalValue() : Int { return physicalValue.value!!}
    fun getMentalValue() : Int { return mentalValue.value!!}

}