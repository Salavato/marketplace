package ru.otus.otuskotlin.marketplace.mappers.v1

import ru.otus.otuskotlin.marketplace.api.v1.models.*
import ru.otus.otuskotlin.marketplace.common.models.InsDeal
import ru.otus.otuskotlin.marketplace.common.models.InsDealFilter
import ru.otus.otuskotlin.marketplace.common.models.InsDealStatus
import kotlin.test.Test
import kotlin.test.assertEquals

/** Тесты маппера между транспортными и внутренними моделями */
class MapperTest {

    /** Запрос на создание сделки корректно маппится во внутреннюю модель */
    @Test
    fun fromTransportCreate() {
        val request = DealCreateRequest(
            deal = DealObject(
                productId = "product-1",
                userId = "user-1",
                orderId = "order-1",
                status = DealStatus.NEW,
            )
        )
        val deal = request.toInternal()

        assertEquals("product-1", deal.productId)
        assertEquals("user-1", deal.userId)
        assertEquals("order-1", deal.orderId)
        assertEquals(InsDealStatus.NEW, deal.status)
    }

    /** Запрос поиска маппится в фильтр, а не в сделку */
    @Test
    fun fromTransportSearch() {
        val request = DealSearchRequest(
            filter = DealSearchFilter(userId = "user-1", status = DealStatus.PAID)
        )
        val filter = request.toInternal()

        assertEquals("user-1", filter.userId)
        assertEquals(InsDealStatus.PAID, filter.status)
    }

    /** Запрос на отмену сделки передаёт id во внутреннюю модель */
    @Test
    fun fromTransportCancel() {
        val request = DealCancelRequest(dealId = "deal-1")
        val deal = request.toInternal()

        assertEquals("deal-1", deal.id)
    }

    /** Внутренняя модель сделки корректно маппится в транспортный ответ */
    @Test
    fun toTransportCreate() {
        val deal = InsDeal(
            id = "deal-1",
            productId = "product-1",
            userId = "user-1",
            orderId = "order-1",
            status = InsDealStatus.PAID,
        )
        val response = deal.toTransportCreate()

        assertEquals(ResponseResult.SUCCESS, response.result)
        assertEquals("deal-1", response.deal?.id)
        assertEquals(DealStatus.PAID, response.deal?.status)
    }
}
