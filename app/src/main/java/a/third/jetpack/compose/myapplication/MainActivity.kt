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
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlin.random.Random
import kotlin.random.nextInt

//Todo: Card based? Follow story w/ different rounds. "Who are you" theme (Planescape: Torment)ish? With characters + helpers.
    //Todo: Ancestral traits/cards to start, since current slate is blank.
//Todo: Dating profile generator
    //Todo: Input (selection) of traits/likes/dislikes/etc.

//var lifeLeft = 1.0f

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AThirdJetpackComposeStoryTheme {
                Surface(color = MaterialTheme.colors.background) {
                    FullView()
                }
            }
        }
    }
}

@Composable
fun FullView() {
    ConstraintLayout (modifier = Modifier
        .fillMaxSize()
    ) {
        val startGuideline = createGuidelineFromTop(0.2f)
        val (statsLayout, boardLayout) = createRefs()

        var lifeLeft by remember { mutableStateOf(1.0f) }


        Column (modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.light_grey))
            .constrainAs(statsLayout) {
                top.linkTo(parent.top)
                bottom.linkTo(startGuideline)
            }
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //Size here affects Surface parent.
            Image(
                painter = painterResource(id = R.drawable.boxman_2),
                contentDescription = "Box Man",
                modifier = Modifier

                    .width(80.dp)
                    .height(80.dp)
                    .alpha(lifeLeft)
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.teal_200))
            .constrainAs(boardLayout) {
                top.linkTo(startGuideline)
            }
        ) {
            Button(colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.black)),
                //requiredSize works where size does not.
                modifier = Modifier
                    .requiredSize(120.dp, 40.dp),
                onClick = {
                    lifeLeft += subtractLifeFloat()
                    Log.i("testLife", "life left is $lifeLeft")
                }) {
                Text(text = "Click me!")
            }
        }
    }
}

private fun addLifeFloat() : Float { return randomFloat(0.05f, 0.1f) }

private fun subtractLifeFloat() : Float { return - (randomFloat(0.05f, 0.1f)) }

private fun randomFloat(min: Float, max: Float) : Float {
    return min + Random.nextFloat() * (max - min)
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