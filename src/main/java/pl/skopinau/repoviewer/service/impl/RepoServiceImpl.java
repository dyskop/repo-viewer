package pl.skopinau.repoviewer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.skopinau.repoviewer.dto.RepoInfo;
import pl.skopinau.repoviewer.exception.UserNotExistsException;
import pl.skopinau.repoviewer.mapper.RepoMapper;
import pl.skopinau.repoviewer.model.Branch;
import pl.skopinau.repoviewer.model.Repo;
import pl.skopinau.repoviewer.service.RepoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static pl.skopinau.repoviewer.exception.Message.USER_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class RepoServiceImpl implements RepoService {

    private final WebClient client;
    private final RepoMapper repoMapper;

    @Override
    public Flux<RepoInfo> getByUsername(String username) {
        return client.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .onRawStatus(code -> code == HttpStatus.NOT_FOUND.value(), clientResponse -> Mono.error(new UserNotExistsException(String.format(USER_NOT_EXISTS.getMessage(), username))))

                .bodyToFlux(Repo.class)
                .filter(repo -> !repo.isFork())
                .flatMap(repo -> fetchBranches(repo)
                        .collectList()
                        .map(repo::withBranches)
                        .map(repoMapper::repoToRepoInfo));
    }

    private Flux<Branch> fetchBranches(Repo repo) {
        return client.get()
                .uri("/repos/{username}/{repo}/branches", repo.getOwner().getLogin(), repo.getName())
                .retrieve()
                .bodyToFlux(Branch.class);
    }
}
