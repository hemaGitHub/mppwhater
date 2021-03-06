package codepig.ideal.mppwhater.operator;

import com.google.common.collect.MapMaker;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Executor 内存管理器
 */
@Deprecated
public class CacheManager
{
    private static final ConcurrentMap<Integer, List<?>> cacheMap = new MapMaker().weakValues().makeMap();

    public static void addCache(int id, List<?> data)
    {
        cacheMap.putIfAbsent(id, data);
    }

    public static List<?> getCacheData(int id)
    {
        return cacheMap.get(id);
    }

    public static final ConcurrentMap<Integer, Queue<?>> reduceTaskMap = new ConcurrentHashMap<>(128);
}
