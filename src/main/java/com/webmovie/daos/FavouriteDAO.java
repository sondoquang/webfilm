package com.webmovie.daos;




import com.webmovie.entities.Favourite;
import com.webmovie.entities.Video;

import java.util.List;

public interface FavouriteDAO extends DAOs<Favourite, Integer> {
    List<Object[]> getVideoFavourites();
    List<Video> getTop10VideosFavourite(int limit);
    List<Video> getVideosNotFavourite();
    List<Video> getVideoFavouritesByUser(int idUser);
    Long updateLikeVideoFavourite(Favourite favourite);
    Favourite selectVideoByUserIdAndVideoId(Integer videoId, Integer userId);
}
