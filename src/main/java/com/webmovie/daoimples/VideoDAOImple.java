package com.webmovie.daoimples;



import com.webmovie.daos.VideoDAO;
import com.webmovie.entities.Video;
import com.webmovie.utils.XJpa;
import jakarta.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VideoDAOImple implements VideoDAO {
    EntityManager em = XJpa.getEntityManager();
    @Override
    public List<Video> findAll() {
        String jpql = "SELECT v FROM Video v";
        return XJpa.getResultList(Video.class, jpql,new HashMap<>());
    }

    /* lấy tổng số lượng video trong table video*/
    @Override
    public Long selectAllCountVideo() {
        String jpql = "SELECT COUNT(v.id) FROM Video v";
        return XJpa.getSingleResult(Long.class, jpql);
    }

    /* Tìm video theo id --> return: video nếu thành công != return -->null*/
    @Override
    public Video findById(Integer id) {
        return em.find(Video.class, id);
    }


    /* Tạo video mới: --> return video nếu thành công != return -->null */
    @Override
    public Video create(Video item) {
        return XJpa.excuteUpdate(item,1);
    }


    /*Cập nhật: Với video là đối số*/
    @Override
    public Video update(Video item) {
        return XJpa.excuteUpdate(item,0);
    }

    /* Xóa video có id là tham số truyền vào*/
    @Override
    public Video deleteById(Integer id) {
        return XJpa.excuteDUpdate(id,Video.class);
    }

    @Override
    public List<Video> findAll(int firstResult, int maxResults) {
        String jpql = "SELECT v FROM Video v";
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(Video.class,jpql, map);
    }

    @Override
    public List<Video> findAllByTitleOrStatus(int firstResult, int maxResults,String title,Boolean...status) {
        String jpql = "SELECT v FROM Video v Where v.title like ?1";
        if(status.length>0){
            jpql = "SELECT v FROM Video v Where v.title like ?1 and v.active = ?2";
        }
        Object[] value = null;
        if(status.length>0){
            value = new Object[]{"%" + title + "%", status[0]};
        }else{
            value = new Object[]{"%"+title+"%"};
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(Video.class,jpql, map,value);
    }

    @Override
    public Long findAllCountByTitleOrStatus( String title, Boolean... status) {
        String jpql = "SELECT COUNT(v.title) FROM Video v Where v.title like ?1";
        if(status.length>0){
            jpql = "SELECT COUNT(v.title) FROM Video v Where v.title like ?1 and v.active in ?2";
        }
        Object[] value = null;
        if(status.length>0){
            value = new Object[]{"%" + title + "%", status[0]};
        }else{
            value = new Object[]{"%"+title+"%"};
        }
        return XJpa.getSingleResult(Long.class,jpql,value);
    }

    /*=== Tìm tất cả các video có title là tham số ======*/
    @Override
    public List<Object[]> findVideosByTitle(String title) {
        String jqpl = "SELECT v.title,COUNT(f.id),v.active FROM Video v LEFT JOIN Favourite f ON f.video = v WHERE v.title like ?1 GROUP BY v.id, v.title, v.active ORDER BY COUNT(f.id) DESC";
        Object[] value ={"%"+title+"%"};
        return XJpa.getResultList(Object[].class, jqpl, new HashMap<>(), value);
    }


    /*Đã test hết tất cả các dao phía trên ngày 6/11/2024 (--> Không cần test lại các hàm này nữa <--)*/
    @Override
    public List<Video> getTopVideoActive(int firstResult, int maxResults, boolean active) {
        String jpql ="SELECT v FROM Video v WHERE v.active = ?1";
        Object[] value = {active};
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(Video.class, jpql, map,value);
    }

    @Override
    public Long selectSumCountVideoActive(Boolean active) {
        String jpql ="SELECT COUNT(v.id) FROM Video v WHERE v.active = ?1";
        Object[] value = {active};
        return XJpa.getSingleResult(Long.class, jpql,value);
    }

    /* Test hoàn tất các hàm trên */
    /*******************************/
    /* Thêm hàm phía dưới để test*/


    public static void main(String[] args) {
        VideoDAO dao = new VideoDAOImple();
        List<Video> list = dao.findAllByTitleOrStatus(0,16,"",false);
        System.out.println(list.size());
    }





}
