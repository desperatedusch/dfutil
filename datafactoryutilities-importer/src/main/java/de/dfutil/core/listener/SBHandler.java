package de.dfutil.core.listener;

import de.dfutil.dao.jpa.SBDaoUsingJPA;
import de.dfutil.events.CreatedFrom;
import de.dfutil.events.MergeWith;
import org.springframework.stereotype.Service;

@Service
public class SBHandler implements DataFactoryImportHandler{

    private final SBDaoUsingJPA dao;

    public SBHandler(SBDaoUsingJPA dao) {
        this.dao = dao;
    }

    @Override
    public void handleCreate(CreatedFrom event) {

    }

    @Override
    public void handleMerge(MergeWith event) {

    }
}
