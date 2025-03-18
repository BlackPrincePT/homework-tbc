package com.perullheim.homework.presentation.equipment.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.perullheim.homework.R
import com.perullheim.homework.domain.model.Equipment
import com.perullheim.homework.presentation.equipment.components.EquipmentItem

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EquipmentScreen(
    viewModel: EquipmentViewModel = hiltViewModel()
) {
    val uiState by viewModel.state
        .collectAsStateWithLifecycle()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    TextField(
                        value = uiState.searchFieldValue,
                        onValueChange = { viewModel.onEvent(EquipmentUiEvent.OnSearchValueChanged(it)) },
                        placeholder = { Text("Search...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            uiState.equipmentList.isEmpty() -> Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = stringResource(R.string.no_equipment_found),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            else -> EquipmentList(
                equipmentList = uiState.equipmentList,
                modifier = Modifier
                    .padding(innerPadding)
            )
        }
    }
}

@Composable
private fun EquipmentList(
    modifier: Modifier = Modifier,
    equipmentList: List<Equipment>
) {
    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = "Suggestions",
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                fontSize = 12.sp,
                color = Color.LightGray
            )
        }
        items(equipmentList.size) { index ->
            val equipment = equipmentList[index]

            EquipmentItem(equipment = equipment)
        }
    }
}

@Preview
@Composable
private fun EquipmentListPreview() {
    val equipmentList: List<Equipment> = (0..5).map { i ->
        Equipment(
            id = "",
            name = "Equipment $i",
            nameDe = "",
            createdAt = "",
            parentAmount = i
        )
    }

    EquipmentList(equipmentList = equipmentList)
}