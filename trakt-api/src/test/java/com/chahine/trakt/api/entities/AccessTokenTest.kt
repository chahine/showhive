package com.chahine.trakt.api.entities

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class AccessTokenTest {

    private val moshi = com.chahine.trakt.api.MoshiFactory().make()

    private val testClass = AccessToken::class.java

    @Test
    fun `deserializes AccessToken`() {
        val actual = moshi.adapter(testClass).fromJson(
            """
            {
                "access_token": "7185da4d84de0f141e25bef7fdcde42c7ebce208744c74557e2a4cc9c89dfef8",
                "token_type": "Bearer",
                "expires_in": 7889238,
                "refresh_token": "3884eb2e861359a45624f2a0e92b5c6595855620d2c864280525a3aab9b86091",
                "scope": "public",
                "created_at": 1623994664
            }
            """.trimIndent()
        )

        assertNotNull(actual)
    }

    @Test
    fun `serialize and deserializes AccessToken`() {
        val expected = AccessToken(
            accessToken = "7185da4d84de0f141e25bef7fdcde42c7ebce208744c74557e2a4cc9c89dfef8",
            tokenType = "Bearer",
            expiresIn = 7889238,
            refreshToken = "3884eb2e861359a45624f2a0e92b5c6595855620d2c864280525a3aab9b86091",
            scope = "public"
        )

        val json = moshi.adapter(testClass).toJson(expected)
        val actual = moshi.adapter(testClass).fromJson(json)

        assertEquals(expected, actual)
    }
}
