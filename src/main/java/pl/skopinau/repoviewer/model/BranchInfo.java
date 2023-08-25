package pl.skopinau.repoviewer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchInfo {
    private String name;
    private Commit commit;
}
