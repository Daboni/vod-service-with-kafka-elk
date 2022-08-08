package com.vod.jdb.service;

import com.vod.jdb.document.SynopsisDoc;
import com.vod.jdb.document.TrendingMovieDoc;
import com.vod.jdb.document.TrendingTVDoc;
import com.vod.jdb.repository.SynopsisRepository;
import com.vod.jdb.repository.TrendingMovieRepository;
import com.vod.jdb.repository.TrendingTVRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private final TrendingTVRepository trendingTVRepository;
    private final TrendingMovieRepository trendingMovieRepository;
    private final SynopsisRepository synopsisRepository;

    @Transactional
    public void save(TrendingTVDoc dto) {
        trendingTVRepository.save(dto);
    }

    public List<TrendingTVDoc> findAll() {
        return trendingTVRepository.findAll();
    }

    public Map<String,Object> getTrendingTV() {

        Map<String,Object> resultMap = new HashMap<>();

        String date = LocalDate.now().toString();
        List<TrendingTVDoc> trendingTVDocs = trendingTVRepository.findByDateEquals(date);
        for(TrendingTVDoc tr : trendingTVDocs)
            System.out.println(tr);
        List<TrendingTVDoc> tvs = trendingTVDocs.stream().filter((c) -> c.getContent_id().startsWith("TV")).collect(Collectors.toList());

        for(TrendingTVDoc tr : tvs)
            System.out.println(tr);

        resultMap.put("result_data",tvs);

        return resultMap;
    }

    public Map<String,Object> getTrendingMovie() {

        Map<String,Object> resultMap = new HashMap<>();

        String date = LocalDate.now().toString();
        List<TrendingMovieDoc> trendingMovieDocs = trendingMovieRepository.findByDateEquals(date);

        List<TrendingMovieDoc> mvs = trendingMovieDocs.stream().filter((c) -> c.getContent_id().startsWith("MV")).collect(Collectors.toList());

        resultMap.put("result_data",mvs);
        return resultMap;
    }


    public Map<String, Object> getSynopsis(String content_id) {

        Map<String,Object> resultMap = new HashMap<>();

        SynopsisDoc synopsisDoc = synopsisRepository.findByContentId(content_id);
        System.out.println(synopsisDoc.toString());
        resultMap.put("result_data",synopsisDoc);
        return resultMap;

    }
}
