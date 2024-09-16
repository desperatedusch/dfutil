import de.dfutil.core.FileParser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DataFactoryFileParserTest {

    FileParser cut = new FileParser();

    @Test
    @Disabled
    public void testParseDataFactoryFileSBRowWithFileChannel() {
        cut.parseFileWithFileChannel(getClass().getResource("/files2import/B2308213.DAT").getFile());
    }

    @Test
    @Disabled
    public void testParseDataFactoryFileSBRowWithAsynchronousileChannel() {
        cut.parseFileWithAsynchronousFileChannelBlockingBehaviour("D:\\idea-workspaces\\dfutil\\datafactoryimporter\\target\\test-classes\\files2import\\B2308213.DAT");
    }

    @Test
    @Disabled
    public void testParseDataFactoryFileSBRowWithCompletionHandler() {
        cut.parseFileWithAsynchronousFileChannelCompletionHandler("D:\\idea-workspaces\\dfutil\\datafactoryimporter\\target\\test-classes\\files2import\\B2308213.DAT");
    }

}
