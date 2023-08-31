package cn.origincraft.magic.magicredis.function;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.magicredis.result.RedisResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.BooleanResult;
import dev.rgbmc.expression.results.StringResult;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.List;

public class RedisKeyExistsFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "RedisGet function requires at least two arguments.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        if (arg1 instanceof RedisResult && arg2 instanceof StringResult) {
            RedisResult redisResult = (RedisResult) arg1;
            StringResult stringResult = (StringResult) arg2;
            String key = stringResult.getString();
            RedisClient client = redisResult.getRedis();
            StatefulRedisConnection<String, String> connection = client.connect();
            RedisCommands<String, String> syncCommands = connection.sync();
            if (args.size()>2){
                FunctionResult arg3 = args.get(2);
                if (arg3 instanceof StringResult) {
                    StringResult stringResult1 = (StringResult) arg3;
                    if (VariableUtil.tryInt(stringResult1.getString())){
                        syncCommands.select(Integer.parseInt(stringResult1.getString()));
                    }
                }
            }
            if (syncCommands.exists(key)<=0){
                connection.close();
                return new BooleanResult(false);
            }else {
                connection.close();
                return new BooleanResult(true);
            }
        }else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
    }

    @Override
    public String getType() {
        return "REDIS_SYSTEM";
    }

    @Override
    public String getName() {
        return "redisKeyExists";
    }
}
