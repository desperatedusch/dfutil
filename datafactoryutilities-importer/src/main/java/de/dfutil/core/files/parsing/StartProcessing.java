package de.dfutil.core.files.parsing;

import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Order(1)
@Profile({"procedural-persisting ", "!eventbased-persisting"})
public class StartProcessing extends AbstractProcessingStep {


    @Override
    protected Optional<String> readAndApplyNext(String row) {
        return Optional.empty();
    }
}
