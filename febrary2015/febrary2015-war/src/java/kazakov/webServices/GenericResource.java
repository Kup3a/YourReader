/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kazakov.webServices;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import kazakov.webbeans.forWS;

/**
 * REST Web Service
 *
 * @author aka_Kup3a
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    @EJB
    forWS ws;
    
    

    /**
     * метод, который вызывается при нажатии кнопки DropBox
     * @param request
     * @param title
     * @param link
     * @return 
     */
    @GET
    @Produces("text/plain")
    @Path("addbook")
    public String addBook(@Context HttpServletRequest request, @QueryParam("title") String title, @QueryParam("link") String link) {
        String login = request.getUserPrincipal().getName();
        link = "../web/viewer.html?file=".concat(link);
        ws.newBookToCurrentUser(login, title, link);
        return "OK";
    }

    @GET
    @Produces("text/plain")
    @Path("add")
    public String add(@Context HttpServletRequest request, @QueryParam("title") String title, @QueryParam("link") String link) {
        //String login = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        String s = request.getUserPrincipal().getName();
        return title + " " + link + " login = " + s;
    }
    
    @GET
    @Produces("text/plain")
    @Path("lastpage")
    public String lastPage(@QueryParam("page") int page, @QueryParam("bookId") int bookId) {
        //TODO return proper representation object
        ws.changeLastPage(page, bookId);
        return "<h1>dfgs</h1>";
    }
    
    

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of
     * kazakov.webServices.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
}
