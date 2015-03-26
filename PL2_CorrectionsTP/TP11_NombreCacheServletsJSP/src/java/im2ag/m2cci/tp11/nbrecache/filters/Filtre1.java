package im2ag.m2cci.tp11.nbrecache.filters;

import im2ag.m2cci.tp11.nbrecache.model.NombreInconnu;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Ce filtre de servlet teste si il existe un nombre dans la session lorsque
 * l'on appelle l'une des url de l'application si ce n'est le cas redirige vers
 * l'action débuter partie. Ce filtre, protège l'application d'une navigation
 * qui se ferait en tappant directement les urls dans le navigateur.
 *
 * @author Philippe Genoud - LIG STeamer
 */

// on aurait pu utiliser une annotation pour configurer le filtre
//@WebFilter(filterName = "filtre1", urlPatterns = {"/debuterPartie", "/abandonner", "/jouer"}
//       dispatcherTypes = {DispatcherType.REQUEST})
// mais on a préféré faire leconfiguration des filtres dans le fichier de déploiement
// web.xml ce qui permet de contrôler leur ordre d'application.
public class Filtre1 implements Filter {

    private static final boolean debug = true;
    
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public Filtre1() {
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

        log("Filtre1:doFilter()");
        HttpSession session = ((HttpServletRequest) request).getSession(true);
        NombreInconnu valeurCachee
                = (NombreInconnu) session.getAttribute("nombreInconnu");
        if (valeurCachee == null) {
            // il n'y a pas de nombre inconnu dans la session. Par exemple
            // parceque l'utilisateur a tappé directement l'url
            // http://...../NombreCache/jouer dans son navigateur). 
            // On redirige alors l'utilisateur vers la page servlet qui
            // initialise une partie.
            log("on redirige sur la page d'accueil");
            request.getRequestDispatcher("/debuterPartie")
                    .forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     *
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
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FiltreNombreCache:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltreNombreCache()");
        }
        StringBuilder sb = new StringBuilder("FiltreNombreCache(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
