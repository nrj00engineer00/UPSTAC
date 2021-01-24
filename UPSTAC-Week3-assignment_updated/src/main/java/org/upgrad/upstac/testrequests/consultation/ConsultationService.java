package org.upgrad.upstac.testrequests.consultation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.upgrad.upstac.config.security.UserLoggedInService;
import org.upgrad.upstac.exception.AppException;
import org.upgrad.upstac.testrequests.TestRequest;
import org.upgrad.upstac.users.User;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Validated
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    private static Logger logger = LoggerFactory.getLogger(ConsultationService.class);

    @Autowired
    private UserLoggedInService userLoggedInService;


    @Transactional
    public Consultation assignForConsultation( TestRequest testRequest, User doctor) {
        Consultation consultation = new Consultation();
        consultation.setDoctor(doctor);
        consultation.setRequest(testRequest);

        return    consultationRepository.save(consultation);


    }

    public Consultation updateConsultation(TestRequest testRequest1 , CreateConsultationRequest createConsultationRequest) {

            Consultation consultation1 = consultationRepository.findByRequest(testRequest1).orElseThrow(()-> new AppException("Invalid Rrequest"));


        consultation1.setSuggestion(createConsultationRequest.getSuggestion());
        consultation1.setComments(createConsultationRequest.getComments());
        consultation1.setUpdatedOn(LocalDate.now());

        return consultationRepository.save(consultation1);


    }


}
