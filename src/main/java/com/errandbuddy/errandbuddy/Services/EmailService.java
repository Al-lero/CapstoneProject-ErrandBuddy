package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Dto.request.EmailDetails;

public interface EmailService {

    void sendEmailAlert(EmailDetails emailDetails);

    void sendEmailAlert(String email, String newErrand, String messageBody);
}
