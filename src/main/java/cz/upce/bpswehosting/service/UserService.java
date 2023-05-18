package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.model.user.RegistrationModel;
import cz.upce.bpswehosting.model.user.UpdateProfileModel;

public interface UserService {

    void create(RegistrationModel model);
    void update(Long id, UpdateProfileModel model);

}
