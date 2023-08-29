package pl.skopinau.repoviewer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Repo {

    private String name;

    private Owner owner;

    private List<Branch> branches;

    private boolean fork;

    public Repo withBranches(List<Branch> branches) {
        this.branches = branches;
        return this;
    }
}
