package de.dfutil.updating;

import de.dfutil.dao.jpa.ObRowRepository;
import de.dfutil.dao.jpa.OrRowRepository;
import de.dfutil.dao.jpa.SbRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuccessionHandling {

    @Autowired
    private ObRowRepository obRowRepository;
    @Autowired
    private OrRowRepository orRowRepository;
    @Autowired
    private SbRowRepository sbRowRepository;

    public void markOrphans() {
        orRowRepository.findProcessableOrphans().forEach(row -> {
            row.setOutdatedAt(LocalDateTime.now());
            orRowRepository.save(row);
            orRowRepository.
                    findAllById(List.of(row.getOrRowId()))
                    .stream()
                    .filter(orRow -> orRow.getOrRowId().ortStatus().matches(""))
                    .collect(Collectors.toCollection(ArrayList::new));
        });
        obRowRepository.findProcessableOrphans().forEach(row -> {
            row.setOutdatedAt(LocalDateTime.now());
            obRowRepository.save(row);
        });
        sbRowRepository.findProcessableOrphans().forEach(row -> {
            row.setOutdatedAt(LocalDateTime.now());
            sbRowRepository.save(row);
        });
    }

    public void hasSuccessor() {

    }


    public void hasSuccessors() {

    }

}
