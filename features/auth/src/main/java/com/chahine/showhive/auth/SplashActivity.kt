package com.chahine.showhive.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.chahine.showhive.base.BaseActivity
import com.chahine.showhive.base.Router
import com.chahine.showhive.base.theme.ShowHiveTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    @Inject lateinit var router: Router

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShowHiveTheme {
                SplashScreen(
                    viewModel::onConnectButtonClick,
                    viewModel::onSkipButtonClick,
                )
            }
        }

        lifecycleScope.launchWhenCreated { viewModel.navigateToTrakt.collectLatest { router.connectWithTrakt() } }
        lifecycleScope.launchWhenCreated { viewModel.navigateToHome.collectLatest { router.home() } }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        viewModel.onNewIntent(intent)
    }
}

private const val HALF_FRACTION = .5f

@Composable
fun SplashScreen(onConnectClick: () -> Unit, onSkipClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(HALF_FRACTION)
                .background(colorResource(R.color.splash_background)),
            content = {},
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(HALF_FRACTION)
                .background(colorResource(R.color.splash_background_variant))
                .align(Alignment.BottomCenter),
            content = {},
        )

        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.mipmap.ic_launcher_foreground),
            contentDescription = null,
            tint = Color.White,
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 32.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.splash_background_accent)),
                onClick = onConnectClick
            ) {
                Text(stringResource(R.string.connect_with_trakt_tv).uppercase())
            }
            TextButton(
                onClick = onSkipClick,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = stringResource(R.string.skip).uppercase(),
                    color = Color.White,
                )
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    ShowHiveTheme {
        SplashScreen({}, {})
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreviewDark() {
    ShowHiveTheme {
        SplashScreen({}, {})
    }
}
