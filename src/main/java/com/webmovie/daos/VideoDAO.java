package com.webmovie.daos;


import com.webmovie.entities.Video;

import java.util.List;

public interface VideoDAO extends DAOs<Video, Integer>{
    List<Video> findAll(int  firstResult, int maxResults);
    Long selectAllCountVideo();
    List<Video> findAllByTitleOrStatus(int  firstResult, int maxResults,String title,Boolean...status);
    Long findAllCountByTitleOrStatus(String title,Boolean...status);
    List<Object[]> findVideosByTitle(String title);
    List<Video> getTopVideoActive(int firstResult, int maxResults,boolean active);
    Long selectSumCountVideoActive(Boolean active);
}
