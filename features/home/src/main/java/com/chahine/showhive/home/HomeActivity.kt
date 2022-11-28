package com.chahine.showhive.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.chahine.showhive.base.BaseActivity
import com.chahine.showhive.base.Router
import com.chahine.showhive.base.theme.ShowHiveTheme
import com.chahine.showhive.home.calendar.CalendarScreen
import com.chahine.showhive.home.calendar.CalendarUiModel
import com.chahine.showhive.home.discover.DiscoverScreen
import com.chahine.showhive.home.discover.DiscoverUiModel
import com.chahine.showhive.home.profile.ProfileItem
import com.chahine.showhive.home.profile.ProfileScreen
import com.chahine.showhive.home.util.LoadedValue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    @Inject lateinit var router: Router

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.navigateToSplash.collectLatest {
                router.splash()
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.fetchProfile()
        }

        setContent {
            ShowHiveTheme {
                HomeScreen(
                    viewModel.myCalendar(),
                    viewModel.trending(),
                    viewModel.profile(),
                    viewModel::onSignOutClick
                )
            }
        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    calendarFlow: Flow<PagingData<CalendarUiModel>>,
    discoverFlow: Flow<PagingData<DiscoverUiModel>>,
    profileFlow: Flow<LoadedValue<List<ProfileItem>, Exception>>,
    signOutListener: () -> Unit,
) {
    var selectedItem: Int by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { HomeNavigation(selectedItem) { selectedItem = it } },
    ) { insets: PaddingValues ->
        Surface(modifier = Modifier.padding(insets)) {
            when (selectedItem) {
                0 -> CalendarScreen(calendarFlow)
                1 -> DiscoverScreen(discoverFlow)
                2 -> ProfileScreen(profileFlow, signOutListener)
            }
        }
    }
}

@Composable
fun HomeNavigation(selectedItem: Int, onTabSelected: (Int) -> Unit) {
    val items = listOf(
        R.string.calendar to R.drawable.ic_calendar,
        R.string.discover to R.drawable.ic_television_classic,
        R.string.profile to R.drawable.ic_menu,
    )

    NavigationBar {
        items.forEachIndexed { index, (textResId, iconResId) ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = iconResId), contentDescription = null
                    )
                },
                label = { Text(stringResource(id = textResId)) },
                selected = selectedItem == index,
                onClick = { onTabSelected(index) },
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(apiLevel = 24, showSystemUi = true)
@Preview(apiLevel = 30, showSystemUi = true)
@Preview(apiLevel = 33, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    ShowHiveTheme {
        HomeScreen(emptyFlow(), emptyFlow(), emptyFlow(), {})
    }
}