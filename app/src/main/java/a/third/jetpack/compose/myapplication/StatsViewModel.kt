package a.third.jetpack.compose.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatsViewModel : ViewModel() {
    val energyValue = MutableLiveData<Int>()
    val moodValue = MutableLiveData<Int>()
    val physicalValue = MutableLiveData<Int>()
    val mentalValue = MutableLiveData<Int>()


}