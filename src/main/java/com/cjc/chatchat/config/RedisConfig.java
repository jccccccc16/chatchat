package com.cjc.chatchat.config;

import com.cjc.chatchat.entity.Record;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/12/15
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 **/
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Record> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Record> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }


}
