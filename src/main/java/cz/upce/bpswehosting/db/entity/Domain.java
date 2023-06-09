package cz.upce.bpswehosting.db.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
    name = "domain",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Domain extends AbstractEntity<Long> {

    private String name;
    private String basePath;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User domainOwner;

    @ManyToMany
    @JoinTable(
        name = "member",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "domain_id"))
    private List<User> members;

    public User getDomainOwner() {
        return domainOwner;
    }

    public Domain setDomainOwner(User owner) {
        this.domainOwner = owner;
        return this;
    }

    public String getName() {
        return name;
    }

    public Domain setName(String name) {
        this.name = name;
        return this;
    }

    public String getBasePath() {
        return basePath;
    }

    public Domain setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public List<User> getMembers() {
        return members;
    }

    public Domain setMembers(List<User> members) {
        this.members = members;
        return this;
    }
}
