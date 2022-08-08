package com.vod.jdb.repository;

import com.vod.jdb.document.TrendingMovieDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrendingMovieRepository extends ElasticsearchRepository<TrendingMovieDoc,String> {

    List<TrendingMovieDoc> findAll();
    List<TrendingMovieDoc> findByDateEquals(String date);

}
