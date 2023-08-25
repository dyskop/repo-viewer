package pl.skopinau.repoviewer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.skopinau.repoviewer.dto.RepoInfo;
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
