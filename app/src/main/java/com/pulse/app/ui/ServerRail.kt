package com.pulse.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pulse.app.model.MockData
import com.pulse.app.model.Server

@Composable
fun ServerRail(
    selectedId: String,
    personalMode: Boolean,
    onSelectServer: (Server) -> Unit,
    onOpenPersonal: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(72.dp)
            .background(DarkSidebar),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(12.dp))

        Surface(
            modifier = Modifier
                .size(48.dp)
                .clip(if (personalMode) RoundedCornerShape(16.dp) else CircleShape)
                .clickable { onOpenPersonal() },
            color = PulseAccent
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Icon(Icons.Default.Chat, contentDescription = "Личные", tint = Color.White)
            }
        }

        Spacer(Modifier.height(12.dp))
        Box(Modifier.width(32.dp).height(2.dp).background(DarkDivider))
        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(MockData.servers) { server ->
                val selected = !personalMode && server.id == selectedId
                Box {
                    Surface(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(if (selected) RoundedCornerShape(16.dp) else CircleShape)
                            .clickable { onSelectServer(server) },
                        color = Color(server.accentColor)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            Text(server.emoji, fontSize = 22.sp)
                        }
                    }
                    if (server.unread > 0) {
                        Surface(
                            modifier = Modifier.align(Alignment.BottomEnd).size(20.dp),
                            color = PulseDanger,
                            shape = CircleShape
                        ) {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = if (server.unread > 9) "9+" else server.unread.toString(),
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(Modifier.height(4.dp))
                Surface(
                    modifier = Modifier.size(48.dp).clip(CircleShape),
                    color = DarkPanel
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Icon(Icons.Default.Add, contentDescription = "Добавить", tint = PulseOnline)
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))
        Surface(
            modifier = Modifier.size(44.dp).clip(CircleShape),
            color = Color(0xFFEB459E)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("🐱", fontSize = 22.sp)
            }
        }
        Spacer(Modifier.height(12.dp))
    }
}
