package codepig.ideal.mppwhater;

import codepig.ideal.mppwhater.api.DataSet;
import codepig.ideal.mppwhater.operator.CollectionDataSet;
import codepig.ideal.mppwhater.operator.Operator;
import codepig.ideal.mppwhater.operator.TextFileDataSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public interface MppContext
{
    public default <E> DataSet<E> fromCollection(Collection<E> collection)
    {
        return new CollectionDataSet<>(this, collection, 2);
    }

    public default <E> DataSet<E> fromCollection(Collection<E> collection, int parallelism)
    {
        return new CollectionDataSet<>(this, collection, parallelism);
    }

    public default <E> DataSet<E> fromArray(E... e)
    {
        return fromCollection(Arrays.asList(e), 2);
    }

    public default DataSet<String> textFile(String dirPath)
    {
        return new TextFileDataSet(this, dirPath);
    }

    public static MppContext getOrCreate()
    {
        return new LocalMppContext();
    }

    public <E, R> List<R> runJob(Operator<E> dataSet, Function<Iterator<E>, R> function);
}
