package com.loryblu.feature.logbook.model

data class LogbookTaskModel(
    var category: Category,
    var task: String,
    var shift: String,
    var frequency: List<String>
)

enum class Category(val action:String) {
    ROUTINE("LoryRotina"),
    STUDIOUS("LoryEstudioso")
}