package de.dfutil.files.parsing;

import java.nio.file.Path;

public interface Parser {

    void fromFile(Path path);

    long rowCount();

}
