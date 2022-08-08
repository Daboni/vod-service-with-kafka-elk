package com.vod.jdb.repository;

import com.vod.jdb.document.TrendingTVDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrendingTVRepository extends ElasticsearchRepository<TrendingTVDoc,String> {

    List<TrendingTVDoc> findAll();
    List<TrendingTVDoc> findByDateEquals(String date);
}
