package ru.otus.otuskotlin.marketplace.common.models

/** Страховой продукт — справочник доступных страховых программ */
data class InsuranceProduct(
    /** Идентификатор страхового продукта */
    val id: String = "",
    /** Название страхового продукта */
    val name: String = "",
    /** Описание условий страхования */
    val description: String = "",
    /** Стоимость страхового продукта */
    val price: Double = 0.0,
)
