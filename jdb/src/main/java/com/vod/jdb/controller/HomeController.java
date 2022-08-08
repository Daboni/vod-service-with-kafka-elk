package com.vod.jdb.controller;

import com.vod.jdb.service.HomeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/euxp/api/get")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/tv")
    public ResponseEntity<TrendingTVDto> getTrendingTV() {

        Map<String, Object> trendingTV = homeService.getTrendingTV();

        TrendingTVDto trendingTVDto = new TrendingTVDto();
        trendingTVDto.setResult_data(trendingTV);

        return new ResponseEntity<>(trendingTVDto, HttpStatus.OK);
    }

    @GetMapping("/movie")
    public ResponseEntity<TrendingMovieDto> getTrendingMovie() {

        Map<String, Object> trendingMovie = homeService.getTrendingMovie();

        TrendingMovieDto trendingMovieDto = new TrendingMovieDto();
        trendingMovieDto.setResult_data(trendingMovie);

        return new ResponseEntity<>(trendingMovieDto, HttpStatus.OK);
    }

    @GetMapping("/{content_id}")
    public ResponseEntity<SynopsisDto> getSynopsis(@PathVariable String content_id) {

        Map<String, Object> synopsis = homeService.getSynopsis(content_id);

        SynopsisDto synopsisDto = new SynopsisDto();
        synopsisDto.setResult_data(synopsis);

        return new ResponseEntity<>(synopsisDto, HttpStatus.OK);
    }


    @Getter
    private class TrendingTVDto {

        String if_no = "IF-EUXP.GET.API-001";
        String ver = "v1";
        String svc_name = "CONTENT_INFO_GET_API";
        String result = "0000";
        String reason = "RTN_SUCCESS";
        Map<String,Object> result_data;

        public void setResult_data(Map<String, Object> result_data) {
            this.result_data = result_data;
        }
    }

    @Getter
    private class TrendingMovieDto {

        String if_no = "IF-EUXP.GET.API-002";
        String ver = "v1";
        String svc_name = "CONTENT_INFO_GET_API";
        String result = "0000";
        String reason = "RTN_SUCCESS";
        Map<String,Object> result_data;

        public void setResult_data(Map<String, Object> result_data) {
            this.result_data = result_data;
        }
    }

    @Getter
    private class SynopsisDto {

        String if_no = "IF-EUXP.GET.API-003";
        String ver = "v1";
        String svc_name = "CONTENT_INFO_GET_API";
        String result = "0000";
        String reason = "RTN_SUCCESS";
        Map<String,Object> result_data;

        public void setResult_data(Map<String, Object> result_data) {
            this.result_data = result_data;
        }
    }

}
