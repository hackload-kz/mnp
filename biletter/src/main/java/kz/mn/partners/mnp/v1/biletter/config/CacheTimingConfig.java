package kz.mn.partners.mnp.v1.biletter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Конфигурация временных интервалов для кэширования пользователей
 * Оптимизирована для высокой нагрузки: 100,000 билетов за час
 */
@Data
@Component
@ConfigurationProperties(prefix = "app.cache.user")
public class CacheTimingConfig {

    /**
     * Интервал preloading в минутах (по умолчанию 2 минуты для высокой нагрузки)
     */
    private int preloadIntervalMinutes = 2;

    /**
     * Загружать пользователей за последние N минут (по умолчанию 10 минут для высокой нагрузки)
     */
    private int recentMinutes = 10;

    /**
     * Загружать активных пользователей за последние N дней (по умолчанию 1 день для высокой нагрузки)
     */
    private int activeDays = 1;

    /**
     * Максимальное количество пользователей для preloading (по умолчанию 50,000 для высокой нагрузки)
     */
    private int maxPreloadUsers = 50_000;

    /**
     * Включить/выключить preloading (по умолчанию включено)
     */
    private boolean preloadingEnabled = true;

    /**
     * Включить/выключить логирование статистики preloading (по умолчанию включено)
     */
    private boolean statisticsEnabled = true;

    /**
     * Включить агрессивное кэширование для высокой нагрузки (по умолчанию включено)
     */
    private boolean aggressiveCaching = true;

    /**
     * Интервал очистки устаревших кэшей в минутах (по умолчанию 5 минут)
     */
    private int cacheCleanupIntervalMinutes = 5;

    /**
     * Максимальное количество параллельных потоков для preloading (по умолчанию 8)
     */
    private int maxPreloadThreads = 8;

    /**
     * Размер батча для загрузки пользователей (по умолчанию 1000)
     */
    private int batchSize = 1000;
}
