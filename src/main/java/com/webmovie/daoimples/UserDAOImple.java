package com.webmovie.daoimples;


import java.util.List;

import com.webmovie.daos.UserDAO;
import com.webmovie.entities.User;
import com.webmovie.utils.XJpa;
import jakarta.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

public class UserDAOImple implements UserDAO {

    private EntityManager em = XJpa.getEntityManager();

    @Override
    public List<User> findAll() {
        String jpql = "SELECT o FROM User o";
        return XJpa.getResultList(User.class, jpql, new HashMap<>());
    }

    @Override
    public User findById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email = ?1";
        Object[] values = {email};
        return XJpa.getSingleResult(User.class, jpql, values);
    }

    @Override
    public User create(User entity) {
        entity.setEmail(entity.getEmail().trim().toLowerCase());
        return XJpa.excuteUpdate(entity, 1);
    }

    @Override
    public User update(User entity) {
        entity.setEmail(entity.getEmail().trim().toLowerCase());
        return XJpa.excuteUpdate(entity, 0);
    }

    @Override
    public User deleteById(Integer id) {
        return XJpa.excuteDUpdate(id, User.class);
    }

    @Override
    public List<User> findByEmail(int firstResult, int maxResults, String search, Boolean admin) {
        String jpql = "SELECT u FROM User u WHERE (u.email like ?1 or u.fullname like ?2) and (u.admin=?3)";
        Object[] values = {
                "%"+search+"%",
                "%"+search+"%",
                admin
        };
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(User.class, jpql, map, values);
    }

    @Override
    public List<User> findByEmail(int firstResult, int maxResults, String search) {
        String jpql = "SELECT u FROM User u WHERE u.fullname like ?1 or u.email like ?2";
        Object[] values = {"%"+search+"%","%"+search+"%"};
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(User.class, jpql, map, values);
    }

    @Override
    public List<User> findAll(int firstResult, int maxResults) {
        String jpql = "SELECT u FROM User u";
        Map<String, Integer> map = new HashMap<>();
        map.put("firstResult", firstResult);
        map.put("maxResults", maxResults);
        return XJpa.getResultList(User.class, jpql, map);
    }

    @Override
    public List<User> findByName(String name) {
        String jpql = "SELECT o FROM User o where fullname like ?1";
        Object[] values = {"%" + name + "%"};
        return XJpa.getResultList(User.class, jpql, new HashMap<>(), values);
    }

    @Override
    public User findValidEmail(User entity) {
        String jpql = "SELECT o FROM User o WHERE o.email like ?1 and o.id <>?2";
        Object[] values = {
                entity.getEmail().trim().toLowerCase(),
                entity.getId()
        };
        return XJpa.getSingleResult(User.class, jpql, values);
    }

    @Override
    public Long selectCountByName(String search,String admin){
        String jpql = "SELECT count(u.id) FROM User u WHERE (u.fullname like ?1 or u.email like ?2) ";
        Object[] values= null ;
        if(!admin.equals("")){
            jpql += "and (u.admin=?3)";
            values = new Object[]{"%"+search+"%", "%"+search+"%", Boolean.parseBoolean(admin)};
        }else{
            values = new Object[]{"%"+search+"%","%"+search+"%"};
        }
        return XJpa.getSingleResult(Long.class, jpql,values);
    }

    @Override
    public User findByIdOrEmail(String info) {
        String jpql = "SELECT u FROM User u WHERE  u.username = ?1 OR u.email = ?2";
        Object[] value ={
                info,
                info
        };
        return XJpa.getSingleResult(User.class, jpql, value);
    }

    @Override
    public Boolean checkValidUsername(String username) {
        String jpql = "SELECT u FROM User u Where u.username = ?1 ";
        Object[] value = {username};
        User user = XJpa.getSingleResult(User.class, jpql, value);
        if (user != null)
            return true;
        return false;
    }


    /*Hoàn thành test  Tất cả các hàm trên*/
    public static void main(String[] args) {
//        EntityManager em = XJpa.getEntityManager();
        UserDAO dao = new UserDAOImple();
        /*Xem tất cả các video*/
//		String jpql = "SELECT v FROM Video v";
        /*Lấy tất cả các video có views giảm dần*/
//		String jpql = "SELECT v FROM Video v ORDER BY views DESC";
        /*Lấy tất cả video có id = ?*/
//		String jpql = "SELECT v FROM Video v WHERE Id = 20";
        /*Xem các video tieu de co chua tu */
//		String jpql = "SELECT v FROM Video v WHERE v.title like '%Adam%'";
        /* Xem danh sách các video được yêu thích */
//		String jpql = "SELECT f.video.title FROM Favourite f ";
        /*Xem danh sách các video được share */

//		String jpql = "SELECT s.video.title FROM Share s";
//		Share share = new Share(usr,vid,"tamnv@fpt.edu.vn",new Date());
//		ShareDAO dao = new ShareDAOImple();
//		dao.create(share);
//		TypedQuery<String> query = emm.createQuery(jpql, .class);
//        String jpql = "SELECT u FROM User u ";
//        TypedQuery<User> query = em.createQuery(jpql,User.class);
//        query.setFirstResult(0);
//        query.setMaxResults(10);
//        System.out.println(dao.findByEmail(0 , 10, ""));
        User user = new User();
        user.setEmail("sondoquang3@gmail.com");
        System.out.println(new UserDAOImple().findValidEmail(user));
    }
}
