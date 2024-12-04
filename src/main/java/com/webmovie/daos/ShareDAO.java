package com.webmovie.daos;


import com.webmovie.entities.Share;
import com.webmovie.entities.Video;

import java.util.List;

public interface ShareDAO extends DAOs<Share, Integer> {
    List<Video> getVideosShareByYear(int year);
}
