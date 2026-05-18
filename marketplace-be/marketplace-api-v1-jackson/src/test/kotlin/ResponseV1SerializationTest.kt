package ru.otus.otuskotlin.marketplace.api.v1

import ru.otus.otuskotlin.marketplace.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

/** Тесты сериализации ответов API v1 */
class ResponseV1SerializationTest {
    private val response = DealCreateResponse(
        result = ResponseResult.SUCCESS,
        deal = DealObject(
            id = "deal-1",
            productId = "product-1",
            userId = "user-1",
            orderId = "order-1",
            status = DealStatus.NEW,
        )
    )

    /** Ответ корректно сериализуется в JSON с нужными полями и дискриминатором */
    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"id\":\\s*\"deal-1\""))
        assertContains(json, Regex("\"responseType\":\\s*\"dealCreate\""))
    }

    /** JSON десериализуется обратно в исходный объект без потерь */
    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as DealCreateResponse

        assertEquals(response, obj)
    }
}
