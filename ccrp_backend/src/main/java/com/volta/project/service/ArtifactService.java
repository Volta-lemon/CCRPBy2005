package com.volta.project.service;

import com.volta.project.common.PageUtils;
import com.volta.project.model.entity.Artifact;
import org.springframework.data.domain.Page;

public interface ArtifactService {
    Artifact getArtifactById(Integer id);
    PageUtils getSimilarArtifacts(int curPage,int pageSize,Integer id);
    PageUtils getArtifactsByDetail(int curPage,int pageSize,Integer startTime, Integer endTime, Integer isAscend, String author, String artifactName);

    PageUtils searchArtifactByKeyword(int curPage,int pageSize,String keyword);
}
