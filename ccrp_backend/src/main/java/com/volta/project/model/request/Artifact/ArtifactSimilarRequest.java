package com.volta.project.model.request.Artifact;

import lombok.Data;

@Data
public class ArtifactSimilarRequest {
    private Integer pageSize;
    private Integer currPage;
    private Integer id;
}
