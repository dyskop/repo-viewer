package pl.skopinau.repoviewer.service;

import pl.skopinau.repoviewer.dto.RepoInfo;
import reactor.core.publisher.Flux;

public interface RepoService {

    Flux<RepoInfo> getByUserAndAcceptHeader(String username, String acceptHeader);
}
