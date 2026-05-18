package ru.otus.otuskotlin.marketplace.mappers.v1

import ru.otus.otuskotlin.marketplace.api.v1.models.*
import ru.otus.otuskotlin.marketplace.common.models.InsDeal
import ru.otus.otuskotlin.marketplace.common.models.InsDealFilter
import ru.otus.otuskotlin.marketplace.common.models.InsDealStatus

fun DealCreateRequest.toInternal(): InsDeal = deal?.toInternal() ?: InsDeal()

fun DealCancelRequest.toInternal(): InsDeal = InsDeal(id = dealId ?: "")

fun DealReadRequest.toInternal(): InsDeal = InsDeal(id = dealId ?: "")

fun DealSearchRequest.toInternal(): InsDealFilter = InsDealFilter(
    userId = filter?.userId ?: "",
    status = filter?.status.toInternal(),
)

private fun DealObject.toInternal() = InsDeal(
    id = id ?: "",
    productId = productId ?: "",
    userId = userId ?: "",
    orderId = orderId ?: "",
    status = status.toInternal(),
)

private fun DealStatus?.toInternal() = when (this) {
    DealStatus.NEW -> InsDealStatus.NEW
    DealStatus.PAID_PROGRESS -> InsDealStatus.PAID_PROGRESS
    DealStatus.PAID -> InsDealStatus.PAID
    DealStatus.NOT_PAID -> InsDealStatus.NOT_PAID
    DealStatus.CANCELLED -> InsDealStatus.CANCELLED
    DealStatus.CLAIMED -> InsDealStatus.CLAIMED
    null -> InsDealStatus.NEW
}
