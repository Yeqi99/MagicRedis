package cn.origincraft.magic.magicredis;

import cn.origincraft.magic.MagicManager;
import cn.origincraft.magic.magicredis.function.*;

public class MagicRedisFunctionRegister {
    public static void reg(MagicManager magicManager){
        magicManager.registerFunction(new RedisFunction());
        magicManager.registerFunction(new RedisExistsFunction(),"redisexist");
        magicManager.registerFunction(new RedisGetFunction(),"redisget");
        magicManager.registerFunction(new RedisKeyExistsFunction(),"redishas");
        magicManager.registerFunction(new RedisPutFunction(),"redisput");
    }
}
