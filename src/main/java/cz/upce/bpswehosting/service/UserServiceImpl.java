package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.db.entity.User;
import cz.upce.bpswehosting.db.repository.UserRepository;
import cz.upce.bpswehosting.model.user.RegistrationModel;
import cz.upce.bpswehosting.model.user.UpdateProfileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private PasswordEncoder encoder;
    private UserRepository userRepository;

    @Override
    public void create(RegistrationModel model) {
        userRepository.save(
            new User().setUsername(model.getUsername())
                      .setFirstName(model.getFirstName())
                      .setLastName(model.getLastName())
                      .setPassword(encoder.encode(model.getPassword()))
        );
    }

    @Override
    public void update(Long id, UpdateProfileModel model) {
        userRepository.save(
            new User().setFirstName(model.getFirstName())
                      .setLastName(model.getLastName())
        );
    }
}
