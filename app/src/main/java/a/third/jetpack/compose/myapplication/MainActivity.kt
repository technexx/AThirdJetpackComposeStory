package a.third.jetpack.compose.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import a.third.jetpack.compose.myapplication.ui.theme.AThirdJetpackComposeStoryTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.NonDisposableHandle.parent

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

        //Todo: Should Column come before surface?
        //Stats surface.
        Surface(color = MaterialTheme.colors.primary,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
//                .height(IntrinsicSize.Max)
                .constrainAs(statsLayout) {
                    top.linkTo(parent.top)
                    bottom.linkTo(startGuideline)
                }

        ) {
            Column (modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //Todo: Size here affects Surface parent.
                Image(
                    painter = painterResource(id = R.drawable.boxman_2),
                    contentDescription = "Boxman",
                    modifier = Modifier
                        .size(80.dp)
                )
            }
        }

        Surface(modifier = Modifier
            .constrainAs(boardLayout) {
                top.linkTo(statsLayout.bottom)
            }
            .fillMaxSize(),
            color = MaterialTheme.colors.onBackground) {
            //Todo: Button fills entire screen
            Button(
                //requiredSize works where size does not
                modifier = Modifier
                .requiredSize(120.dp, 40.dp),
                onClick = {
                    testClick()
                }) {
                Text(text = "Click me!")
            }
        }
    }
}

private fun testClick() {

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