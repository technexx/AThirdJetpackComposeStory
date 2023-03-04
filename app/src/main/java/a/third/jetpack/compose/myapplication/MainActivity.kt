package a.third.jetpack.compose.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import a.third.jetpack.compose.myapplication.ui.theme.AThirdJetpackComposeStoryTheme
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlin.random.Random
import androidx.activity.viewModels
import kotlin.random.Random.Default.nextInt

//Todo: Card based? Follow story w/ different rounds. "Who are you" theme (Planescape: Torment)ish? With characters + helpers.
    //Todo: Ancestral traits/cards to start, since current slate is blank.
//Todo: Dating profile generator
    //Todo: Input (selection) of traits/likes/dislikes/etc.
    //Todo: Use thesaurus for descriptions entered.

private lateinit var statsDataClass: StatsDataClass
private lateinit var statsViewModel : StatsViewModel
private lateinit var handler : Handler
private lateinit var statBleedRunnable : Runnable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val statsViewModelInit : StatsViewModel by viewModels()
        statsViewModel = statsViewModelInit
        handler = Handler(Looper.getMainLooper())

        //When our moodValue is changed via a UI action, that change is observed by our ViewModel, which then updates the value in our StatsValues class. Our FullView() Composable uses our ViewModel's stat values for its textViews.
        //This is a bit redundant at the moment, since our StatsValues class doesn't actually send anything back to ViewModel (the stat value is already changed), but it lays the groundwork for future changes.
        statsViewModel.moodValue.observe(this) {
            updateStatsDataClassFromViewModel("Mood")
            Log.i("testView", "mood observer called")
        }
        statsViewModel.energyValue.observe(this) {
            updateStatsDataClassFromViewModel("Energy")
            Log.i("testView", "energy observer called")
        }
        statsViewModel.physicalValue.observe(this) {
            updateStatsDataClassFromViewModel("Physical")
            Log.i("testView", "physical observer called")
        }
        statsViewModel.mentalValue.observe(this) {
            updateStatsDataClassFromViewModel("Mental")
            Log.i("testView", "mental observer called")
        }

        setInitialStatsValuesInClass()
        assignStatsValuesToViewModel()

        instantiateStatBleedRunnable()

        setContent {
            AThirdJetpackComposeStoryTheme {
                FullView()
            }
        }
    }
}
private fun updateStatsDataClassFromViewModel(stat: String) {
    if (stat == "Energy") statsDataClass.energy = statsViewModel.getEnergyValue()
    if (stat == "Mood") statsDataClass.mood = statsViewModel.getMoodValue()
    if (stat == "Physical") statsDataClass.physical = statsViewModel.getPhysicalValue()
    if (stat == "Mental") statsDataClass.mental = statsViewModel.getMentalValue()

    Log.i("testView", "energy value in data class is ${statsDataClass.energy}")
    Log.i("testView", "mood value in data class is ${statsDataClass.mood}")
    Log.i("testView", "physical value in data class is ${statsDataClass.physical}")
    Log.i("testView", "mental value in data class is ${statsDataClass.mental}")
}

private fun setInitialStatsValuesInClass() { statsDataClass = StatsDataClass(100, 100, 100, 100) }

private fun assignStatsValuesToViewModel() {
    statsViewModel.setEnergyValue(statsDataClass.energy)
    statsViewModel.setMoodValue(statsDataClass.mood)
    statsViewModel.setPhysicalValue(statsDataClass.physical)
    statsViewModel.setMentalValue(statsDataClass.mental)
}

private fun addStatValueInViewModel(value: Int) {
    val statStringRolled = getRandomStatString()
    val currentStatValue = statsViewModel.getStatValue(statStringRolled)
    val newStatValue = currentStatValue + value

    statsViewModel.setStatValue(statStringRolled, newStatValue)
}

private fun getRandomStatString() : String{
    val roll = (1..4).random()

    if (roll == 1) return "mood"
    if (roll == 2) return "energy"
    if (roll == 3) return "physical"
    if (roll == 4) return "mental"

    return ""
}

private fun randomValueForManualStatChange() : Int { return (5..10).random() }

private fun postStatBleedRunnable() { handler.post(statBleedRunnable) }

//Todo: No remembrance set in composable? Likely not changing because we are not directly interacting w/ UI.
private fun instantiateStatBleedRunnable() {
    statBleedRunnable = Runnable {
        val statToBleed = getRandomStatString()

        if (statToBleed == "mood") statsViewModel.setMoodValue(statsViewModel.getMoodValue() -1)
        if (statToBleed == "energy") statsViewModel.setEnergyValue(statsViewModel.getEnergyValue() -1)
        if (statToBleed == "physical") statsViewModel.setPhysicalValue(statsViewModel.getPhysicalValue() -1)
        if (statToBleed == "mental") statsViewModel.setMentalValue(statsViewModel.getMentalValue() -1)

//        Log.i("testRunnable", "running with $statToBleed")
        Log.i("testRunnable", "mood is ${statsViewModel.getMoodValue()}")

        handler.postDelayed(statBleedRunnable,100)
    }
}

