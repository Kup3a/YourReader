/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kazakov.sessionbeans;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import kazakov.entities.Books;
import kazakov.entities.Users;

/**
 *
 * @author aka_Kup3a
 */
@Stateless
@Named(value = "sessionForBooks")
public class sessionForBooks {
    @PersistenceContext(unitName = "febrary2015-ejbPU")
    EntityManager entityManager;
    
    public String getUserNameByLogin (String login){
        Query q = entityManager.createNamedQuery("Users.findByLogin");
        q.setParameter("login", login);
        List<Users> users = q.getResultList();
        return users.get(0).getName();
    }
    
    public String getUserSirnameByLogin (String login){
        Query q = entityManager.createNamedQuery("Users.findByLogin");
        q.setParameter("login", login);
        List<Users> users = q.getResultList();
        return users.get(0).getSirname();
    }
    
    public void newRowToBooksTable(String refer, String title) {
        Books book = new Books();
        book.setReference(refer);
        book.setTitle(title);
        entityManager.persist(book);        
    }
    
    public List<Books> selectAll () {
        Query query = entityManager.createNamedQuery("Books.findAll");
        return query.getResultList();
    }
    
    public List<Books> selectAllByUserId (int userId){
        Query q = entityManager.createNamedQuery("Books.findByUserId");
        q.setParameter("userId", userId);
        return q.getResultList();
    }
    
    public List<Books> selectAllByUserLogin (String login){
        Query q = entityManager.createNamedQuery("Books.findByUserLogin");
        q.setParameter("userLogin", login);
        return q.getResultList();
    }
    /**
     * Метод для регистрации нового пользователя.
     * @param login 
     * @param password
     * @return 0 - всё прошло успешно
     *         1 - такой логин уже существует
     */
    public int newUserRegistration (String login, String password) {
        Query q = entityManager.createNamedQuery("Users.findByLogin");
        q.setParameter("login", login);
        if (q.getResultList().isEmpty()) {
            Users u = new Users();
            u.setLogin(login);
            u.setPassword(password);
            u.setSecurityGroupId("private");
            entityManager.persist(u);
            return 0;
        } else {
            return 1;
        }        
    }
    /**
     * 
     * @param login
     * @param title
     * @param ref
     * @return 0 - всё прошло успешно
     */
    public int newBookToDBForCurrentUser(String login, String title, String ref){
        Books b = new Books();
        Query q = entityManager.createNamedQuery("Users.findByLogin");
        q.setParameter("login", login);
        List<Users> uList = q.getResultList();
        b.setLastPage(1);
        b.setUserId(uList.get(0).getUserId());
        b.setTitle(title);
        b.setReference(ref);
        entityManager.persist(b);
        return 0;
    }
    
    public void changeLastPage(int lastPage, int bookId) {
        Query q = entityManager.createNamedQuery("Books.findByBookId");
        q.setParameter("bookId", bookId);
        List<Books> list = q.getResultList();
        Books b = list.get(0);
        b.setLastPage(lastPage);
    }
    
    public void changeFIOByLogin (String login, String name, String sirName) {
        Query q = entityManager.createNamedQuery("Users.findByLogin");
        q.setParameter("login", login);
        List<Users> u = q.getResultList();
        Users user = u.get(0);
        user.setName(name);
        user.setSirname(sirName);
        entityManager.persist(user);
    }
}
