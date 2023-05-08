package com.volta.project.model.request.Artifact;

import lombok.Data;

@Data
public class ArtifactSearchRequest {
    private Integer pageSize;
    private Integer currPage;
    private String keyword;
}
