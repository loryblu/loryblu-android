package com.loryblu.feature.access_control.model

import com.loryblu.feature.access_control.R

class ChildProfile : AccessItem(
    idCard = 1,
    titleId = R.string.child_profile_title,
    descriptionId = R.string.child_profile_description,
    imageId = R.drawable.seedling,
)

class ParentProfile : AccessItem(
    idCard = 2,
    titleId = R.string.parent_profile_title,
    descriptionId = R.string.parent_profile_description,
    imageId = R.drawable.tree,
)

class TaskRegister : AccessItem(
    idCard = 3,
    titleId = R.string.task_register_title,
    descriptionId = R.string.task_register_description,
    imageId = R.drawable.clipboard,
)

class TaskEdit : AccessItem(
    idCard = 4,
    titleId = R.string.task_edit_title,
    descriptionId = R.string.task_edit_description,
    imageId = R.drawable.pencil,
)

class TaskDelete : AccessItem(
    idCard = 5,
    titleId = R.string.task_delete_title,
    descriptionId = R.string.task_delete_description,
    imageId = R.drawable.trash,
)

class CardOrder : AccessItem(
    idCard = 6,
    titleId = R.string.card_order_title,
    descriptionId = R.string.card_order_description,
    imageId = R.drawable.sort_arrows,
)

class ExitApp : AccessItem(
    idCard = 7,
    titleId = R.string.exit_app_title,
    descriptionId = R.string.exit_app_description,
    imageId = R.drawable.exit_door,
)

fun getAllAccessItems(): List<AccessItem> {
    return listOf(
        ChildProfile(),
        ParentProfile(),
        TaskRegister(),
        TaskEdit(),
        TaskDelete(),
        CardOrder(),
        ExitApp(),
    )
}
