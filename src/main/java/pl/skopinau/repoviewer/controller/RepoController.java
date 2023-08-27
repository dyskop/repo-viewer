package pl.skopinau.repoviewer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.skopinau.repoviewer.dto.RepoInfo;
import pl.skopinau.repoviewer.exception.UnsupportedMediaTypeException;
import pl.skopinau.repoviewer.service.RepoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static pl.skopinau.repoviewer.exception.Message.UNSUPPORTED_MEDIA_TYPE;

@RestController
@RequestMapping("/api/v1/repos")
@RequiredArgsConstructor
public class RepoController {

    private final RepoService repoService;

    @GetMapping(value = "/{username}")
    public Flux<RepoInfo> getReposByUsername(
            @PathVariable String username,
            @RequestHeader(HttpHeaders.ACCEPT) String accept) {

//        if (!accept.equals(MediaType.APPLICATION_JSON_VALUE)) {
//            throw new UnsupportedMediaTypeException(String.format(UNSUPPORTED_MEDIA_TYPE.getMessage(), accept));
//        }

        return repoService.getByUsername(username);
    }
}
