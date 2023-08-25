package pl.skopinau.repoviewer.dto;

public record BranchInfo(
        String branchName,
        String lastCommitSha) {
}
