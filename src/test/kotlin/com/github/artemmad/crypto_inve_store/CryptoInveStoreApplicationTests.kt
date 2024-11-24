package com.github.artemmad.crypto_inve_store

import com.github.artemmad.crypto_inve_store.configuration.TestTelegramBotConfiguration
import com.github.artemmad.crypto_inve_store.configuration.TestcontainersConfiguration
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@Import(
    TestcontainersConfiguration::class,
    TestTelegramBotConfiguration::class
)
@SpringBootTest
class CryptoInveStoreApplicationTests {

    @Test
    fun contextLoads() {
    }

}
