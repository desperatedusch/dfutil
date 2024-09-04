import de.dfutil.core.FileParser;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

public class DataFactoryFileParserTest {

    FileParser cut = new FileParser();

    @Test
    @Ignore
    public void testParseDataFactoryFileSBRowWithFileChannel() {
        cut.parseFileWithFileChannel(getClass().getResource("/files2import/B2308213.DAT").getFile());
    }

    @Test
    public void testParseDataFactoryFileSBRowWithAsynchronousileChannel() {
        cut.parseFileWithAsynchronousFileChannelBlockingBehaviour("D:\\idea-workspaces\\dfutil\\datafactoryimporter\\target\\test-classes\\files2import\\B2308213.DAT");
    }



}
