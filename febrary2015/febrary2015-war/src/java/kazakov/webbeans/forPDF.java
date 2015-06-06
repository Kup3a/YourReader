/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kazakov.webbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import kazakov.entities.Books;
import kazakov.sessionbeans.sessionForBooks;

@ManagedBean
@SessionScoped
public class forPDF implements Serializable {
    private List<String> ref = new ArrayList<>();
    private List<Books> booksByUserId;
    private List<Books> booksByUserLogin;
    private String strRef;

    public List<Books> getBooksByUserLogin() {
        String userLogin = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return sessBooks.selectAllByUserLogin(userLogin);
    }

    public void setBooksByUserLogin(List<Books> booksByUserLogin) {
        this.booksByUserLogin = booksByUserLogin;
    }
    private String reference;

    public List<Books> getBooksByUserId(int userId) {
        return sessBooks.selectAllByUserId(userId);
    }

    public void setBooksByUserId(List<Books> booksByUserId) {
        this.booksByUserId = booksByUserId;
    }
    
    public List<String> getRef() {
        return ref;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStrRef() {
        return strRef;
    }

    public void setStrRef(String strRef) {
        this.strRef = strRef;
    }

    public void setRef(List<String> ref) {
        this.ref = ref;
    }
    
    public void add() {
        reference = strRef;
        ref.add("docs/Getting.pdf");
    }
    
    //работа с session-бинами
    @Inject
    @EJB
    sessionForBooks sessBooks;
    
    public void newBook () {
        sessBooks.newRowToBooksTable("ref10", "title");
        reference = "hello from newBook";
    }
    
    private List<Books> books;

    public List<Books> getBooks() {
        return sessBooks.selectAll();
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
    
    public String goToReader () {
        /*тут кладём в базу ссылку*/
        /*return "web/viewer.html?file=".concat(strRef);*/
        return "newxhtml";
    }
    
    public void addBook () {
        String ref = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("inn1");
        String tit = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("inn2");
        sessBooks.newRowToBooksTable(ref, tit);
    }
}
