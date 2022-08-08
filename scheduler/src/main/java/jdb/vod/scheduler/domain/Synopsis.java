package jdb.vod.scheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class Synopsis {

    String type;
    String content_id;
    String title;
    String release_date;
    String poster_path;
    List<String> genrs;
    Integer running_time;
    String overview;
    String director;
    List<String> actors;
    String certification;
    Double voteAverage;
    Double userRating;
    String date;
    List<Map<String,String>> recomm;

}
