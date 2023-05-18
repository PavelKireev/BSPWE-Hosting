package cz.upce.bpswehosting.service;

public interface DomainService {

    void create();
    void update();
    void delete();
    void addUser(Long userId);
    void removeUser(Long userId);

}
