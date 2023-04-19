package com.volta.project.model.request;

import lombok.Data;

@Data
public class ArtifactGetRequest {
    private int pageSize;
    private int currPage;
    private Integer startTime;
    private Integer endTime;
    private Integer isAscend;
    private String author;
    private String artifactName;
}
