package pl.skopinau.repoviewer.service;

import pl.skopinau.repoviewer.dto.RepoInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RepoService {

    Flux<RepoInfo> getByUsername(String username);
}
