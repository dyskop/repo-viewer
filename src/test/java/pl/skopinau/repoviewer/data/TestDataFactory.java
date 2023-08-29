package pl.skopinau.repoviewer.data;

import org.junit.jupiter.params.provider.Arguments;
import pl.skopinau.repoviewer.dto.BranchInfo;
import pl.skopinau.repoviewer.dto.RepoInfo;
import pl.skopinau.repoviewer.exception.ExceptionDetails;
import pl.skopinau.repoviewer.exception.Message;
import pl.skopinau.repoviewer.model.Branch;
import pl.skopinau.repoviewer.model.Commit;
import pl.skopinau.repoviewer.model.Owner;
import pl.skopinau.repoviewer.model.Repo;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.*;

public class TestDataFactory {

    public static Commit commit() {
        return Commit.builder()
                .sha("sha")
                .build();
    }

    public static Owner owner() {
        return Owner.builder()
                .login("login")
                .build();
    }

    public static Branch branch() {
        return Branch.builder()
                .name("branch-name")
                .commit(commit())
                .build();
    }

    public static Repo notForkedRepo() {
        return Repo.builder()
                .name("not-forked-repo")
                .owner(owner())
                .branches(List.of(branch()))
                .fork(false)
                .build();
    }

    public static Repo forkedRepo() {
        return Repo.builder()
                .name("forked-repo")
                .owner(owner())
                .branches(List.of(branch()))
                .fork(true)
                .build();
    }

    public static BranchInfo branchInfo() {
        return BranchInfo.builder()
                .branchName("branch-name")
                .lastCommitSha("sha")
                .build();
    }

    public static RepoInfo notForkedRepoInfo() {
        return RepoInfo.builder()
                .repositoryName("not-forked-repo")
                .ownerLogin("login")
                .branches(List.of(branchInfo()))
                .build();
    }

    public static RepoInfo forkedRepoInfo() {
        return RepoInfo.builder()
                .repositoryName("forked-repo")
                .ownerLogin("login")
                .branches(List.of(branchInfo()))
                .build();
    }

    public static ExceptionDetails notFound(String username) {
        return ExceptionDetails.builder()
                .status(NOT_FOUND.value())
                .message(String.format(Message.USER_NOT_FOUND.getMessage(), username))
                .build();
    }

    public static ExceptionDetails notAcceptable(String requiredAccept) {
        return ExceptionDetails.builder()
                .status(NOT_ACCEPTABLE.value())
                .message(String.format(Message.NOT_ACCEPTABLE.getMessage(), requiredAccept))
                .build();
    }

    private static Stream<Arguments> withValidAccept() {
        return Stream.of(arguments("login", APPLICATION_JSON_VALUE));
    }

    private static Stream<Arguments> withInvalidAccept() {
        return Stream.of(
                arguments("login", APPLICATION_XML_VALUE),
                arguments("login", APPLICATION_NDJSON_VALUE),
                arguments("login", ALL_VALUE)
        );
    }
}
