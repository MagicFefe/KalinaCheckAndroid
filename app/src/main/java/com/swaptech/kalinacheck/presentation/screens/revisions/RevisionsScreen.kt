package com.swaptech.kalinacheck.presentation.screens.revisions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.swaptech.kalinacheck.R
import com.swaptech.kalinacheck.presentation.screens.attachments.AttachmentsScreenViewModel
import com.swaptech.kalinacheck.presentation.theme.Beige
import com.swaptech.kalinacheck.presentation.utils.RevisionItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RevisionsScreen(
    viewModel: AttachmentsScreenViewModel
) {
    val pagerState = rememberPagerState()
    val pages = listOf(
        stringResource(R.string.active_revisions),
        stringResource(R.string.history_revisions)
    )
    val scope = rememberCoroutineScope()
    val active = (0..100).map { it.toString() }
    val history = (101..201).map { it.toString() }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            modifier = Modifier.graphicsLayer {
                shadowElevation = (4.dp).value
            },
            backgroundColor = Beige,
            contentColor = MaterialTheme.colors.primary,
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    color = MaterialTheme.colors.primary
                )
            }
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = title)
                    }
                )
            }
        }
        HorizontalPager(
            count = pages.size,
            state = pagerState
        ) {
            when (pagerState.currentPage) {
                0 -> {
                    ActiveRevisions(
                        list = active
                    )
                }
                1 -> {
                    HistoryRevisions(
                        list = history
                    )
                }
            }
        }
    }
}

@Composable
fun ActiveRevisions(
    list: List<String> //TODO: Replace String to Model type
) {
    LazyColumn {
        /*
        items(
            items = list
        ) { item ->
            Text(text = item)
        }

         */
        repeat(100) {
            item {
                RevisionItem(
                    revisionName = "Проверка №4950249609",
                    revisionExpiredDate = "12.12.2022 16:00",
                    isActive = true
                )
            }
        }
    }
}

@Composable
fun HistoryRevisions(
    list: List<String> //TODO: Replace String to Model type
) {
    LazyColumn {
        /*
        items(
            items = list
        ) { item ->
            Text(text = item.toString())
        }

         */
        repeat(100) {
            item {
                RevisionItem(
                    revisionName = "Проверка №4950249609",
                    revisionExpiredDate = "12.07.2022 16:00",
                    isActive = false,
                    successfulCompleted = true
                )
            }
        }
    }
}
