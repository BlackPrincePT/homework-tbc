package com.perullheim.homework.presentation.screen.payment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.perullheim.homework.R
import com.perullheim.homework.presentation.components.TopAppBarContent
import com.perullheim.homework.presentation.model.UiAccount
import com.perullheim.homework.presentation.util.StringUtils

@Composable
fun PaymentScreen(viewModel: PaymentViewModel) {

    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBarContent(onBackClick = { viewModel.onEvent(PaymentUiEvent.OnNavigateBack) }) }
    ) { innerPadding ->

        PaymentContent(
            state = uiState,
            onEvent = viewModel::onEvent,
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}

@Composable
private fun PaymentContent(
    state: PaymentUiState,
    onEvent: (PaymentUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.from),
            modifier = Modifier
                .padding(start = 24.dp)
                .padding(vertical = 8.dp)
        )

        if (state.myAccount != null)
            AccountInfo(
                account = state.myAccount,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { onEvent(PaymentUiEvent.OnFromAccountClick) }
            )
        else
            AccountPlaceholder(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        onEvent(PaymentUiEvent.OnFromAccountClick)
                    }
            )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.to),
            modifier = Modifier
                .padding(start = 24.dp)
                .padding(bottom = 8.dp)
        )

        if (state.accountToPay != null)
            AccountInfo(
                account = state.accountToPay,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { onEvent(PaymentUiEvent.OnToAccountClick) }
            )
        else
            AccountPlaceholder(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        onEvent(PaymentUiEvent.OnToAccountClick)
                    }
            )

        Spacer(modifier = Modifier.height(32.dp))

        if (state.myAccount?.currency.also { println(it) } != state.accountToPay?.currency.also { println(it) })
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = state.amountMyCurrency.toString(),
                    onValueChange = { onEvent(PaymentUiEvent.OnAmountMyCurrencyChanged(it)) },
                    label = { Text(text = stringResource(R.string.sell)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                TextField(
                    value = state.amountTheirCurrency.toString(),
                    onValueChange = { onEvent(PaymentUiEvent.OnAmountTheirCurrencyChanged(it)) },
                    label = { Text(text = stringResource(R.string.buy)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp)
                )
            }
        else
            TextField(
                value = state.amountMyCurrency.toString(),
                onValueChange = { onEvent(PaymentUiEvent.OnAmountMyCurrencyChanged(it)) },
                label = { Text(text = stringResource(R.string.amount)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.pitiful_android_developer),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = "",
            onValueChange = { },
            label = { Text(text = stringResource(R.string.description)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onEvent(PaymentUiEvent.OnContinueClick) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 48.dp)
                .height(64.dp)
        ) {
            Text(text = stringResource(R.string.continue_payment))
        }
    }
}

@Composable
private fun AccountInfo(
    account: UiAccount,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier,
        shadowElevation = 2.dp,
        leadingContent = {
            Icon(
                imageVector = Icons.Default.MonetizationOn,
                contentDescription = null,
                tint = colorResource(R.color.cash_green),
                modifier = Modifier
                    .size(48.dp)
            )
        }, headlineContent = {
            Text(
                text = account.accountName
            )
        }, supportingContent = {
            Text(
                text = StringUtils.transform(
                    balance = account.balance,
                    currency = account.currency
                )
            )
        }, trailingContent = {
            Text(
                text = StringUtils.transform(iban = account.iban),
                fontSize = 16.sp
            )
        }
    )
}

@Composable
private fun AccountPlaceholder(
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier,
        shadowElevation = 2.dp,
        headlineContent = {
            Text(text = stringResource(R.string.choose_an_account))
        }
    )
}

@Preview
@Composable
private fun PaymentContentPreview() {
    PaymentContent(state = PaymentUiState(accountToPay = UiAccount.DEFAULT), onEvent = { })
}

@Preview
@Composable
private fun AccountInfoPreview() {
    AccountInfo(account = UiAccount.DEFAULT)
}