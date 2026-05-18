package ru.otus.otuskotlin.marketplace.common.models

/** Страховая сделка — факт оформления страхового продукта покупателем по конкретному заказу */
data class InsDeal(
    /** Идентификатор сделки */
    val id: String = "",
    /** Идентификатор страхового продукта */
    val productId: String = "",
    /** Идентификатор покупателя */
    val userId: String = "",
    /** Идентификатор заказа на маркетплейсе */
    val orderId: String = "",
    /** Текущий статус сделки */
    val status: InsDealStatus = InsDealStatus.NEW,
)
