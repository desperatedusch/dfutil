package de.dfutil.core.files.parsing.procedural;

public interface ProcessingStep extends Processing<ProcessingStep> {

    String outputContent(String inputContent);

}
