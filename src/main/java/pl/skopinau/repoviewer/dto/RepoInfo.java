package pl.skopinau.repoviewer.dto;

import java.util.List;

public record RepoInfo(
        String repositoryName,
        String ownerLogin,
        List<BranchInfo> branches) {
}
