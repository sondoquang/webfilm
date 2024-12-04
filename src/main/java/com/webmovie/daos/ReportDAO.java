package com.webmovie.daos;

import java.util.List;

public interface ReportDAO {
    List<Object[]> selectVideoFavoritesByMaxMinDate(int firstResult, int maxResults);
    Long selectCountVideoFavoritesByMaxMinDate();
    List <Object[]> selectVideoFavoritesByMaxMinDateAndTitle(int firstResult, int maxResults, String title);
    List<Object[]> selectVideoFavorites(int firstResult, int maxResults,String fullName);
    Long selectCountVideoFavorites(String fullName);
    List<Object[]> selectVideoFavoritesGroupBy(int firstResult, int maxResults);
    Long selectCountVideoFavoritesGroupBy();
    List<Object[]> selectUserLikeVideoByFullname(int firstResult, int maxResults,String fullname);
    List<Object[]> selectUserLikeVideoByEmail(int firstResult, int maxResults,String email);
    Long selectCountUserLikeVideoByTitle(String title);
    List<Object[]> selectUserShareVideoByTitle(int firstResult, int maxResults,String title);
    Long selectCountUserShareVideoByTitle(String title);
    List<Object[]> selectUserShareVideoByVideoID(int firstResult, int maxResults,int title);
    Long selectCountUserShareVideoByVideoId(int title);
}
