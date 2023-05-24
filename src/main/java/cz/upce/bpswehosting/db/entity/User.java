package cz.upce.bpswehosting.db.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "users",
    uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "username")
    })
@Entity
public class User extends AbstractEntity<Long> {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "domainOwner")
    private List<Domain> ownedDomains;

    @ManyToMany(mappedBy = "members")
    private List<Domain> domains;

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<Domain> getOwnedDomains() {
        return ownedDomains;
    }

    public User setOwnedDomains(List<Domain> ownedDomains) {
        this.ownedDomains = ownedDomains;
        return this;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public User setDomains(List<Domain> domains) {
        this.domains = domains;
        return this;
    }


}
