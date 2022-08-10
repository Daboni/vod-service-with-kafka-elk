package com.vod.jdb.service;

import com.vod.jdb.document.SynopsisDoc;
import com.vod.jdb.document.TrendingMovieDoc;
import com.vod.jdb.document.TrendingTVDoc;
import com.vod.jdb.repository.SynopsisRepository;
import com.vod.jdb.repository.TrendingMovieRepository;
import com.vod.jdb.repository.TrendingTVRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;
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

    private final ElasticsearchOperations elasticsearchOperations;

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

        Criteria cri = Criteria.where("date").is(date);
        Query query = new CriteriaQuery(cri);
        SearchHits<TrendingTVDoc> search = elasticsearchOperations.search(query, TrendingTVDoc.class);

        List<TrendingTVDoc> tvs = search.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList()).stream().filter((c) -> c.getContent_id().startsWith("TV")).collect(Collectors.toList());

        resultMap.put("result_data",tvs);

        return resultMap;
    }

    public Map<String,Object> getTrendingMovie() {

        Map<String,Object> resultMap = new HashMap<>();

        String date = LocalDate.now().toString();
//        System.out.println(date);
//        List<TrendingMovieDoc> trendingMovieDocs = trendingMovieRepository.findByDateEquals(date);

//        List<TrendingMovieDoc> mvs = trendingMovieDocs.stream().filter((c) -> c.getContent_id().startsWith("MV")).collect(Collectors.toList());

//        elasticsearchOperations.queryForObject()

        Criteria cri = Criteria.where("date").is(date);
        Query query = new CriteriaQuery(cri);
        SearchHits<TrendingMovieDoc> search = elasticsearchOperations.search(query, TrendingMovieDoc.class);

        List<TrendingMovieDoc> mvs = search.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList()).stream().filter((c) -> c.getContent_id().startsWith("MV")).collect(Collectors.toList());

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
