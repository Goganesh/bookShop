package com.goganesh.bookshop.service;

import com.goganesh.bookshop.domain.User;
import com.goganesh.bookshop.dto.ContactConfirmationPayload;
import com.goganesh.bookshop.dto.ContactConfirmationResponse;
import com.goganesh.bookshop.dto.RegistrationForm;

public interface UserRegisterService {

    ContactConfirmationResponse login(ContactConfirmationPayload payload);

    void registerNewUser(RegistrationForm registrationForm);
    User getCurrentUser();

    void mergeTempUserDataToUser(User tempUser, User user);

    void registerTempUser(String email);
    void blockTempUser(User tempUser);
    void deleteBlockedTempUser(User tempUser);
}
