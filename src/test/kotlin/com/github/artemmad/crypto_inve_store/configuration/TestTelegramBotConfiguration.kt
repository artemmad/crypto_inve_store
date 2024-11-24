package com.github.artemmad.crypto_inve_store.configuration

import org.mockito.Mockito
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.telegram.telegrambots.longpolling.starter.TelegramBotInitializer
import org.telegram.telegrambots.meta.generics.TelegramClient

@SpringBootConfiguration
@EnableAutoConfiguration
class TestTelegramBotConfiguration {
    @Bean
    @Primary
    fun mockTelegramClient(): TelegramClient {
        return Mockito.mock(TelegramClient::class.java)
    }

    @Bean
    @Primary
    fun mockTelegramBotInitializer(): TelegramBotInitializer {
        return Mockito.mock(TelegramBotInitializer::class.java)
    }
}