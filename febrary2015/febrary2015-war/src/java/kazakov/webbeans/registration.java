/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kazakov.webbeans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import kazakov.sessionbeans.sessionForBooks;
import org.jboss.weld.context.http.HttpRequestContextImpl;

/**
 *
 * @author aka_Kup3a
 */
@ManagedBean
@SessionScoped
public class registration implements Serializable {
    private String login;
    private String password;
    private String helpMessageClass = "row hidden";
    private String helpMessageText = "Отлично! Вы зарегистрировались. Осталось залогиниться и готово!";
    private String loginFromFacesContext;
    
    @Inject
    @EJB
    sessionForBooks sessBooks;

    public String getLoginFromFacesContext() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }

    public void setLoginFromFacesContext(String loginFromFacesContext) {
        this.loginFromFacesContext = loginFromFacesContext;
    }
    
    public String getHelpMessageText() {
        return helpMessageText;
    }

    public void setHelpMessageText(String helpMessageText) {
        this.helpMessageText = helpMessageText;
    }

    public String getHelpMessageClass() {
        return helpMessageClass;
    }

    public void setHelpMessageClass(String helpMessageClass) {
        this.helpMessageClass = helpMessageClass;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String signIn () {
        if (sessBooks.newUserRegistration(login, password) == 0) {
            helpMessageClass = "row";
        } else {
            helpMessageText = "Введенный Вами логин уже существует. Придумайте другой.";
            helpMessageClass = "row";                    
        }
        return "private/profile.xhtml";
    }
}