//*** Alignment modifiers affect the CHILDREN of rows/columns, not the rows/columns themselves.
//*** You can use a float fraction inside maxWidth/maxHeight to lessen size.
@Composable
fun FullView() {
    ConstraintLayout (modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.white))
    ) {
        val startGuideline = createGuidelineFromTop(0.25f)
        val (statsLayout, centerLayout, userButtonLayout) = createRefs()

        var lifeLeft by remember { mutableStateOf(1.0f) }

        Column (modifier = Modifier
            .constrainAs(statsLayout) {
                top.linkTo(parent.top)
                bottom.linkTo(startGuideline)
            }
            .fillMaxWidth()
            .height(150.dp)
            .background(color = colorResource(id = R.color.lighter_grey)),
        ) {
            Row(modifier = Modifier
                .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
                    .background(colorResource(id = R.color.android_magenta)),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    StatTextHeader(textString = "Energy", 0, 0, 0, 0)
                    StatTextBody(statsViewModel.getEnergyValue(), topPadding = 0)

                    StatTextHeader(textString = "Mood", 20, 0, 0, 0)
                    StatTextBody(statsViewModel.getMoodValue(), topPadding = 0)
                }

                Column(modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
                    .background(colorResource(id = R.color.very_light_grey)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.boxman_2),
                        contentDescription = "Box Man",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .alpha(lifeLeft)
                    )
                }

                Column(modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
                    .background(colorResource(id = R.color.lighter_green)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StatTextHeader("Physical",0, 0, 0, 0)
                    StatTextBody(statsViewModel.getPhysicalValue(), topPadding = 0)

                    StatTextHeader("Mental", 20, 0, 0, 0)
                    StatTextBody(statsViewModel.getMentalValue(), topPadding = 0)
                }
            }
        }

        Column(modifier = Modifier
            .constrainAs(centerLayout) {
                bottom.linkTo(userButtonLayout.top)
            }
            .fillMaxWidth()
            .height(120.dp)
            .background(color = colorResource(id = R.color.fade_white_2)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            Button(colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.black)),
                onClick = {
                    postStatBleedRunnable()
            }) {
                Text(text = "Let's Go!", color = Color.White, fontSize = 20.sp)
            }
        }

        Column(modifier = Modifier
            .constrainAs(userButtonLayout) {
                bottom.linkTo(parent.bottom)
            }
            .fillMaxWidth()
            .height(150.dp)
            .background(color = colorResource(id = R.color.lighter_grey)),
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center

            ) {
                Button(colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white)),
                    modifier = Modifier
                        .size(120.dp, 40.dp),
                    onClick = {
                        addStatValueInViewModel(randomValueForManualStatChange())
                    }) {
                    Text(text = "Energize!!")
                }

                Spacer(modifier = Modifier.width(100.dp))

                Button(colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white)),
                    modifier = Modifier
                        .size(120.dp, 40.dp),
                    onClick = {
                        addStatValueInViewModel(randomValueForManualStatChange())
                    }) {
                    Text(text = "Buffify!!")

                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white)),
                    modifier = Modifier
                        .size(120.dp, 40.dp),
                    onClick = {
                        addStatValueInViewModel(randomValueForManualStatChange())
                    }) {
                    Text(text = "Moodify!!")
                }

                Spacer(modifier = Modifier.width(100.dp))

                Button(colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white)),
                    modifier = Modifier
                        .size(120.dp, 40.dp),
                    onClick = {
                        addStatValueInViewModel(randomValueForManualStatChange())
                    }) {
                    Text(text = "Neurofy!!!")
                }
            }
        }
    }
}

@Composable
private fun StatTextHeader(textString: String, topPadding: Int, bottomPadding: Int, startPadding: Int, endPadding: Int) {
    Text(text = textString, color = Color.Black, fontSize = 18.sp,
        modifier = Modifier
            .padding(top = topPadding.dp)
            .padding(bottom = bottomPadding.dp)
            .padding(start = startPadding.dp)
            .padding(end = endPadding.dp)
    )
}

@Composable
private fun StatTextBody(statValue: Int,  topPadding: Int) {
    Text(text = statValue.toString(), color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(top = topPadding.dp)
    )
}

@Composable
fun VisibleGameCards(cards: List<CardValues>) {
    LazyRow {
        items(cards) { cardsShown ->
            StuffInCards(cardsShown)
        }
    }
}

@Composable
fun StuffInCards(cardValues: CardValues) {

}

data class CardValues(val energyMod: Int, val moodMod: Int, val physicalMod: Int, val mentalMod: Int)

@Preview
@Composable
fun DefaultPreview() {
    AThirdJetpackComposeStoryTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background) {
            FullView()
        }
    }
}