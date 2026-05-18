package ru.otus.otuskotlin.marketplace.mappers.v1

import ru.otus.otuskotlin.marketplace.api.v1.models.*
import ru.otus.otuskotlin.marketplace.common.models.InsDeal
import ru.otus.otuskotlin.marketplace.common.models.InsDealStatus
import ru.otus.otuskotlin.marketplace.common.models.InsuranceProduct

fun InsDeal.toTransportCreate() = DealCreateResponse(
    result = ResponseResult.SUCCESS,
    deal = toTransport(),
)

fun InsDeal.toTransportCancel() = DealCancelResponse(
    result = ResponseResult.SUCCESS,
    deal = toTransport(),
)

fun InsDeal.toTransportRead() = DealReadResponse(
    result = ResponseResult.SUCCESS,
    deal = toTransport(),
)

fun List<InsDeal>.toTransportSearch() = DealSearchResponse(
    result = ResponseResult.SUCCESS,
    deals = map { it.toTransport() }.takeIf { it.isNotEmpty() },
)

fun List<InsuranceProduct>.toTransportProductList() = ProductListResponse(
    result = ResponseResult.SUCCESS,
    products = map { it.toTransport() }.takeIf { it.isNotEmpty() },
)

private fun InsDeal.toTransport() = DealObject(
    id = id.takeIf { it.isNotBlank() },
    productId = productId.takeIf { it.isNotBlank() },
    userId = userId.takeIf { it.isNotBlank() },
    orderId = orderId.takeIf { it.isNotBlank() },
    status = status.toTransport(),
)

private fun InsDealStatus.toTransport() = when (this) {
    InsDealStatus.NEW -> DealStatus.NEW
    InsDealStatus.PAID_PROGRESS -> DealStatus.PAID_PROGRESS
    InsDealStatus.PAID -> DealStatus.PAID
    InsDealStatus.NOT_PAID -> DealStatus.NOT_PAID
    InsDealStatus.CANCELLED -> DealStatus.CANCELLED
    InsDealStatus.CLAIMED -> DealStatus.CLAIMED
}

private fun InsuranceProduct.toTransport() = InsuranceProductObject(
    id = id.takeIf { it.isNotBlank() },
    name = name.takeIf { it.isNotBlank() },
    description = description.takeIf { it.isNotBlank() },
    price = price.takeIf { it != 0.0 },
)
