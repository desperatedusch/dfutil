package de.dfutil.core.files.parsing.procedural;

import java.util.Optional;

public abstract class AbstractProcessingStep implements ProcessingStep {

    private ProcessingStep next;

    @Override
    public void setNext(ProcessingStep step) {
        this.next = step;
    }

    @Override
    public final String outputContent(String inputContent) {
        return processAndApplyNext(inputContent)
                .map(cur -> next.outputContent(cur))
                .orElseGet(() -> next.outputContent(inputContent));

    }


    protected abstract Optional<String> processAndApplyNext(String row);

}
