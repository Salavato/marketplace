package ru.otus.otuskotlin.marketplace.common.models

/** Фильтр для поиска страховых сделок */
data class InsDealFilter(
    /** Фильтр по идентификатору покупателя */
    val userId: String = "",
    /** Фильтр по статусу сделки */
    val status: InsDealStatus = InsDealStatus.NEW,
)
