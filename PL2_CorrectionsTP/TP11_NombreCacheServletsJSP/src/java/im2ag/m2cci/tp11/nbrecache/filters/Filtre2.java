package im2ag.m2cci.tp11.nbrecache.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Teste si il existe un paramètre proposition si on appelle l'url 'jouer'
 * si ce n'est le cas redirige vers la page pageJeu.jsp.
 * @author Philippe Genoud - LIG Steamer
 */

// on aurait pu utiliser une annotation pour configurer le filtre
// @WebFilter(filterName = "filtre2", urlPatterns = {"/jouer"}, dispatcherTypes = {DispatcherType.REQUEST})
// mais on a préféré faire leconfiguration des filtres dans le fichier de déploiement
// web.xml ce qui permet de contrôler leur ordre d'application.

public class Filtre2 implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public Filtre2() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        log("Filtre2:doFilter()");

        if (request.getParameter("proposition") != null) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/pageJeu.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     * @return 
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("Filtre2:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("Filtre2()");
        }
        StringBuilder sb = new StringBuilder("Filtre2(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
