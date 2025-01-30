package de.dfutil.updating;

import de.dfutil.dao.jpa.ObRowRepository;
import de.dfutil.dao.jpa.OrRowRepository;
import de.dfutil.dao.jpa.SbRowRepository;
import de.dfutil.entities.jpa.OrRow;
import de.dfutil.entities.jpa.ids.OrRowId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableTransactionManagement
@Service
public class SuccessionHandling {

    @Autowired
    private ObRowRepository obRowRepository;
    @Autowired
    private OrRowRepository orRowRepository;
    @Autowired
    private SbRowRepository sbRowRepository;

    @Transactional
    public void handleOrOrphans() {
        List<OrRow> processableOrphans = orRowRepository.findProcessableOrphans();
        for (OrRow orphan : processableOrphans) {
            OrRowId predecessorsId = new OrRowId(orphan.getOrRowId().ortAlort(), "G");
            Optional<OrRow> orRow = orRowRepository.findById(predecessorsId);
            orRow.stream().map(prev -> {
                prev.setOutdatedAt(LocalDateTime.now());
                orRowRepository.save(prev);
            }).collect(Collectors.toCollection(ArrayList::new));
        }
//                forEach(row -> {
//            row.setOutdatedAt(LocalDateTime.now());
//            orRowRepository.save(row);
//            ArrayList<OrRow> collect = orRowRepository.
//                    findAllById(List.of(row.getOrRowId()))
//                    .stream()
//                    .filter(orRow -> orRow.getOrRowId().ortStatus().matches("'G'"))
//                    .forEach();
//        });
    }

    public void hasSuccessor() {

    }


    public void hasSuccessors() {

    }

}
