package a.third.jetpack.compose.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import a.third.jetpack.compose.myapplication.ui.theme.AThirdJetpackComposeStoryTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout

//Todo: Card based? Follow story w/ different rounds. "Who are you" theme (Planescape: Torment)ish? With characters + helpers.
    //Todo: Ancestral traits/cards to start, since current slate is blank.
//Todo: Dating profile generator
    //Todo: Input (selection) of traits/likes/dislikes/etc.


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AThirdJetpackComposeStoryTheme {

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.White) {
                    FullView()
                }
            }
        }
    }
}

@Composable
fun FullView() {
    ConstraintLayout {
        val (statsAndBody, playArea) = createRefs()

        val startGuideline = createGuidelineFromTop(0.3f)

        StatsAndBody(modifier = Modifier
            .constrainAs(statsAndBody) {
                top.linkTo(startGuideline)
            }
        )

    }
}

@Composable
fun StatsAndBody(modifier: Modifier) {
    ConstraintLayout {
        //Constrains our surface as done in FullView()
        Surface(modifier = modifier) {
            //Todo: Image visible, but very dark background.
            Image(
                painter = painterResource(id = R.drawable.boxman),
                contentDescription = "Boxman",
                modifier = Modifier
                    .size(80.dp)
            )
        }
    }
}

@Composable
fun PlayArea() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AThirdJetpackComposeStoryTheme {

    }
}