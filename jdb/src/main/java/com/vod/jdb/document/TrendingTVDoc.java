package com.vod.jdb.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder
@AllArgsConstructor
@Document(indexName = "trending-index")
@Getter
@Data
public class TrendingTVDoc {
    @Id
    String id;
    String content_id;
    String title;
    String release_date;
    String poster_path;
    @Field(type = FieldType.Date)
    String date;

}
