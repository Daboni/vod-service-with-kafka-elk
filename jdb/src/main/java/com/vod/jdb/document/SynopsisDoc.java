package com.vod.jdb.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@Document(indexName = "synopsis-index")
@Getter
@Data
public class SynopsisDoc {

    @Id
    String id;
    @Field(name = "content_id")
    String contentId;
    String title;
    String release_date;
    String poster_path;
    List<String> genres;
    Integer running_time;
    String overview;
    String director;
    List<String> actors;
    String certification;
    String date;
    Double voteAverage;
    Double userRating;
    List<Map<String,String>> recomm;
}
