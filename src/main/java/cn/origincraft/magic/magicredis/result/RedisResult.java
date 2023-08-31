package cn.origincraft.magic.magicredis.result;

import dev.rgbmc.expression.functions.FunctionResult;
import io.lettuce.core.RedisClient;

public class RedisResult extends FunctionResult {
    private final RedisClient redisClient;

    public RedisResult(RedisClient redis) {
        this.redisClient = redis;
    }

    public RedisClient getRedis() {
        return redisClient;
    }
}
