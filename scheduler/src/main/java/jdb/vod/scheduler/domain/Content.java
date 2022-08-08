package jdb.vod.scheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Content {

    String type;
    String content_id;
    String title;
    String poster_path;
    String release_date;
    String date;

    public void createContent() {

    }

}
