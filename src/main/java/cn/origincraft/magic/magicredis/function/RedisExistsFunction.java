package cn.origincraft.magic.magicredis.function;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.magicredis.result.RedisResult;
import cn.origincraft.magic.object.SpellContext;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.BooleanResult;
import dev.rgbmc.expression.results.StringResult;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisConnectionException;

import java.net.URL;
import java.util.List;

public class RedisExistsFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            new BooleanResult(false);
        }
        if (args.size()==1){
            FunctionResult arg1 = args.get(0);
            if (arg1 instanceof StringResult){
                StringResult stringResult = (StringResult) arg1;
                String url = stringResult.getString();
                try {
                    RedisClient.create(url);
                } catch (RedisConnectionException e) {
                    return new BooleanResult(false);
                }
            }else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }else if (args.size()==2){
            FunctionResult arg1 = args.get(0);
            FunctionResult arg2 = args.get(1);
            if (arg1 instanceof StringResult && arg2 instanceof StringResult){
                StringResult stringResult1 = (StringResult) arg1;
                StringResult stringResult2 = (StringResult) arg2;
                String ip = stringResult1.getString();
                String port = stringResult2.getString();
                try {
                    RedisClient.create("redis://"+ip+":"+port);
                } catch (RedisConnectionException e) {
                    return new BooleanResult(false);
                }
            }else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }else if(args.size()==3){
            FunctionResult arg1 = args.get(0);
            FunctionResult arg2 = args.get(1);
            FunctionResult arg3 = args.get(2);
            if (arg1 instanceof StringResult && arg2 instanceof StringResult && arg3 instanceof StringResult){
                StringResult stringResult1 = (StringResult) arg1;
                StringResult stringResult2 = (StringResult) arg2;
                StringResult stringResult3 = (StringResult) arg3;
                String ip = stringResult1.getString();
                String port = stringResult2.getString();
                String password = stringResult3.getString();
                try {
                    RedisClient.create("redis://"+password+"@"+ip+":"+port);
                } catch (RedisConnectionException e) {
                    return new BooleanResult(false);
                }
            }else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }

        return new BooleanResult(true);
    }

    @Override
    public String getType() {
        return "REDIS_SYSTEM";
    }

    @Override
    public String getName() {
        return "redisExists";
    }
}
