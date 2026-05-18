package ru.otus.otuskotlin.marketplace.api.v1

import ru.otus.otuskotlin.marketplace.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

/** Тесты сериализации запросов API v1 */
class RequestV1SerializationTest {
    private val request = DealCreateRequest(
        deal = DealObject(
            productId = "product-1",
            userId = "user-1",
            orderId = "order-1",
            status = DealStatus.NEW,
        )
    )

    /** Запрос корректно сериализуется в JSON с нужными полями и дискриминатором */
    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"productId\":\\s*\"product-1\""))
        assertContains(json, Regex("\"requestType\":\\s*\"dealCreate\""))
    }

    /** JSON с дискриминатором десериализуется в правильный тип запроса */
    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, IRequest::class.java) as DealCreateRequest

        assertEquals(request, obj)
    }

    /** Десериализация минимального JSON без обязательных полей не падает */
    @Test
    fun deserializeNaked() {
        val jsonString = """{"deal": null}"""
        val obj = apiV1Mapper.readValue(jsonString, DealCreateRequest::class.java)

        assertEquals(null, obj.deal)
    }
}
