package com.example.board_api

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun CommonTextField(
modifier: Modifier = Modifier,
leadingIcon:@Composable (() -> Unit)? = null,
trailingIcon:@Composable (() -> Unit)? = null,
value: String,
label:String,
onValueChange: (String) -> Unit,

) {
    Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = leadingIcon,
            shape = RoundedCornerShape(8.dp),
            maxLines = 1,
            label = { Text(text = label, style = TextStyle(fontWeight = FontWeight.W500,color = Color.Black.copy(alpha = 0.5f))) },
            colors =   OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Black.copy(
                    alpha = 0.16f
                ),
                focusedBorderColor = Color.Black.copy(
                    alpha = 0.16f
                )
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonAppBar(
    title:String
){
    TopAppBar(
        title = {Text(text = title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W600) )},
//        navigationIcon = { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "goBack")},
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}