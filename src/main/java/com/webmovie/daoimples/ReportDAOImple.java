package com.webmovie.daoimples;

import com.webmovie.daos.ReportDAO;
import com.webmovie.utils.XJpa;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportDAOImple implements ReportDAO {

    /*Select tất cả các video được yêu thích: titleVideo, countFavourite,Ngày thích gần nhất
     * Ngày thích xa nhất*/
    @Override
    public List<Object[]> selectVideoFavoritesByMaxMinDate(int firstResult, int maxResults){
        String jpql = "SELECT f.video.title, COUNT(f.id),MAX(f.likeDate), MIN(f.likeDate) FROM Favourite f GROUP BY f.video.title ORDER BY COUNT(f.id) DESC";
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(Object[].class,jpql,map);
    }

    @Override
    public Long selectCountVideoFavoritesByMaxMinDate() {
        String jpql = "SELECT f.video.title, COUNT(f.id),MAX(f.likeDate), MIN(f.likeDate) FROM Favourite f GROUP BY f.video.title ORDER BY COUNT(f.id) DESC";
        return Long.parseLong(XJpa.getResultList(Object[].class,jpql,new HashMap<>()).size()+"");
    }

    @Override
    public List<Object[]> selectVideoFavoritesByMaxMinDateAndTitle(int firstResult, int maxResults, String title) {
        String jpql = "SELECT f.video.title, COUNT(f.id),MAX(f.likeDate), MIN(f.likeDate) FROM Favourite f WHERE f.video.title like ?1 GROUP BY f.video.title ORDER BY COUNT(f.id) DESC";
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        Object[] value = {"%"+title+"%"};
        return XJpa.getResultList(Object[].class,jpql,map,value);
    }

    /*Select tất cả video được yêu thích*/
    @Override
    public List<Object[]> selectVideoFavorites(int firstResult, int maxResults,String fullname){
        String jpql = "SELECT DISTINCT f.video.title,f.user.fullname,f.likeDate FROM Favourite f WHERE f.user.fullname like ?1 ";
        Object[] value = {"%"+fullname+"%"};
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(Object[].class,jpql,map,value);
    }

    @Override
    public Long selectCountVideoFavorites(String fullname) {
        String jpql = "SELECT COUNT( f.video.title) FROM Favourite f WHERE f.user.fullname like ?1 ";
        Object[] value = {"%"+fullname+"%"};
        return XJpa.getSingleResult(Long.class,jpql,value);
    }

    /*Select tất cả video được yêu thích nhóm theo số lượng*/
    @Override
    public List<Object[]> selectVideoFavoritesGroupBy(int firstResult, int maxResults){
        String jpql = "SELECT DISTINCT f.video.title, f.user.fullname FROM Favourite f group by f.video.title,f.user.fullname ";
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(Object[].class,jpql,map);
    }


    // Đang sai //
    @Override
    public Long selectCountVideoFavoritesGroupBy() {
        String jpql = "SELECT COUNT(f.video.title) FROM Favourite f group by f.video.title";
        return Long.parseLong(XJpa.getResultList(Object[].class,jpql,new HashMap<>()).size()+"");
    }

    /*Select tất cả các user thích video tham số*/
    @Override
    public List<Object[]> selectUserLikeVideoByFullname(int firstResult, int maxResults,String fullname){
        String jpql = "SELECT f.user.id, f.user.fullname, f.user.email, f.likeDate FROM Favourite f WHERE f.user.fullname LIKE ?1";
        Object [] value = {"%"+fullname+"%"};
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(Object[].class,jpql,map,value);
    }

    @Override
    public List<Object[]> selectUserLikeVideoByEmail(int firstResult, int maxResults, String email) {
        String jpql = "SELECT f.user.id, f.user.fullname, f.user.email, f.likeDate FROM Favourite f WHERE f.user.email LIKE ?1";
        Object [] value = {"%"+email+"%"};
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(Object[].class,jpql,map,value);
    }

    @Override
    public Long selectCountUserLikeVideoByTitle(String title) {
        String jpql = "SELECT COUNT(f.user.id) FROM Favourite f WHERE f.video.title LIKE ?1";
        Object [] value = {"%"+title+"%"};
        return XJpa.getSingleResult(Long.class,jpql,value);
    }

    /*Select tất cả các lượt share bao gồm thông tin: Fullname người share, Email người share
     * Share đến email nào và ngày share lọc theo tên video */
    @Override
    public List<Object[]> selectUserShareVideoByTitle(int firstResult, int maxResults,String title){
        String jpql = "SELECT s.user.fullname, s.user.email,s.email, s.shareDate FROM Share s WHERE s.video.title LIKE ?1";
        Object [] value = {"%"+title+"%"};
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(Object[].class,jpql,map,value);
    }

    @Override
    public Long selectCountUserShareVideoByTitle(String title) {
        String jpql = "SELECT COUNT(s.user.id) FROM Share s WHERE s.video.title LIKE ?1";
        Object [] value = {"%"+title+"%"};
        return XJpa.getSingleResult(Long.class,jpql,value);
    }

    @Override
    public List<Object[]> selectUserShareVideoByVideoID(int firstResult, int maxResults, int id) {
        String jpql = "SELECT s.user.fullname, s.user.email,s.email, s.shareDate FROM Share s WHERE s.video.id = ?1";
        Object [] value = {id};
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(Object[].class,jpql,map,value);
    }

    @Override
    public Long selectCountUserShareVideoByVideoId(int id) {
        String jpql = "SELECT COUNT(s.user.id) FROM Share s WHERE s.video.id = ?1";
        Object [] value = {id};
        return XJpa.getSingleResult(Long.class,jpql,value);
    }

    public static void main(String[] args) {
        ReportDAOImple dao = new ReportDAOImple();
        Long values = dao.selectCountUserShareVideoByVideoId(2);
        System.out.println(values);
    }
}
