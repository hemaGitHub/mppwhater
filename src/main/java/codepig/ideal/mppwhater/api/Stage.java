package codepig.ideal.mppwhater.api;

import java.io.Serializable;

public interface Stage
        extends Serializable
{
    public Partition[] getPartitions();

    public void compute(Partition split);

    public int getParallel();
}
