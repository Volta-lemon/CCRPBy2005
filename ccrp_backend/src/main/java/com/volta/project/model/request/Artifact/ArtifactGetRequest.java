package com.volta.project.model.request.Artifact;

import lombok.Data;

@Data
public class ArtifactGetRequest {
    private Integer pageSize;
    private Integer currPage;
    private Integer startTime;
    private Integer endTime;
    private Integer isAscend;
    private String author;
    private String artifactName;
}
