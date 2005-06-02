/*
 * test.java
 *
 * Created on 2 czerwiec 2005, 20:09
 */

package pl.ltd.bee.Filters;


import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

/**
 * Klasa będąca filtrem HTTP. Ma za zadanie dodawanie nagłówków HTTP informujących o cacheowalności danego elementu.
 * @author wilk
 */
public class GeneralFilter implements javax.servlet.Filter{

    private FilterConfig filterConfig;
    
    /** Konstruktor */
    public GeneralFilter() {
    }
    
    /** Metoda wywoływana podczas niszczenia łąńcucha filtrów */
    public void destroy(){
        this.filterConfig = null;
    }

    
    /** Metoda pozwala na uzyskanie informacji o konfiguracji filtra 
     *  @return Obiekt określający konfigurację
     */
    public FilterConfig getFilterConfig() { 
        return filterConfig; 
    }    

    /** Główna metoda filtra wykonująca zamierzone czynności
     * @param request Żądanie HTTP
     * @param response Odpowiedź HTTP serwera
     * @param chain Lista filtrów w kontenerze servletów
     */
    public void doFilter(final ServletRequest request, final ServletResponse response, FilterChain chain) throws IOException, ServletException{
        if (HttpServletRequest.class.isInstance(request))
        {
            if ( (((HttpServletRequest)request).getRequestURL().indexOf("/styles/",0) > 0) || 
                 (((HttpServletRequest)request).getRequestURL().indexOf("/js/",0) > 0) ||
                 (((HttpServletRequest)request).getRequestURL().indexOf("/images/",0) > 0))
            if (HttpServletResponse.class.isInstance(response))
            {
                pl.ltd.bee.Commons.setCachingForever((HttpServletResponse)response);
            } 
        }
        chain.doFilter(request,response);
    }
    
    /** Metoda wywoływana podczas wstawiania do łańcucha filtrów kontenera
     * @param filterConfig Konfiguracja filtra
     */
    public void init(FilterConfig filterConfig){
        this.filterConfig = filterConfig;
    }
}
