package de.dfutil.importing.parsing;

import java.nio.file.Path;

public interface Parser {

    void fromFile(Path path);

    long rowCount();

}
