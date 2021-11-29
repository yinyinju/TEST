package com.example.demo.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory)
    {
        RedisTemplate<String,Object> template=new RedisTemplate<>();
        //配置连接工厂
        template.setConnectionFactory(factory);

        //序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer jacksonSeial=new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om=new ObjectMapper();

        //指定要序列化的域
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);

        //值采用Json序列化
        template.setValueSerializer(jacksonSeial);

        template.setKeySerializer(new StringRedisSerializer());

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public HashOperations<String,String,Object> hashOperations(RedisTemplate<String,Object> redisTemplate)
    {
        return redisTemplate.opsForHash();
    }
    @Bean
    public ValueOperations<String,Object> valueOperations(RedisTemplate<String,Object> redisTemplate)
    {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String,Object> listOperations(RedisTemplate<String,Object> redisTemplate)
    {
        return redisTemplate.opsForList();
    }
    @Bean
    public SetOperations<String,Object> setOperations(RedisTemplate<String,Object> redisTemplate)
    {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String,Object> zSetOperations(RedisTemplate<String,Object> redisTemplate)
    {
        return redisTemplate.opsForZSet();
    }

}
