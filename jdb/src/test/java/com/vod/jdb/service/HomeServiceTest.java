package com.vod.jdb.service;

import com.vod.jdb.document.SynopsisDoc;
import com.vod.jdb.document.TrendingMovieDoc;
import com.vod.jdb.document.TrendingTVDoc;
import com.vod.jdb.repository.SynopsisRepository;
import com.vod.jdb.repository.TrendingMovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
class HomeServiceTest {

    @Autowired
    HomeService homeService;
    @Autowired
    TrendingMovieRepository trendingMovieRepository;
    @Autowired
    SynopsisRepository synopsisRepository;
    @Test
    void save3() {
        TrendingMovieDoc trendingMovieDoc = TrendingMovieDoc.builder().content_id("MV725201").title("그레이 맨").release_date("2022-07-13").poster_path("https://image.tmdb.org/t/p/w342/3weHvdbJ9RiSNHS0cz5694fkAnk.jpg").date("2022-07-26").build();
        TrendingMovieDoc trendingMovieDoc2 = TrendingMovieDoc.builder().content_id("MV507086").title("쥬라기 월드: 도미니언").release_date("2022-06-01").poster_path("https://image.tmdb.org/t/p/w342/odxdUZWZ7fBfy3ZRj063wuJnZvo.jpg").date("2022-07-26").build();
        TrendingMovieDoc trendingMovieDoc3 = TrendingMovieDoc.builder().content_id("MV616037").title("토르: 러브 앤 썬더").release_date("2022-07-06").poster_path("https://image.tmdb.org/t/p/w342/bZLrpWM065h5bu1msUcPmLFsHBe.jpg").date("2022-07-26").build();
        TrendingMovieDoc trendingMovieDoc4 = TrendingMovieDoc.builder().content_id("MV453395").title("닥터 스트레인지: 대혼돈의 멀티버스").release_date("2022-05-04").poster_path("https://image.tmdb.org/t/p/w342/vL5ktZauR0fZMDOHKjakb1idhuU.jpg").date("2022-07-26").build();
        TrendingMovieDoc trendingMovieDoc5 = TrendingMovieDoc.builder().content_id("MV366672").title("블레이드 퍼피 워리어").release_date("2022-07-14").poster_path("https://image.tmdb.org/t/p/w342/qXiIWXlDld14EBgcwlzzvi89y0g.jpg").date("2022-07-26").build();

        trendingMovieRepository.save(trendingMovieDoc);
        trendingMovieRepository.save(trendingMovieDoc2);
        trendingMovieRepository.save(trendingMovieDoc3);
        trendingMovieRepository.save(trendingMovieDoc4);
        trendingMovieRepository.save(trendingMovieDoc5);

    }
    @Test
    void save() {
        TrendingTVDoc trendingTVDto = TrendingTVDoc.builder().content_id("TV108296").title("바이오하자드: 더 시리즈").release_date("2022-07-14").poster_path("https://image.tmdb.org/t/p/w342/q2IlzP9bQNDdGLlX9jtms5MaOCh.jpg").date("2022-07-21").build();
        TrendingTVDoc trendingTVDto2 = TrendingTVDoc.builder().content_id("TV66732").title("기묘한 이야기").release_date("2016-07-15").poster_path("https://image.tmdb.org/t/p/w342/mpOQpOKdo2XJnTqRzo1lTmDNsc1.jpg").date("2022-07-21").build();
        TrendingTVDoc trendingTVDto3 = TrendingTVDoc.builder().content_id("TV88324").title("버진리버").release_date("2019-12-06").poster_path("https://image.tmdb.org/t/p/w342/w4WqEKKQa35nXps41QbQ3586nZ0.jpg").date("2022-07-21").build();
        TrendingTVDoc trendingTVDto4 = TrendingTVDoc.builder().content_id("TV60059").title("베터 콜 사울").release_date("2015-02-08").poster_path("https://image.tmdb.org/t/p/w342/fC2HDm5t0kHl7mTm7jxMR31b7by.jpg").date("2022-07-21").build();
        TrendingTVDoc trendingTVDto5 = TrendingTVDoc.builder().content_id("TV92782").title("미즈 마블").release_date("2022-06-08").poster_path("https://image.tmdb.org/t/p/w342/5K0F6lYosnp162drTXTJD9Pisp6.jpg").date("2022-07-21").build();

        //        TrendingTVDoc trendingTVDto2 = TrendingTVDoc.builder().content_id(616037).title("토르 러브 앤 썬더").release_date("2022-07-06").poster_path("bZLrpWM06h5bu1msUcPmLFsHBe.jpg").build();

        homeService.save(trendingTVDto);
        homeService.save(trendingTVDto2);
        homeService.save(trendingTVDto3);
        homeService.save(trendingTVDto4);
        homeService.save(trendingTVDto5);

    }
    @Test
    void save2() {
        SynopsisDoc synopsisDoc = SynopsisDoc.builder().contentId("TV92782").title("미즈 마블").release_date("2022-06-08").poster_path("https://image.tmdb.org/t/p/w500/5K0F6lYosnp162drTXTJD9Pisp6.jpg").genres(Arrays.asList(new String[]{"Sci-Fi & Fantasy", "Action & Adventure", "코미디"})).running_time(50).overview("저지시티에 사는 미국인 무슬림 가정의 10대 소녀 카말라 칸. 게임에 죽고 사는 그녀는 열렬한 팬픽션 작가이자 슈퍼 히어로 덕후로 특히 캡틴 마블에 대한 팬심은 상상을 초월한다. 그러던 어느 날, 그토록 우러러보던 슈퍼 히어로처럼 카말라에게도 슈퍼 파워가 생기는데. 지금껏 학교에서도, 때로는 집에서도 겉돌던 그녀의 인생, 이제 슈퍼 파워가 생겼으니 폼 나게 달라지려나?")
                .director("Bisha K. Ali").actors(Arrays.asList(new String[]{"Iman Vellani","Matt Lintz","Yasmeen Fletcher","Zenobia Shroff"})).certification("All").date("2022-07-21").build();
        //        TrendingTVDoc trendingTVDto2 = TrendingTVDoc.builder().content_id(616037).title("토르 러브 앤 썬더").release_date("2022-07-06").poster_path("bZLrpWM06h5bu1msUcPmLFsHBe.jpg").build();

        synopsisRepository.save(synopsisDoc);

    }
    @Test
    void findAll() {
        List<TrendingTVDoc> all = homeService.findAll();

        all.forEach(tv -> {
            System.out.println(tv.getContent_id() + " " + tv.getTitle() +" " + tv.getRelease_date() + " " + tv.getPoster_path());
        });
    }

    @Test
    void getTrendingTV() {
        Map<String, Object> trendingTV = homeService.getTrendingTV();
        List<TrendingTVDoc> result_data = (List<TrendingTVDoc>) trendingTV.get("result_data");

        result_data.forEach(tv -> {
            System.out.println(tv.getContent_id() + " " + tv.getTitle() +" " + tv.getRelease_date() + " " + tv.getPoster_path());
        });

    }

    @Test
    void getTrendingMovie() {

        Map<String, Object> tv92782 = homeService.getSynopsis("TV92782");

        for (Map.Entry<String, Object> e : tv92782.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue().toString());
        }
    }
}