package com.loryblu.data.logbook.local

import androidx.compose.ui.graphics.Color
import com.loryblu.data.logbook.R

sealed class CategoryItem(
    val idCard: Int,
    val text: Int,
    val drawable: Int,
    val isDisabled: Boolean = false,
    val color: Color = Color.Unspecified
) {
    data object Routine: CategoryItem(
        idCard = 0,
        text = R.string.lory_routine,
        drawable = R.drawable.lory_routine,
    )

    data object Student: CategoryItem(
        idCard = 1,
        text = R.string.lory_student,
        drawable = R.drawable.lory_student
    )

    companion object {
        fun getAllCategory(): List<CategoryItem> {
            return listOf(Routine, Student)
        }
        fun getCategory(id: Int): CategoryItem {
            return when (id) {
                0 -> Routine
                1 -> Student
                else -> throw IllegalArgumentException("Invalid category ID")
            }
        }
    }
}