package com.vod.jdb.repository;

import com.vod.jdb.document.SynopsisDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SynopsisRepository extends ElasticsearchRepository<SynopsisDoc,String> {

    SynopsisDoc findByContentIdOrderByDateDesc(String contentId);

}
