package ru.otus.otuskotlin.marketplace.common.models

enum class InsDealStatus(val description: String) {
    NEW("Новый"),
    PAID_PROGRESS("На оплате"),
    PAID("Договор оплачен"),
    NOT_PAID("Договор не оплачен"),
    CANCELLED("Отказ/расторжение договора"),
    CLAIMED("Страховка выплачена"),
}
