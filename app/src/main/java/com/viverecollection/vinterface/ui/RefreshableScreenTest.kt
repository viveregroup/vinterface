package com.viverecollection.vinterface.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viverecollection.jetinferface.R
import com.viverecollection.jetinferface.data.BaseOption
import com.viverecollection.jetinferface.ui.component.*
import com.viverecollection.jetinferface.util.toashShortly
import com.viverecollection.vinterface.data.OptionSample
import com.viverecollection.vinterface.data.deleteItem
import com.viverecollection.vinterface.data.filterItems
import id.co.vivere.ui.component.BorderedTextField
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Annas Surdyanto on 25/05/22.
 *
 */

@Composable
fun RefreshableScreenTest() {
    val loadingState = remember { mutableStateOf(false) }
    val submissionLoading = remember { mutableStateOf(false) }
    val invalidState = remember { mutableStateOf(false) }
    val successSubmit = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val currency = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val radioButtonSelection = remember { mutableStateOf(true) }
    val radioButtonInit = remember { mutableStateOf(false) }
    val time = remember { mutableStateOf("") }
    val month = remember { mutableStateOf("") }
    val textField = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val customMultiSelection = remember { mutableStateOf(emptyList<OptionSample>()) }
    val customSelection = remember { mutableStateOf(OptionSample.init) }
    val multiSelection = remember { mutableStateOf(emptyList<BaseOption>()) }
    val selection = remember { mutableStateOf(BaseOption.init) }
    val testDialog = remember { mutableStateOf(false) }
    val customTypeOptionList = remember { mutableStateOf(OptionSample.optionSampleList()) }
    val refresh: () -> Unit = {
        scope.launch {
            loadingState.value = true
            delay(2000)
            loadingState.value = false
        }
    }

    val submit: () -> Unit = {
        submissionLoading.value = true
        scope.launch {
            delay(1000)
            invalidState.value =
                textField.value.isEmpty() && password.value.isEmpty() && currency.value.isEmpty()
            if (!invalidState.value) successSubmit.value = true
            submissionLoading.value = false
        }
    }


    val removeDialogSuccess: () -> Unit = { successSubmit.value = false }
    val removeTestDialog: () -> Unit = { testDialog.value = false }
    val context = LocalContext.current

    if (successSubmit.value) {
        OkaySuccessDialog(message = "Submit success", onConfirmed = removeDialogSuccess)
    }

    if (testDialog.value) {
        RetryDialog(
            message = "Test Dialog",
            onConfirmed = removeTestDialog,
            onCancel = removeTestDialog
        )
    }

    RefreshableScreenContainer(
        loadingState = loadingState.value,
        onRefresh = refresh,
        barTitle = "Refreshable Screen test"
    ) {
        if (submissionLoading.value) LoadingScreen()
        else {
            Scaffold(Modifier.padding(sixteenDp)) {
                LazyColumn {
                    item {
                        HeadingText(text = "Test Heading Text")
                        Spacer()

                        CustomMultiselectTextField(
                            label = "Customized Multi Select with Custom Type",
                            options = customTypeOptionList.value,
                            isInvalid = customMultiSelection.value.isEmpty(),
                            selections = customMultiSelection,
                            isMandatory = true,
                            invalidState = invalidState,
                            onFiltered = { customTypeOptionList.value.filterItems(it) },
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colors.surface,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .fillMaxWidth(),
                            selectedContent = {
                                CustomSelectedColumnScopeListView(
                                    selections = customMultiSelection,
                                    itemContent = {
                                        CustomMultiselectItemView(
                                            modifier = Modifier
                                                .background(
                                                    color = colorResource(id = R.color.category_11),
                                                    shape = RoundedCornerShape(10.dp)
                                                ),
                                            title = it.title,
                                            description = it.description,
                                            onDelete = {
                                                customMultiSelection.value.deleteItem(it) {
                                                    customMultiSelection.value = it
                                                }
                                            }
                                        )
                                    }
                                )
                            },
                            optionContent = {
                                CustomOptionItemView(
                                    title = it.title,
                                    description = it.description
                                )
                            }
                        )
                        Spacer()

                        CustomMultiselectTextField(
                            label = "Default Multiselect Custom Type (Column)",
                            selections = customMultiSelection,
                            options = customTypeOptionList.value,
                            title = { it.title },
                            isMandatory = true,
                            invalidState = invalidState,
                            description = { it.description },
                            onFiltered = { customTypeOptionList.value.filterItems(it) },
                            onDelete = {
                                customMultiSelection.value.deleteItem(it) {
                                    customMultiSelection.value = it
                                }
                            }
                        )

                        Spacer()
                        CustomMultiselectTextField(
                            label = "Default Multiselect Custom Type (Row)",
                            selections = customMultiSelection,
                            options = customTypeOptionList.value,
                            title = { it.title },
                            isMandatory = true,
                            invalidState = invalidState,
                            description = { it.description },
                            onFiltered = { customTypeOptionList.value.filterItems(it) },
                            onDelete = {
                                customMultiSelection.value.deleteItem(it) {
                                    customMultiSelection.value = it
                                }
                            },
                            useColumnScopeArrangement = false
                        )
                        Spacer()
                        CustomSelectableTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = "Selectable Textfield with Custom Type",
                            selection = customSelection,
                            isMandatory = true,
                            invalidState = invalidState,
                            onFiltered = { customTypeOptionList.value.filterItems(it) },
                            title = { it.title },
                            useBorderStroke = false,
                            options = customTypeOptionList.value
                        )

                        Spacer()
                        BodyTextBold(text = "Test Body Text Bold")
                        BodyText(text = "Test Body Text Normal")
                        BodyTextSecondary(text = "Test Body Text Secondary")
                        Spacer()
                        CopyButton(
                            label = "Copy",
                            content = "Copy Test",
                            style = MaterialTheme.typography.caption,
                            iconTint = Color.Blue
                        )
                        Spacer()
                        ShareButton(
                            label = "Share",
                            content = "Share Test",
                            style = MaterialTheme.typography.caption,
                            iconTint = Color.Green
                        )
                        Spacer()
                        EmailAddressButton(
                            modifier = Modifier.fillMaxWidth(),
                            address = "annas.shawn@gmail.com",
                            style = MaterialTheme.typography.caption,
                            iconTint = Color.Red
                        )
                        Spacer()
                        PhoneNumberButton(
                            modifier = Modifier.fillMaxWidth(),
                            phoneNumber = "081291827229",
                            style = MaterialTheme.typography.caption,
                            iconTint = Color.Yellow
                        )
                        Spacer()
                        DirectUrlButton(
                            modifier = Modifier.fillMaxWidth(),
                            url = "google.com",
                            label = "Visit Website",
                            style = MaterialTheme.typography.caption
                        )
                        Spacer()
                    }

                    item {
                        ImageDescribedView(
                            title = "Image Description View",
                            description = stringResource(id = R.string.long_description_sample),
                            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkFtOJDNY1RfJkFWrpFZPzjROR10yyb1-Ohw&usqp=CAU"
                        )
                        Spacer()
                    }

                    item {
                        CircleImageView(
                            title = "Circle Image View",
                            description = stringResource(id = R.string.long_title_sample),
                            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkFtOJDNY1RfJkFWrpFZPzjROR10yyb1-Ohw&usqp=CAU",
                            isClickable = true
                        )
                    }

                    item {
                        ExpandableHeading(label = "Expandable Heading", defaultState = true) {
                            BorderedTextField(
                                label = "Outlined Text Field",
                                state = textField,
                                isInvalid = textField.value.isEmpty(),
                                invalidState = invalidState,
                                isMandatory = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer()
                            UnderlinedTextField(
                                label = "Common Text Field",
                                state = textField,
                                isInvalid = textField.value.isEmpty(),
                                invalidState = invalidState,
                                isMandatory = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Spacer()
                    }

                    item {
                        ExpandableTitle(label = "Expandable Title", defaultState = true) {
                            OutlinedCurrencyTextField(
                                label = "Currency Text Field",
                                state = currency,
                                isInvalid = currency.value.isEmpty(),
                                invalidState = invalidState,
                                isMandatory = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer()
                            CurrencyTextField(
                                label = "Currency Text Field",
                                state = currency,
                                isInvalid = currency.value.isEmpty(),
                                invalidState = invalidState,
                                isMandatory = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Spacer()
                    }

                    item {
                        ExpandableLabel(label = "Expandable Label", defaultState = true) {
                            PasswordTextField(
                                label = "Password Text Field",
                                state = password,
                                onDone = submit,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer()
                            OutlinedPasswordTextField(
                                label = "Outlined Password Text Field",
                                state = password,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Spacer()
                    }

                    item {
                        MonthPickerView(
                            label = "Bordered Month Picker",
                            state = month,
                            isMandatory = true,
                            invalidState = invalidState,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer()
                        DatePickerView(
                            label = "Bordered Date Picker",
                            state = date,
                            isMandatory = true,
                            invalidState = invalidState,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer()
                        TimePickerView(
                            label = "Bordered Time Picker",
                            state = time,
                            isMandatory = true,
                            invalidState = invalidState,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer()
                    }
                    item {
                        MonthPickerView(
                            label = "Month Picker",
                            state = month,
                            isMandatory = true,
                            useBorderStroke = false,
                            invalidState = invalidState,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer()
                        DatePickerView(
                            label = "Date Picker",
                            state = date,
                            isMandatory = true,
                            useBorderStroke = false,
                            invalidState = invalidState,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer()
                        TimePickerView(
                            label = "Time Picker",
                            state = time,
                            isMandatory = true,
                            useBorderStroke = false,
                            invalidState = invalidState,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer()
                    }

                    item {
                        SelectableTextField(
                            label = "Selectable Field Column View",
                            options = customTypeOptionList.value,
                            isInvalid = selection.value == BaseOption.init,
                            selection = selection,
                            isMandatory = true,
                            invalidState = invalidState,
                            useBorderStroke = false,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Spacer()
                        SelectableTextField(
                            label = "Rounded Selectable Field Column View",
                            options = customTypeOptionList.value,
                            isInvalid = selection.value == BaseOption.init,
                            selection = selection,
                            isMandatory = true,
                            invalidState = invalidState,
                            useBorderStroke = true,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Spacer()
                        MultiselectTextField(
                            label = "Multi Select Field Custom Row View",
                            options = customTypeOptionList.value,
                            isInvalid = multiSelection.value.isEmpty(),
                            selections = multiSelection,
                            isMandatory = true,
                            invalidState = invalidState,
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colors.surface,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .fillMaxWidth(),
                            content = {
                                SelectedRowScopeListView(
                                    modifier = Modifier,
                                    selections = multiSelection
                                ) { selectedItem, deleteItem ->
                                    MultiselectItemView(
                                        modifier = Modifier
                                            .background(
                                                color = colorResource(id = R.color.category_5),
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .padding(twentyDp),
                                        item = selectedItem,
                                        onDelete = deleteItem
                                    )
                                }
                            }
                        )
                        Spacer()

                        MultiselectTextField(
                            label = "Multi Select Field Custom Column View",
                            options = customTypeOptionList.value,
                            isInvalid = multiSelection.value.isEmpty(),
                            selections = multiSelection,
                            isMandatory = true,
                            invalidState = invalidState,
                            modifier = Modifier.fillMaxWidth(),
                            content = {
                                SelectedColumnScopeListView(
                                    modifier = Modifier,
                                    selections = multiSelection,
                                ) { selectedItem, deleteItem ->
                                    MultiselectItemView(
                                        modifier = Modifier
                                            .background(
                                                color = colorResource(id = R.color.category_11),
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .padding(twelveDp),
                                        item = selectedItem,
                                        onDelete = deleteItem
                                    )
                                }
                            }
                        )

                        Spacer()
                        MultiselectTextField(
                            label = "Multi Select Field Default Column View",
                            options = customTypeOptionList.value,
                            isInvalid = multiSelection.value.isEmpty(),
                            selections = multiSelection,
                            isMandatory = true,
                            invalidState = invalidState,
                            modifier = Modifier.fillMaxWidth(),
                        )

                        Spacer()
                        MultiselectTextField(
                            label = "Multi Select Field Default Row View",
                            options = customTypeOptionList.value,
                            isInvalid = multiSelection.value.isEmpty(),
                            selections = multiSelection,
                            isMandatory = true,
                            invalidState = invalidState,
                            modifier = Modifier.fillMaxWidth(),
                            useColumnScopeArrangement = false
                        )
                        Spacer()
                    }

                    item {
                        RadioButtonView(
                            modifier = Modifier.fillMaxWidth(),
                            label = "Radio Button",
                            isFirstSelected = radioButtonSelection,
                            isInit = radioButtonInit
                        )
                        Spacer()
                    }
                    item {
                        PrimaryButton(
                            text = "Submit Test",
                            onClick = submit,
                            modifier = Modifier.fillMaxWidth()
                        )

                        BorderedSecondaryButton(
                            text = "Border Button",
                            onClick = { "Button Clicked".toashShortly(context) },
                            modifier = Modifier.fillMaxWidth()
                        )

                        BorderedSecondaryButton(
                            text = "Test Dialog",
                            onClick = { testDialog.value = true },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RefreshableScreenTest_Preview() {
    RefreshableScreenTest_Preview()
}