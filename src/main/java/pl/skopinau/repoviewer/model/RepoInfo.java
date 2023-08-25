package pl.skopinau.repoviewer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepoInfo {
    private String name;
    private Owner owner;
    private List<BranchInfo> branches;

    public RepoInfo withBranches(List<BranchInfo> branches) {
        this.branches = branches;
        return this;
    }
}
