package de.dfutil.core.listener;

import de.dfutil.dao.jpa.SBDaoUsingJPA;
import de.dfutil.events.CreatedFrom;
import de.dfutil.events.MergeWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SBHandler implements DataFactoryImportHandler{

    @Autowired
    private SBDaoUsingJPA dao;


    public SBHandler() {    }

    @Override
    public void handleCreate(CreatedFrom event) {

    }

    @Override
    public void handleMerge(MergeWith event) {

    }
}
