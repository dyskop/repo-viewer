package pl.skopinau.repoviewer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.skopinau.repoviewer.model.BranchInfo;
import pl.skopinau.repoviewer.model.RepoInfo;
import pl.skopinau.repoviewer.service.RepoService;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class RepoServiceImpl implements RepoService {

    private final WebClient client;

    @Override
    public Flux<RepoInfo> getByUserAndAcceptHeader(String username, String acceptHeader) {
        return client.get()
                .uri("/users/{username}/repos", username)
                .accept(MediaType.parseMediaType(acceptHeader))
                .retrieve()
                .bodyToFlux(RepoInfo.class)
                .flatMap(repoInfo -> fetchBranches(repoInfo, acceptHeader)
                        .collectList()
                        .map(repoInfo::withBranches));
    }

    private Flux<BranchInfo> fetchBranches(RepoInfo repoInfo, String acceptHeader) {
        return client.get()
                .uri("/repos/{username}/{repo}/branches", repoInfo.getOwner().getLogin(), repoInfo.getName())
                .accept(MediaType.parseMediaType(acceptHeader))
                .retrieve()
                .bodyToFlux(BranchInfo.class);
    }
}
