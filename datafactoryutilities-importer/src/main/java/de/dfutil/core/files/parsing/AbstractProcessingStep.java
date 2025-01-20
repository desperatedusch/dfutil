package de.dfutil.core.files.parsing;

import java.util.Optional;

public abstract class AbstractProcessingStep implements ProcessingStep {

    private ProcessingStep next;

    @Override
    public void setNext(ProcessingStep step) {
        this.next = step;
    }

    @Override
    public final String row(String row) {
        return readAndApplyNext(row)
                .map(cur -> next.row(cur))
                .orElseGet(() -> next.row(row));

    }


    protected abstract Optional<String> readAndApplyNext(String row);

}
