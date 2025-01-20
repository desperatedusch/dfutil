package de.dfutil.core.files.parsing;

public interface ProcessingStep extends Processing<ProcessingStep> {

    String row(String row);

}
