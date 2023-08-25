package pl.skopinau.repoviewer.model;

import lombok.Data;

import java.util.List;

@Data
public class Repo {
    private String name;
    private Owner owner;
    private List<Branch> branches;

    public Repo withBranches(List<Branch> branches) {
        this.branches = branches;
        return this;
    }
}
