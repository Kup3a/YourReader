/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kazakov.webbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import kazakov.sessionbeans.sessionForBooks;

@Stateless
@LocalBean
public class forWS {
    @Inject
    @EJB
    sessionForBooks sessBooks;
    
    
    public int newBookToCurrentUser (String login, String title, String link) {
        
        return sessBooks.newBookToDBForCurrentUser(login, title, link);
    }
    
     public void changeLastPage(int lastPage, int bookId) {
        sessBooks.changeLastPage(lastPage, bookId);
    }
}
