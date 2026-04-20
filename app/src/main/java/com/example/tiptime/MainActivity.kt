package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceApp()
        }
    }
}

// Данные о произведениях искусства
data class Artwork(
    val id: Int,
    val title: String,
    val artist: String,
    val year: Int,
    val description: String,
    val color: Color
)

// Список произведений
private val artworks = listOf(
    Artwork(
        id = 1,
        title = "Звёздная ночь",
        artist = "Винсент Ван Гог",
        year = 1889,
        description = "Одна из самых известных картин в истории искусства.",
        color = Color(0xFF1565C0)
    ),
    Artwork(
        id = 2,
        title = "Мона Лиза",
        artist = "Леонардо да Винчи",
        year = 1503,
        description = "Портрет женщины с загадочной улыбкой.",
        color = Color(0xFF6A1B9A)
    ),
    Artwork(
        id = 3,
        title = "Крик",
        artist = "Эдвард Мунк",
        year = 1893,
        description = "Экспрессионистская картина, передающая чувство тревоги.",
        color = Color(0xFFC62828)
    ),
    Artwork(
        id = 4,
        title = "Постоянство памяти",
        artist = "Сальвадор Дали",
        year = 1931,
        description = "Сюрреалистическая картина с мягкими тающими часами.",
        color = Color(0xFF00695C)
    ),
    Artwork(
        id = 5,
        title = "Рождение Венеры",
        artist = "Сандро Боттичелли",
        year = 1486,
        description = "Изображает богиню Венеру, выходящую из моря.",
        color = Color(0xFF4527A0)
    )
)

@Composable
fun ArtSpaceApp() {
    var currentIndex by remember { mutableStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F5F5)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Карточка с изображением (цветной фон)
            Card(
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(16.dp)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = currentArtwork.color)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "🖼️",
                        fontSize = 64.sp
                    )
                    Text(
                        text = currentArtwork.title,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Карточка с информацией
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = currentArtwork.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${currentArtwork.artist} • ${currentArtwork.year}",
                        fontSize = 14.sp,
                        color = Color(0xFF666666)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = currentArtwork.description,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF888888)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Кнопки навигации
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        currentIndex = if (currentIndex > 0) currentIndex - 1 else artworks.size - 1
                    },
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                ) {
                    Text(text = "◀ Назад")
                }

                Button(
                    onClick = {
                        currentIndex = if (currentIndex < artworks.size - 1) currentIndex + 1 else 0
                    },
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                ) {
                    Text(text = "Вперёд ▶")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceApp()
}