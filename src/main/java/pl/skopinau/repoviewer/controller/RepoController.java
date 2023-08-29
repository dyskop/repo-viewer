package pl.skopinau.repoviewer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.skopinau.repoviewer.dto.RepoInfo;
import pl.skopinau.repoviewer.exception.NotAcceptableException;
import pl.skopinau.repoviewer.service.RepoService;
import reactor.core.publisher.Flux;

import static org.springframework.http.HttpHeaders.ACCEPT;

@RestController
@RequestMapping("/api/v1/repos")
@RequiredArgsConstructor
public class RepoController {

    private final RepoService repoService;

    @GetMapping(value = "/{username}")
    public Flux<RepoInfo> getReposByUsername(
            @PathVariable String username,
            @RequestHeader(ACCEPT) String accept) {

        String requiredAccept = MediaType.APPLICATION_JSON_VALUE;
        if (!accept.equals(requiredAccept)) {
            throw new NotAcceptableException(requiredAccept);
        }
        return repoService.getByUsername(username);
    }
}
