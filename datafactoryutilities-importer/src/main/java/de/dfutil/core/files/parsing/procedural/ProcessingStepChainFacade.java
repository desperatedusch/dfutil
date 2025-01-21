package de.dfutil.core.files.parsing.procedural;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessingStepChainFacade {

    private final ProcessingStep chainHead;

    public ProcessingStepChainFacade(List<ProcessingStep> steps) {
        this.chainHead = Processing.buildChain(steps, new NoOpProcessingStep());
    }

    public String row(String row) {
        return chainHead.outputContent(row);
    }

}
