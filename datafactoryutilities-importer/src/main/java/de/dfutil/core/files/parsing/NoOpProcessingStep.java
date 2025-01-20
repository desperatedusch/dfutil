package de.dfutil.core.files.parsing;

public class NoOpProcessingStep implements ProcessingStep {


    @Override
    public String row(String row) {
        return row;
    }

    @Override
    public void setNext(ProcessingStep step) {
        // no op
    }

}
