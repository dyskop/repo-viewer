package pl.skopinau.repoviewer.dto;

import lombok.Builder;

@Builder
public record BranchInfo(
        String branchName,
        String lastCommitSha) {
}
