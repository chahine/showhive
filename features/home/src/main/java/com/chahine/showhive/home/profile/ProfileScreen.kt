package com.chahine.showhive.home.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chahine.showhive.home.R
import kotlinx.coroutines.flow.Flow

@Composable
fun ProfileScreen(profileFlow: Flow<List<ProfileItem>>, signOutListener: () -> Unit) {

    val profileItems: List<ProfileItem> by profileFlow.collectAsState(emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(profileItems, key = { it.id }) { profileItem ->
            when (profileItem) {
                is ProfileItem.Error -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Something went wrong!",
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                }
                ProfileItem.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
                is ProfileItem.UserCard -> {
                    ProfileUserCard(profileItem, signOutListener)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileUserCard(userCard: ProfileItem.UserCard, signOutListener: () -> Unit) {
    GlideImage(
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.avatar_size))
            .clip(CircleShape),
        model = userCard.user.images?.avatar?.full,
        contentDescription = null,
    )

    val nameText = if (!userCard.user.name.isNullOrBlank()) {
        userCard.user.name
    } else {
        userCard.user.username
    }

    nameText?.let {
        Text(
            text = nameText,
            style = MaterialTheme.typography.titleMedium,
        )
    }
    userCard.user.location?.let {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.padding(2.dp),
                painter = painterResource(R.drawable.ic_baseline_location_on),
                contentDescription = null,
            )
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
    userCard.user.about?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.titleSmall,
        )
    }

    Button(onClick = signOutListener) {
        Text(text = stringResource(id = R.string.sign_out_cta))
    }
}
