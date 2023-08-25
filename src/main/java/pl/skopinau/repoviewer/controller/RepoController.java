package pl.skopinau.repoviewer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.skopinau.repoviewer.model.RepoInfo;
import pl.skopinau.repoviewer.service.RepoService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/repos")
@RequiredArgsConstructor
public class RepoController {

    private final RepoService repoService;

    @GetMapping("/{username}")
    public Flux<RepoInfo> getReposByUsername(
            @PathVariable String username,
            @RequestHeader("Accept") String acceptHeader) {
        return repoService.getByUserAndAcceptHeader(username, acceptHeader);
    }
}
