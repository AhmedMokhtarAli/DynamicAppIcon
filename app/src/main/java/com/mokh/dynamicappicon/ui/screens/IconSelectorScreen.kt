import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mokh.dynamicappicon.R
import com.mokh.dynamicappicon.model.AppIconModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconSelectorScreen(
    appIcons: List<AppIconModel>,
    onRemoteConfigEnabled: () -> Unit,
    onIconSelected: (AppIconModel) -> Unit,
    currentIcon: AppIconModel? = null,
    remoteIcon: AppIconModel? = null,
) {
    var selectedIconIndex by remember { mutableStateOf<Int?>(appIcons.indexOfFirst { it.aliasName == currentIcon?.aliasName }) }
    var isRemoteConfigEnabled by remember { mutableStateOf(remoteIcon != null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.select_app_icon),
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                )
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.enable_remote_config),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                )
                Switch(checked = isRemoteConfigEnabled, onCheckedChange = { isChecked ->
                    isRemoteConfigEnabled = isChecked
                    if (isChecked) {
                        selectedIconIndex = null
                        onRemoteConfigEnabled()
                    } else {
                        selectedIconIndex = 0
                        onIconSelected(appIcons[selectedIconIndex!!])
                    }
                })

            }
            if (isRemoteConfigEnabled && remoteIcon != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AppIconButton(
                        icon = remoteIcon,
                        isSelected = true,
                        onClick = {
                            onIconSelected(remoteIcon)
                        }
                    )
                }
                Text(
                    text = stringResource(remoteIcon.titleResId),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                        .align(Alignment.CenterHorizontally)
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
            }
            LazyVerticalGrid(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 12.dp)
            ) {
                itemsIndexed(appIcons) { index, icon ->
                    var visible by remember { mutableStateOf(false) }
                    val scale by animateFloatAsState(
                        targetValue = if (visible) 1f else 0.8f,
                        animationSpec = spring(dampingRatio = 0.6f)
                    )

                    LaunchedEffect(Unit) {
                        visible = true
                    }

                    Box(
                        modifier = Modifier
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                alpha = if (visible) 1f else 0f
                            }
                    ) {
                        AppIconButton(
                            icon = icon,
                            isSelected = selectedIconIndex == index,
                            onClick = {
                                selectedIconIndex = if (selectedIconIndex == index) null else index
                                onIconSelected(icon)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AppIconButton(
    icon: AppIconModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    var selected by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = when {
            selected -> 1.1f
            isPressed -> 0.9f
            else -> 1f
        },
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 500f)
    )

    val elevation by animateDpAsState(
        targetValue = when {
            selected -> 12.dp
            isPressed -> 4.dp
            else -> 8.dp
        }
    )

    val strokeWidth by animateDpAsState(
        targetValue = if (isSelected) 3.dp else 0.dp,
        animationSpec = tween(durationMillis = 200)
    )
    val strokeColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else Color.Transparent,
        animationSpec = tween(durationMillis = 200)
    )

    Card(
        modifier = Modifier
            .size(100.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    selected = !selected
                    onClick()
                }
            )
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        colors = CardDefaults.cardColors(icon.color.copy(alpha = 0.9f)),
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = strokeWidth,
                    color = strokeColor,
                    shape = CircleShape
                )
                .padding(strokeWidth)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(icon.iconResId ?: R.drawable.ic_lanucher),
                contentDescription = stringResource(icon.titleResId),
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}