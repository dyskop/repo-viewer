package pl.skopinau.repoviewer.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.skopinau.repoviewer.data.TestDataFactory;
import pl.skopinau.repoviewer.dto.RepoInfo;
import pl.skopinau.repoviewer.exception.ExceptionDetails;
import pl.skopinau.repoviewer.exception.UserNotFoundException;
import pl.skopinau.repoviewer.service.RepoService;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.parseMediaType;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class RepoControllerIntegrationTest {

    @Autowired
    private WebTestClient testClient;

    @MockBean
    private RepoService repoService;

    private final List<RepoInfo> repoInfos = List.of(
            TestDataFactory.notForkedRepoInfo(),
            TestDataFactory.forkedRepoInfo());

    @ParameterizedTest
    @MethodSource("pl.skopinau.repoviewer.data.TestDataFactory#withValidAccept")
    @DisplayName("When username and accept header are valid then returns flux of RepoInfo and 200")
    void getReposByUsername_ok(String username, String accept) {

        Flux<RepoInfo> repoInfoFlux = Flux.fromIterable(repoInfos);

        when(repoService.getByUsername(username)).thenReturn(repoInfoFlux);

        testClient.get()
                .uri("/api/v1/repos/{username}", username)
                .accept(parseMediaType(accept))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RepoInfo.class).isEqualTo(repoInfos).hasSize(2);
    }

    @ParameterizedTest
    @MethodSource("pl.skopinau.repoviewer.data.TestDataFactory#withValidAccept")
    @DisplayName("When username is invalid and accept header is valid then returns ExceptionDetails and 404")
    void getReposByUsername_notFound(String username, String accept) {

        ExceptionDetails exceptionDetails = TestDataFactory.notFound(username);
        Flux<RepoInfo> repoInfoFlux = Flux.error(new UserNotFoundException(username));

        when(repoService.getByUsername(username)).thenReturn(repoInfoFlux);

        testClient.get()
                .uri("/api/v1/repos/{username}", username)
                .accept(parseMediaType(accept))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ExceptionDetails.class).isEqualTo(exceptionDetails);
    }

    @ParameterizedTest
    @MethodSource("pl.skopinau.repoviewer.data.TestDataFactory#withInvalidAccept")
    @DisplayName("When username is valid and accept header is invalid then returns ExceptionDetails and 406")
    void getReposByUsername_notAcceptable(String username, String accept) {

        ExceptionDetails exceptionDetails = TestDataFactory.notAcceptable(APPLICATION_JSON_VALUE);
        Flux<RepoInfo> repoInfoFlux = Flux.error(new UserNotFoundException(username));

        when(repoService.getByUsername(username)).thenReturn(repoInfoFlux);

        testClient.get()
                .uri("/api/v1/repos/{username}", username)
                .accept(parseMediaType(accept))
                .exchange()
                .expectStatus().isEqualTo(NOT_ACCEPTABLE)
                .expectBody(ExceptionDetails.class).isEqualTo(exceptionDetails);
    }
}