package a.third.jetpack.compose.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import a.third.jetpack.compose.myapplication.ui.theme.AThirdJetpackComposeStoryTheme
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlin.random.Random
import androidx.activity.viewModels
import androidx.lifecycle.Observer

//Todo: Card based? Follow story w/ different rounds. "Who are you" theme (Planescape: Torment)ish? With characters + helpers.
    //Todo: Ancestral traits/cards to start, since current slate is blank.
//Todo: Dating profile generator
    //Todo: Input (selection) of traits/likes/dislikes/etc.

//Todo: Use MVVM

val statsValues = StatsValues()
private lateinit var statsViewModel : StatsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val statsViewModelInit : StatsViewModel by viewModels()
            statsViewModel = statsViewModelInit

            setStatsValues()

            AThirdJetpackComposeStoryTheme {
                FullView()
            }
        }
    }

    private fun setStatsViewModelObservers() {
        statsViewModel.moodValue.observe(this, {

        })
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
        val (statsLayout, boardLayout) = createRefs()

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
//                .background(colorResource(id = R.color.light_teal)),
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
                    StatTextBody(100, topPadding = 0)

                    StatTextHeader(textString = "Mood", 20, 0, 0, 0)
                    StatTextBody(100, topPadding = 0)
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
                    StatTextBody(100, topPadding = 0)

                    StatTextHeader("Mental", 20, 0, 0, 0)
                    StatTextBody(100, topPadding = 0)
                }
            }
        }

        Column(modifier = Modifier
            .constrainAs(boardLayout) {
                top.linkTo(statsLayout.bottom)
                bottom.linkTo(parent.bottom)
            }
            .fillMaxWidth()
            .height(120.dp)
            .background(color = colorResource(id = R.color.lighter_grey)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
                )
            {
                Button(colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.black)),
                    modifier = Modifier
                        .size(100.dp, 40.dp),
                    onClick = {
                        lifeLeft += addLifeFloat()
                    }) {
                    Text(text = "Live!")
                }

                Spacer(modifier = Modifier.width(40.dp))

                Button(colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.black)),
                    //requiredSize works where size does not.
                    modifier = Modifier
                        .size(100.dp, 40.dp),
                    onClick = {
                        lifeLeft += subtractLifeFloat()
                    }) {
                    Text(text = "Kill!")
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

////////////////////////////////////////////////////////////////////////////////////

private fun addLifeFloat() : Float { return randomFloat(0.05f, 0.1f) }

private fun subtractLifeFloat() : Float { return - (randomFloat(0.05f, 0.1f)) }

private fun randomFloat(min: Float, max: Float) : Float {
    return min + Random.nextFloat() * (max - min)
}

private fun setStatsValues() {
    statsValues.energy = 100
    statsValues.mood = 100
    statsValues.physical = 100
    statsValues.mental = 100
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AThirdJetpackComposeStoryTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background) {
            FullView()
        }
    }
}