package kazakov.webbeans;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import kazakov.entities.Books;
import kazakov.sessionbeans.sessionForBooks;

@ManagedBean
@SessionScoped
//@Stateless
//@LocalBean
public class forPrivateContent implements Serializable {

    private String aboutUserBooks = "Вы еще не добавляли свои книги. Попробуйте найти что-нибудь в своём Dropbox'е.";
    private List<Books> booksByUserLogin;
    private int currentBook = 0;
    private String login;
    private String userName;    
    private String userSirname;

    public String getUserName() {
        
        return sessBooks.getUserNameByLogin(this.getLogin());
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSirname() {
        return sessBooks.getUserSirnameByLogin(this.getLogin());
    }

    public void setUserSirname(String userSirname) {
        this.userSirname = userSirname;
    }
    

    public String getLogin() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(int currentBook) {
        this.currentBook = currentBook;
    }
    
    
    @Inject
    @EJB
    sessionForBooks sessBooks;
    
    registration reg;

    public List<Books> getBooksByUserLogin() {
        String userLogin = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
//        String userLogin = reg.getLoginFromFacesContext();
        return sessBooks.selectAllByUserLogin(userLogin);
    }

    public void setBooksByUserLogin(List<Books> booksByUserLogin) {
        this.booksByUserLogin = booksByUserLogin;
    }

    
    public String getAboutUserBooks() {
        if (! getBooksByUserLogin().isEmpty()) {
            aboutUserBooks = "Ранее Вы читали";
        }
        return aboutUserBooks;
    }

    public void setAboutUserBooks(String aboutUserBooks) {
        this.aboutUserBooks = aboutUserBooks;
    }
    
    
    
    public int newBookToCurrentUser (String login, String title, String link) {
//        System.out.print("до гетнейм");
//        Principal login = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
//        System.out.print("до гетнейм");
//        String s = login.getName();
//        System.out.print("после гентнейма");
        
        return sessBooks.newBookToDBForCurrentUser(login, title, link);
    }

    public void changeFIO () {
        sessBooks.changeFIOByLogin(this.getLogin(), userName, userSirname);
    }
}
