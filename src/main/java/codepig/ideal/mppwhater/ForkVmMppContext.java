package codepig.ideal.mppwhater;

import codepig.ideal.mppwhater.operator.Operator;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Deprecated
public class ForkVmMppContext
        implements MppContext
{
    @Override
    public <E, R> List<R> runJob(Operator<E> dataSet, Function<Iterator<E>, R> function)
    {
        throw new UnsupportedOperationException("this method have't support!");
//        Partition[] partitions = dataSet.getPartitions();
//        Stream.of(partitions).parallel().forEach(partition -> {
//                    JVMLauncher<R> jvmLauncher = JVMLaunchers.<R>newJvm()
//                            .setCallable(() -> {
//                                Iterator<E> iterator = dataSet.compute(partition);
//                                return function.apply(iterator);
//                            })
//                            .setConsole(System.out::println)
//                            .build();
//                    try {
//                        jvmLauncher.startAndGet();
//                    }
//                    catch (JVMException e) {
//                        e.printStackTrace();
//                    }
//                }
//        );
    }
}
