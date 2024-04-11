package fr.aderugy.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import fr.aderugy.movieapp.models.Movie

val RobotoFontFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val RobotoTypography = androidx.compose.material3.Typography(
    displayLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 57.sp
    ),
    displayMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp
    ),
    displaySmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
)

val Movies: Array<Movie> = arrayOf(
    Movie(
        name = "Kung Fu Panda 4",
        certification = "PG",
        image = "https://www.myvue.com/-/jssmedia/vuecinemas/img/import/a223aaeb-6a2d-4561-92f0-e3efa105a21e_kung-fu-panda-4_posters_kf4_intl_dgtl_payoff_1sheet_uk_712px.jpg?mw=450&rev=7239547d058742c7b4ee0899fe51c29e",
        description = "After Po is tapped to become the Spiritual Leader of the Valley of Peace, he needs to find and train a new Dragon Warrior, while a wicked sorceress plans to re-summon all the master villains whom Po has vanquished to the spirit realm.",
        running_time_mins = 94,
        starring = arrayOf( "Jack Black" ,"Awkwafina", "Viola David", "Dustin Hoffman", "Bryan Cranston", "Mr Beast" )
    ),
    Movie(
        name = "CIVIL WAR",
        certification = "15A",
        image = "https://www.myvue.com/-/jssmedia/vuecinemas/film-and-events/apr-2024/civil-war_sun_1sht_imax.jpg?mw=450&rev=6eb928814f0f4676a666f6f9552ff2e4",
        description = "In the near future, a team of journalists travel across the United States during a rapidly escalating civil war that has engulfed the entire nation.",
        running_time_mins = 109,
        starring = arrayOf("Wagner Moura", "Kirsten Dunst", "Cailee Spaeny", "Stephen McKinley Henderson")
    ),
    Movie(
        name = "DUNE: PART 2",
        certification = "12A",
        image = "https://www.myvue.com/-/jssmedia/vuecinemas/img/import/7a7a20aa-1064-46fd-96cc-4b271268f2c5_dune-part-ii_posters_one-sheet_712px.jpg?mw=450&rev=c61dcb539042435c973daaeeb97100b6",
        description = "Paul Atreides unites with Chani and the Fremen while on a warpath of revenge against the conspirators who destroyed his family.",
        running_time_mins = 166,
        starring = arrayOf("Timothée Chalamet", "Florence Pugh", "Zendaya ", "Souheila Yacoub", "Austin Butler")
    ),
    Movie(
        name = "Monkey Man",
        certification = "16",
        image = "https://www.myvue.com/-/jssmedia/vuecinemas/img/import/97cfbf7b-19b4-472f-8790-77bd719b14e9_monkey-man_posters_mkm_digital_teaser_1sheet_712px.jpg?mw=450&rev=63ed7297b49b461088fa5e2cec58b540",
        description = "Oscar® nominee Dev Patel (Lion, Slumdog Millionaire) achieves an astonishing, tour-de-force feature directing debut with an action thriller about one man’s quest.",
        running_time_mins = 121,
        starring = arrayOf("Dev Patel", "Sharlto Copley", "Pitobash", "Adithi Kalkunte", "Sobhita Dhulipala")
    ),
);

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun MovieAppTheme(content: @Composable () -> Unit) {
    val colors = darkColorScheme(
        background = Color(0xFF1E2129),
        onBackground = Color.White,
    )

    MaterialTheme(
        colorScheme = colors,
        typography = RobotoTypography,
        content = content
    )
}

@Composable
fun App() {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "movies",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("movies") {
                MoviesPage(navController)
            }
            composable("movieDetail/{movieIndex}") { backStackEntry ->
                val movieIndex = backStackEntry.arguments?.getString("movieIndex")?.toIntOrNull() ?: 0
                MovieDetailPage(movie = Movies[movieIndex], navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesPage(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                        Text("Movies")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column( modifier = Modifier
            .padding(innerPadding)) {

            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                items(Movies.indices.toList()) { index ->
                    val movie = Movies[index]
                    MovieItem(movie = movie, left = index % 2 == 0) {
                        navController.navigate("movieDetail/$index")
                    }
                    if (index < Movies.size - 1) {
                        MoviesPageDivider()
                    }
                    else {
                        Text(text = "Data credits: https://www.myvue.com/cinema/dublin/whats-on")
                    }
                }
            }

        }
    }
}


@Composable
fun MovieItem(movie: Movie, left: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
            .height(250.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        if (left) {
            MovieImage(image = movie.image)
            Spacer(modifier = Modifier.width(8.dp))
            MovieTexts(movie, Modifier.weight(1f))
        } else {
            MovieTexts(movie, Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            MovieImage(image = movie.image)
        }
    }
}


@Composable
fun MovieImage(image: String) {
    Image(
        painter = rememberAsyncImagePainter(image),
        contentDescription = "fr.aderugy.movieapp.models.Movie Poster",
        modifier = Modifier
            .fillMaxHeight(),
        contentScale = ContentScale.FillHeight
    )
}

@Composable
fun MovieTexts(movie: Movie, modifier: Modifier = Modifier) {
    Column( modifier = modifier) {
        Text(
            text = movie.name,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "${movie.certification} certification",
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = movie.description,
            style = TextStyle(
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun MoviesPageDivider() {
    Spacer(modifier = Modifier.height(16.dp))
    Divider(color = Color.Gray, thickness = 1.dp)
    Spacer(modifier = Modifier.height(16.dp))
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailPage(navController: NavHostController, movie: Movie) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                        Text(movie.name)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(movie.image),
                contentDescription = "fr.aderugy.movieapp.models.Movie Poster",
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(20.dp))
            Column {
                Text(
                    text = "${movie.name} (${movie.certification})",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = movie.description,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray
                    ),
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row {
                    Text(
                        text = "Starring",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = movie.starring.joinToString(", "),
                        style = TextStyle(color = Color.Gray)
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Row {

                    Text(
                        text = "Running time",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${movie.running_time_mins / 60}hr ${movie.running_time_mins % 60}min",
                        style = TextStyle(color = Color.Gray)
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Select Seats", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Button(
                            onClick = {
                                if (movie.seats_selected > 0) {
                                    movie.seats_selected--
                                    movie.seats_remaining++
                                }
                            },
                            enabled = movie.seats_selected > 0,
                            shape = CircleShape
                        ) {
                            Text("-", textAlign = TextAlign.Center)
                        }
                        Text("${movie.seats_selected}", Modifier.padding(horizontal = 8.dp))
                        Button(
                            onClick = {
                                if (movie.seats_remaining > 0) {
                                    movie.seats_selected++
                                    movie.seats_remaining--
                                }
                            },
                            enabled = movie.seats_remaining > 0,
                            shape = CircleShape
                        ) {
                            Text("+", textAlign = TextAlign.Center)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Seats")
                    Text("${movie.seats_remaining} remaining")
                }
            }
        }
    }
}
