package pl.skopinau.repoviewer.mapper;

import org.junit.jupiter.api.Test;
import pl.skopinau.repoviewer.data.TestDataFactory;
import pl.skopinau.repoviewer.dto.BranchInfo;

import static org.junit.jupiter.api.Assertions.*;

class BranchMapperTest {

    private final BranchMapper branchMapper = new BranchMapperImpl();

    @Test
    void branchToBranchInfo() {
        BranchInfo expected = TestDataFactory.branchInfo();
        BranchInfo actual = branchMapper.branchToBranchInfo(TestDataFactory.branch());
        assertEquals(expected, actual);
    }
}