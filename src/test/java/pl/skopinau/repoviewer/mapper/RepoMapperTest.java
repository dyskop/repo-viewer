package pl.skopinau.repoviewer.mapper;

import org.junit.jupiter.api.Test;
import pl.skopinau.repoviewer.data.TestDataFactory;
import pl.skopinau.repoviewer.dto.RepoInfo;

import static org.junit.jupiter.api.Assertions.*;

class RepoMapperTest {

    private final BranchMapper branchMapper = new BranchMapperImpl();

    private final RepoMapper repoMapper = new RepoMapperImpl(branchMapper);

    @Test
    void repoToRepoInfo() {
        RepoInfo expected = TestDataFactory.notForkedRepoInfo();
        RepoInfo actual = repoMapper.repoToRepoInfo(TestDataFactory.notForkedRepo());
        assertEquals(expected, actual);
    }
}