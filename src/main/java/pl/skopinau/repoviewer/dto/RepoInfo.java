package pl.skopinau.repoviewer.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RepoInfo(
        String repositoryName,
        String ownerLogin,
        List<BranchInfo> branches) {
}
