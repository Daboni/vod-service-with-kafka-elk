package jdb.vod.scheduler.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class TmdbResponse {

    @Data
    public static class TrendingListRes {
        public String classification;
        public List<Map<String,Object>> results;
    }

    @Data
    public static class SynopsisTVRes {

        public String id;

        public List<Map<String,String>> created_by;
        public List<Map<String,String>> genres;
        public List<Integer> episode_run_time;
        public String overview;
        public String first_air_date;
        public String name;
        public String poster_path;
        public Double vote_average;
        public Map<String,List<Map<String,String>>> content_ratings;

    }

    @Data
    public static class Credit {
        public List<Map<String,String>> cast;
        public List<Map<String,String>> crew;

    }

    @Data
    public static class SynopsisMVRes {

        public String id;

        public List<Map<String,String>> genres;
        public Integer runtime;
        public String overview;
        public String release_date;
        public String title;
        public String poster_path;
        public Double vote_average;
        public Map<String,List<Map<String,String>>> releases;

    }

    @Data
    public static class RecommendationsTV {
        public List<Map<String,Object>> results;
    }

    @Data
    public static class RecommendationsMV {
        public Map<String,Object> results;
    }
}
