package com.webmovie.daoimples;

import com.webmovie.daos.ShareDAO;
import com.webmovie.entities.Share;
import com.webmovie.entities.Video;
import com.webmovie.utils.XJpa;
import jakarta.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

public class ShareDAOImple implements ShareDAO {
    EntityManager em = XJpa.getEntityManager();
    @Override
    public List<Share> findAll() {
        String jpql = "SELECT s FROM Share s";
        return XJpa.getResultList(Share.class, jpql,new HashMap<>());
    }

    @Override
    public Share findById(Integer id) {
        return em.find(Share.class, id);
    }

    @Override
    public Share create(Share item) {
        return XJpa.excuteUpdate(item, 1);
    }

    @Override
    public Share update(Share item) {
        return XJpa.excuteUpdate(item, 0);
    }

    @Override
    public Share deleteById(Integer id) {
        return XJpa.excuteDUpdate(id,Share.class);
    }

    // Tìm tất cả các video được share trong năm là tham số truyền vào và sắp xếp theo time  //
    @Override
    public List<Video> getVideosShareByYear(int year) {
        String jpql = "SELECT s.video FROM  Share s WHERE  YEAR(s.shareDate) = ?1 ORDER BY s.shareDate desc";
        Object[] value = {year};
        return XJpa.getResultList(Video.class, jpql,new HashMap<>(), value);
    }

    public static void main(String[] args) {
        List<Video> list = new ShareDAOImple().getVideosShareByYear(2024);
        list.forEach(v ->{
            System.out.println("title: "+v.getTitle());
        });
    }
}
