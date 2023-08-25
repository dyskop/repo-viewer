package pl.skopinau.repoviewer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.skopinau.repoviewer.dto.RepoInfo;
import pl.skopinau.repoviewer.mapper.RepoMapper;
import pl.skopinau.repoviewer.model.Branch;
import pl.skopinau.repoviewer.model.Repo;
import pl.skopinau.repoviewer.service.RepoService;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class RepoServiceImpl implements RepoService {

    private final WebClient client;
    private final RepoMapper repoMapper;

    @Override
    public Flux<RepoInfo> getByUserAndAcceptHeader(String username, String acceptHeader) {
        return client.get()
                .uri("/users/{username}/repos", username)
                .accept(MediaType.parseMediaType(acceptHeader))
                .retrieve()
                .bodyToFlux(Repo.class)
                .filter(repo -> !repo.isFork())
                .flatMap(repo -> fetchBranches(repo, acceptHeader)
                        .collectList()
                        .map(repo::withBranches)
                        .map(repoMapper::repoToRepoInfo));
    }

    private Flux<Branch> fetchBranches(Repo repo, String acceptHeader) {
        return client.get()
                .uri("/repos/{username}/{repo}/branches", repo.getOwner().getLogin(), repo.getName())
                .accept(MediaType.parseMediaType(acceptHeader))
                .retrieve()
                .bodyToFlux(Branch.class);
    }
}
