package codepig.ideal.mppwhater.operator;

import codepig.ideal.mppwhater.MppContext;
import codepig.ideal.mppwhater.api.Partition;
import sun.nio.cs.StreamDecoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.github.harbby.gadtry.base.Throwables.throwsException;
import static java.nio.charset.StandardCharsets.UTF_8;

public class TextFileDataSet
        extends AbstractDataSet<String>
{
    private final String dir;

    public TextFileDataSet(MppContext yarkContext, String dir)
    {
        super(yarkContext);
        this.dir = dir;
    }

    @Override
    public Partition[] getPartitions()
    {
        File file = new File(dir);
        if (file.isFile()) {
            return new TextFilePartition[] {new TextFilePartition(0, file)};
        }
        else {
            throw new UnsupportedOperationException();
        }
    }

    private static class TextFilePartition
            extends Partition
    {
        private final File file;

        public TextFilePartition(int index, File file)
        {
            super(index);
            this.file = file;
        }
    }

    @Override
    protected void close()
    {
        super.close();
    }

    @Override
    public Iterator<String> compute(Partition partition)
    {
        TextFilePartition filePartition = (TextFilePartition) partition;
        try {
            //TODO: need pipe readLine
            return Files.readAllLines(Paths.get(filePartition.file.toURI())).iterator();
        }
        catch (IOException e) {
            throw throwsException(e);
        }
    }

    public static List<String> readAll(int buffSize, Path path)
            throws IOException
    {
        try (FileInputStream inputStream = new FileInputStream(path.toFile());
                FileChannel channel = inputStream.getChannel();
                StreamDecoder streamDecoder = StreamDecoder.forDecoder(channel, UTF_8.newDecoder(), buffSize);
                BufferedReader reader = new BufferedReader(streamDecoder)) {
            List<String> result = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
            return result;
        }
    }
}