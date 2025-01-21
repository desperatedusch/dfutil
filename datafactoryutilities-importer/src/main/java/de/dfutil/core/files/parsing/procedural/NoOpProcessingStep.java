package de.dfutil.core.files.parsing.procedural;

public class NoOpProcessingStep implements ProcessingStep {


    @Override
    public String outputContent(String inputContent) {
        return inputContent;
    }

    @Override
    public void setNext(ProcessingStep step) {
        // no op
    }

}
