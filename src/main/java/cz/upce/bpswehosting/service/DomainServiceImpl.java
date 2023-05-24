package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.config.FtpConnection;
import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.db.entity.User;
import cz.upce.bpswehosting.db.repository.DomainRepository;
import cz.upce.bpswehosting.dto.DirectoryElement;
import cz.upce.bpswehosting.dto.DomainDto;
import cz.upce.bpswehosting.model.domain.CreateDomainModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DomainServiceImpl implements DomainService {

    private static final String BASE_PATH = "/home/ftp_admin";

    private final FtpConnection ftpConnection;
    private final UserService userService;
    private final DomainRepository domainRepository;

    @Override
    public List<DomainDto> create(CreateDomainModel model) {
        Domain domain = new Domain().setName(model.getDomainName())
                                    .setBasePath('/' + model.getDomainName())
                                    .setDomainOwner(userService.getOne(model.getOwnerId()));

        domain = domainRepository.save(domain);
        createDirectory(domain.getId(), "", domain.getName());
        return findAllByOwnerId(model.getOwnerId());
    }

    @Override
    public Domain addUser(Long userId, Long domainId) {
        User user = userService.getOne(userId);
        Domain domain = domainRepository.findById(domainId).orElseThrow(EntityNotFoundException::new);
        domain.getMembers().add(user);
        return domainRepository.save(domain);
    }

    @Override
    public Domain removeUser(Long userId, Long domainId) {
        User user = userService.getOne(userId);
        Domain domain = domainRepository.findById(domainId).orElseThrow(EntityNotFoundException::new);
        domain.getMembers().remove(user);
        return domainRepository.save(domain);
    }

    @Override
    public Domain getOne(Long domainId) {
        return domainRepository.findById(domainId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<DomainDto> findAllByOwnerId(Long ownerId) {
        return domainRepository.findAllByDomainOwnerId(ownerId)
                               .stream()
                               .map(DomainDto::new)
                               .collect(Collectors.toList());
    }

    @Override
    public List<Domain> findAllByOwnerUsername(String username) {
        return domainRepository.findAllByDomainOwnerUsername(username);
    }

    private List<DomainDto> createDirectory(Long domainId, String path, String name) {
        Domain domain = domainRepository.findById(domainId).orElseThrow(EntityNotFoundException::new);
        try {
            ftpConnection.getFtpClient().changeWorkingDirectory(BASE_PATH);
            ftpConnection.getFtpClient().makeDirectory(domain.getBasePath().substring(1));
            return findAllByOwnerId(domain.getDomainOwner().getId());
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return Collections.emptyList();
        }
    }

}
