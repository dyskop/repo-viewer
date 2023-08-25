package pl.skopinau.repoviewer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.skopinau.repoviewer.dto.RepoInfo;
import pl.skopinau.repoviewer.model.Repo;

@Mapper(uses = BranchMapper.class)
public interface RepoMapper {

    @Mapping(target = "repositoryName", source = "name")
    @Mapping(target = "ownerLogin", source = "repo.owner.login")
    RepoInfo repoToRepoInfo(Repo repo);
}
