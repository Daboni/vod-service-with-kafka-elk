package jdb.vod.scheduler.service;


import jdb.vod.scheduler.domain.Content;
import jdb.vod.scheduler.domain.NaverRating;
import jdb.vod.scheduler.domain.Synopsis;
import jdb.vod.scheduler.domain.TmdbResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiCallService {

    private final KafkaTemplate<String, Content> kafkaTemplate;
    private final KafkaTemplate<String, Synopsis> kafkaTemplate2;

    private static String apiKey = "4cffcf5836a3f497346626d7389864ba";
    private static String lang = "&language=ko-KR";

    public static Map<String, List<String>> ids;

    public void init() {
        ids = new HashMap<>();
    }
    public void produceTrendingList() {

        String[] types = {"movie", "tv"};
        String path = "https://image.tmdb.org/t/p/w342";
        for (String type : types) {

            List<String> typeIds = new ArrayList<>();

            String uri = "https://api.themoviedb.org/3/trending/" + type + "/day?api_key=";
            uri += apiKey + lang;

            log.info("trending uri : {}", uri);
            TmdbResponse.TrendingListRes tmdbRes = WebClient.builder().build().get().uri(uri).retrieve().bodyToMono(TmdbResponse.TrendingListRes.class).block();

            int i = 0;
            for (Map<String, Object> result : tmdbRes.results) {
                if (i++ == 5) break;
                String content_id = type.equals("tv") ? "TV" + result.get("id") : "MV" + result.get("id");
                String title = type.equals("tv") ? result.get("name").toString() : result.get("title").toString();
                String release_date = type.equals("tv") ? result.get("first_air_date").toString() : result.get("release_date").toString();
                String poster_path = path + result.get("poster_path");
                String date = LocalDate.now().toString();
                typeIds.add(result.get("id").toString());

                Content trending = Content.builder().type("trending").content_id(content_id).title(title).release_date(release_date).poster_path(poster_path).date(date).build();
                log.info("trending contents : {}",trending.toString());
                kafkaTemplate.send("springboot-kafka-elk",trending);
            }
            ids.put(type,typeIds);
//            map.put(type,content);
        }

    }
    public void produceSynopsis () {

        produceMVSynopsis();
        produceTVSynopsis();

    }

    private void produceTVSynopsis() {

        String path = "https://image.tmdb.org/t/p/w500";

        for (String id : ids.get("tv")) {

            String uri = "https://api.themoviedb.org/3/tv/" + id + "?api_key=";
            uri += apiKey + lang;
            uri += "&append_to_response=content_ratings";

            TmdbResponse.SynopsisTVRes synopsisTVRes = WebClient.builder().build().get().uri(uri).retrieve().bodyToMono(TmdbResponse.SynopsisTVRes.class).block();
            String s = WebClient.builder().build().get().uri(uri).retrieve().bodyToMono(String.class).block();

            String content_id = "TV" + synopsisTVRes.id;
            String title = synopsisTVRes.name;
            String release_date = synopsisTVRes.first_air_date;
            String poster_path = path + synopsisTVRes.poster_path;
            List<String> genrs = new ArrayList<>();
            Integer runningTime = synopsisTVRes.episode_run_time.size()==0 ? 50 : synopsisTVRes.episode_run_time.get(0);
            String overview = synopsisTVRes.overview;
            if(overview.equals("")) overview="정보 없음";
            String director = "";
            List<String> actors = new ArrayList<>();
            String certification = "ALL";
            Double voteAverage = Math.ceil(synopsisTVRes.vote_average/2*10)/10;
            String date = LocalDate.now().toString();
            List<Map<String,String>> recomm = new ArrayList<>();
            Double userRating = 0.0;

            for(Map<String,String> cb : synopsisTVRes.created_by) {
                director = cb.get("name");
            }

            for(Map<String,String> genr : synopsisTVRes.genres) {
                genrs.add(genr.get("name"));
            }
            log.info("tv detail uri : {}", uri);
            for(Map<String,String> cert : synopsisTVRes.content_ratings.get("results")) {
                if(cert.get("iso_3611_1")==null) continue;
                if(cert.get("iso_3166_1").equals("KR") && !cert.get("certification").equals("")) certification = cert.get("rating");
            }

            String creditUri = "https://api.themoviedb.org/3/tv/"+id+"/credits?api_key="+apiKey+lang;
            TmdbResponse.Credit credit = WebClient.builder().build().get().uri(creditUri).retrieve().bodyToMono(TmdbResponse.Credit.class).block();
            log.info("tv credit uri : {} ", creditUri);
            int i =0;
            for(Map<String,String> actor : credit.cast) {
                if(i++ == 5) break;
                actors.add(actor.get("name"));
            }

            String recommUri = "https://api.themoviedb.org/3/tv/"+id+"/recommendations?api_key="+apiKey+lang+"&page=1";
            TmdbResponse.RecommendationsTV recomRes = WebClient.builder().build().get().uri(recommUri).retrieve().bodyToMono(TmdbResponse.RecommendationsTV.class).block();
            log.info("tv recomm uri : {}", recommUri);
            for(int k=0;k<5;k++) {
                if(recomRes.results.size()==0) break;
                Map<String,String> recommContent = new HashMap<>();
                Map<String,Object> res = recomRes.results.get(k);
                recommContent.put("content_id","TV" + res.get("id"));
                recommContent.put("title",res.get("name").toString());
                recommContent.put("poster_path","https://image.tmdb.org/t/p/w185"+res.get("poster_path"));
                recommContent.put("release_date",res.get("first_air_date").toString());
                recomm.add(recommContent);
            }
            Synopsis synopsis = Synopsis.builder().type("synopsis").content_id(content_id).title(title).release_date(release_date).poster_path(poster_path)
                    .genrs(genrs).running_time(runningTime).overview(overview).director(director).actors(actors).certification(certification)
                    .voteAverage(voteAverage).date(date).recomm(recomm).userRating(userRating).build();
            log.info("tv synopsis : {}",synopsis.toString());
            kafkaTemplate2.send("springboot-kafka-elk",synopsis);
        }
    }

    private void produceMVSynopsis() {

        String path = "https://image.tmdb.org/t/p/w500";

        for (String id : ids.get("movie")) {

            String uri = "https://api.themoviedb.org/3/movie/" + id + "?api_key=";
            uri += apiKey + lang;
            uri += "&append_to_response=releases";

            TmdbResponse.SynopsisMVRes synopsisMVRes = WebClient.builder().build().get().uri(uri).retrieve().bodyToMono(TmdbResponse.SynopsisMVRes.class).block();
//            String s = WebClient.builder().build().get().uri(uri).retrieve().bodyToMono(String.class).block();

            String content_id = "MV" + synopsisMVRes.id;
            String title = synopsisMVRes.title;
            String release_date = synopsisMVRes.release_date;
            String poster_path = path + synopsisMVRes.poster_path;
            List<String> genrs = new ArrayList<>();
            Integer runningTime = synopsisMVRes.runtime;
            String overview = synopsisMVRes.overview;
            if(overview.equals("")) overview="정보 없음";
            String director = "";
            List<String> actors = new ArrayList<>();
            String certification = "ALL";
            Double voteAverage = Math.ceil(synopsisMVRes.vote_average/2*10)/10;
            String date = LocalDate.now().toString();
            List<Map<String,String>> recomm = new ArrayList<>();

            log.info("mv detail : {}",uri);
            URI naverUri = UriComponentsBuilder.fromUriString("https://openapi.naver.com").
                    path("/v1/search/movie.json")
                    .queryParam("query", title)
                    .queryParam("display", 1)
                    .queryParam("yearfrom", release_date)
                    .queryParam("yearto", release_date)
                    .encode()
                    .build()
                    .toUri();

            RequestEntity requestEntity = RequestEntity
                    .get(naverUri)
                    .header("X-Naver-Client-Id","LVt9VKSNulyrJzbvOAL_")
                    .header("X-Naver-Client-Secret","vwUfz39adG")
                    .build();
            log.info("naver api uri : {}",naverUri);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<NaverRating> searchList = restTemplate.exchange(requestEntity, NaverRating.class);

            Double userRating = 0.0;
            if(searchList.getBody().getItems().size()!=0)
                userRating = Math.ceil(Double.valueOf(searchList.getBody().getItems().get(0).get("userRating")) / 2 * 10) / 10;

            for(Map<String,String> genr : synopsisMVRes.genres) {
                genrs.add(genr.get("name"));
            }
            for(Map<String,String> cert : synopsisMVRes.releases.get("countries")) {
                if(cert.get("iso_3166_1").equals("KR")) certification = cert.get("certification");
            }

            String creditUri = "https://api.themoviedb.org/3/movie/"+id+"/credits?api_key="+apiKey+lang;
            TmdbResponse.Credit credit = WebClient.builder().build().get().uri(creditUri).retrieve().bodyToMono(TmdbResponse.Credit.class).block();
            log.info("mv credit uri : {}",creditUri);
            int i =0;
            for(Map<String,String> actor : credit.cast) {
                if(i++ == 5) break;
                actors.add(actor.get("name"));
            }

            for(Map<String,String> cr : credit.crew) {
                if(cr.get("job").equals("Director")) director = cr.get("name");
            }
            String recommUri = "https://api.themoviedb.org/3/movie/"+id+"/recommendations?api_key="+apiKey+lang+"&page=1";
            TmdbResponse.RecommendationsTV recomRes = WebClient.builder().build().get().uri(recommUri).retrieve().bodyToMono(TmdbResponse.RecommendationsTV.class).block();
            log.info("mv recomm uri : {}",recommUri);
            for(int k=0;k<5;k++) {
                if(recomRes.results.size()==0) break;

                Map<String,String> recommContent = new HashMap<>();
                Map<String,Object> res = recomRes.results.get(k);

                recommContent.put("content_id","MV" + res.get("id"));
                recommContent.put("title",res.get("title").toString());
                recommContent.put("release_date",res.get("release_date").toString());
                recommContent.put("poster_path","https://image.tmdb.org/t/p/w185"+res.get("poster_path"));
                recomm.add(recommContent);
            }

            Synopsis synopsis = Synopsis.builder().type("synopsis").content_id(content_id).title(title).release_date(release_date).poster_path(poster_path)
                    .genrs(genrs).running_time(runningTime).overview(overview).director(director).actors(actors).certification(certification)
                    .voteAverage(voteAverage).date(date).recomm(recomm).userRating(userRating).build();

            System.out.println(synopsis);
            log.info("mv synopsis : {}",synopsis.toString());

            kafkaTemplate2.send("springboot-kafka-elk",synopsis);

        }
    }
}
