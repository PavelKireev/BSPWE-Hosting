package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.db.entity.User;
import cz.upce.bpswehosting.model.user.RegistrationModel;
import cz.upce.bpswehosting.model.user.UpdateProfileModel;

public interface UserService {

    User getOne(Long id);
    void create(RegistrationModel model);
    void update(Long id, UpdateProfileModel model);

}
