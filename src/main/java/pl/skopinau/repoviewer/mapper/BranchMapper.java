package pl.skopinau.repoviewer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.skopinau.repoviewer.dto.BranchInfo;
import pl.skopinau.repoviewer.model.Branch;

@Mapper
public interface BranchMapper {

    @Mapping(target = "branchName", source = "branch.name")
    @Mapping(target = "lastCommitSha", source = "branch.commit.sha")
    BranchInfo branchToBranchInfo(Branch branch);
}
