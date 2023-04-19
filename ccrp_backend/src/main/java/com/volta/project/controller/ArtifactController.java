package com.volta.project.controller;

import com.volta.project.common.BaseResponse;
import com.volta.project.common.ErrorCode;
import com.volta.project.common.PageUtils;
import com.volta.project.common.ResultUtils;
import com.volta.project.controller.exception.BusinessException;
import com.volta.project.model.entity.Artifact;
import com.volta.project.model.request.Artifact.ArtifactDetailRequest;
import com.volta.project.model.request.Artifact.ArtifactGetRequest;
import com.volta.project.model.request.Artifact.ArtifactSearchRequest;
import com.volta.project.model.request.Artifact.ArtifactSimilarRequest;
import com.volta.project.service.ArtifactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 文物接口
 *
 * @author zpf
 */
@RestController
@RequestMapping("/artifact")
@Api(tags = "文物相关操作接口")
public class ArtifactController {
    @Resource
    private ArtifactService artifactService;

    @GetMapping("/getArtifactById")
    @ApiOperation(value = "通过id获取文物")
    BaseResponse<Artifact>getArtifactById(@RequestBody ArtifactDetailRequest artifactDetailRequest)
    {
        if (artifactDetailRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(artifactDetailRequest.getId()==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer id=artifactDetailRequest.getId();
        return ResultUtils.success(artifactService.getArtifactById(id));
    }
    @GetMapping("/getArtifactsByDetail")
    @ApiOperation(value="通过文物具体信息，筛选或查询或搜索相关文物，进行分页查询，如果不需要对相应字段进行限定，就不传。分页信息必须传")
    BaseResponse<PageUtils>getArtifactsByDetail(@RequestBody ArtifactGetRequest artifactGetRequest)
    {
        if(artifactGetRequest==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(artifactGetRequest.getCurrPage()==null||artifactGetRequest.getPageSize()==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Artifact artifact=new Artifact();
        return ResultUtils.success(artifactService.getArtifactsByDetail(artifactGetRequest.getCurrPage(),artifactGetRequest.getPageSize(),artifactGetRequest.getStartTime(),artifactGetRequest.getEndTime(),artifactGetRequest.getIsAscend(),artifactGetRequest.getAuthor(),artifactGetRequest.getArtifactName()));
    }

    @GetMapping("/getSimilarArtifactsById")
    @ApiOperation(value="通过文物id,进行分页查询，查询相似文物的信息。分页信息和id必须传")
    BaseResponse<PageUtils>getSimilarArtifactsById(@RequestBody ArtifactSimilarRequest artifactSimilarRequest)
    {
        if(artifactSimilarRequest==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(artifactSimilarRequest.getCurrPage()==null||artifactSimilarRequest.getPageSize()==null||artifactSimilarRequest.getId()==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return ResultUtils.success(artifactService.getSimilarArtifacts(artifactSimilarRequest.getCurrPage(),artifactSimilarRequest.getPageSize(),artifactSimilarRequest.getId()));
    }
    @GetMapping("/searchArtifact")
    @ApiOperation(value="通过关键字,进行分页查询，查询相似文物的信息。分页信息和关键字必须传")
    BaseResponse<PageUtils>getSimilarArtifactsById(@RequestBody ArtifactSearchRequest artifactSearchRequest)
    {
        if(artifactSearchRequest==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(artifactSearchRequest.getCurrPage()==null||artifactSearchRequest.getPageSize()==null||artifactSearchRequest.getKeyword()==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return ResultUtils.success(artifactService.searchArtifactByKeyword(artifactSearchRequest.getCurrPage(),artifactSearchRequest.getPageSize(),artifactSearchRequest.getKeyword()));
    }

}
