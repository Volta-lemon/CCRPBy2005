package com.volta.project.service;

import com.volta.project.common.PageUtils;
import com.volta.project.model.entity.Artifact;
import org.springframework.data.domain.Page;

public interface ArtifactService {
    Artifact getArtifactById(Integer id);
    Artifact getSimilarArtifacts(Integer id);
    PageUtils getArtifactByDetail(int curPage,int pageSize,Integer startTime, Integer endTime, Integer isAscend, String author, String artifactName);
    //List<Artifact> searchArtifactsByKeyword(String keyword);

}
