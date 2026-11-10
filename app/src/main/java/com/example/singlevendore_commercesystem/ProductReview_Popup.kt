package com.example.singlevendore_commercesystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.singlevendore_commercesystem.ui.theme.SingleVendorECommerceSystemTheme

@Composable
fun ProductReviewPopup(
    onDismissRequest: () -> Unit = {},
    onSubmit: (rating: Int, comment: String) -> Unit = { _, _ -> }
) {
    Dialog(onDismissRequest = onDismissRequest) {
        ProductReviewCard(
            onDismissRequest = onDismissRequest,
            onSubmit = onSubmit
        )
    }
}

@Composable
fun ProductReviewCard(
    onDismissRequest: () -> Unit = {},
    onSubmit: (rating: Int, comment: String) -> Unit = { _, _ -> }
) {
    var rating by remember { mutableIntStateOf(0) }
    var comment by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            // "Rating" at top left
            Text(
                text = "Rating",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 5 stars underneath Rating
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 1..5) {
                    val isSelected = i <= rating
                    Text(
                        text = if (isSelected) "★" else "☆",
                        fontSize = 32.sp,
                        color = if (isSelected) Color(0xFFFFC107) else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.clickable { rating = i }
                    )
                }
            }

            // Space between Rating and Comment
            Spacer(modifier = Modifier.height(20.dp))

            // "Comment" title
            Text(
                text = "Comment",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Text box right below Comment
            OutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
                placeholder = { Text("Write your review here...") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onDismissRequest) {
                    Text("Cancel")
                }
                Button(
                    onClick = { onSubmit(rating, comment) },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Submit")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductReviewPopupPreview() {
    SingleVendorECommerceSystemTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            ProductReviewCard()
        }
    }
}
