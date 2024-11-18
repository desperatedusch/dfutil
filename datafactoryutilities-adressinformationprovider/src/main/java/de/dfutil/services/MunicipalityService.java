package de.dfutil.services;

import de.dfutil.dao.SbDao;
import de.dfutil.dao.jpa.SBDaoUsingJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MunicipalityService {

    @Autowired
    private SbDao sbDao;

    public MunicipalityService(SBDaoUsingJPA sbDao) {
        this.sbDao = sbDao;
    }
}
