package com.kynsof.treatments.infrastructure.repositories.job;

import com.kynsof.treatments.infrastructure.repositories.query.BusinessReadDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.ExternalConsultationReadDataJPARepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;

public class ExternalConsultationScheduled {
    private final ExternalConsultationReadDataJPARepository repositoryQueryExternalConsultation;
    private final BusinessReadDataJPARepository repositoryQueryBusiness;

    public ExternalConsultationScheduled(ExternalConsultationReadDataJPARepository repositoryQueryExternalConsultation,
                                         BusinessReadDataJPARepository repositoryQueryBusiness) {
        this.repositoryQueryExternalConsultation = repositoryQueryExternalConsultation;
        this.repositoryQueryBusiness = repositoryQueryBusiness;
    }

//    @Transactional
//    @Scheduled(cron = "0 0 1 * *?")
    //@Scheduled(cron = "0 * * * *?")//Segundo 0 de cada minuto
    public void reportExternalConsultationForBusiness() {
        
    }
}
