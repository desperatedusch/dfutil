package de.dfutil.endpoints;


import de.dfutil.services.MunicipalityService;
import de.dfutil.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AGSRestEndpoint {

    @Autowired
    private MunicipalityService municipalityService;

    @Autowired
    private RegionService regionService;

    public AGSRestEndpoint(MunicipalityService municipalityService, RegionService regionService) {
        this.municipalityService = municipalityService;
        this.regionService = regionService;
    }
}
