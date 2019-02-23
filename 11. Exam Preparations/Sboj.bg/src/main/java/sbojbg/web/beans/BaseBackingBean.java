package sbojbg.web.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseBackingBean {

    @Inject
    protected ExternalContext externalContext;

    @Inject
    protected FacesContext facesContext;

    @Inject
    private Logger logger;

    protected void redirect(String url) {
        try {
            externalContext.redirect(url);
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            logger.log(Level.SEVERE, "Failed redirect to: " + url, e);
        }
    }

    protected Runnable addMessageRunnable(String message) {
        return () -> facesContext.addMessage(null, new FacesMessage(message));
    }

    protected void addMessage(String message) {
        addMessageRunnable(message).run();
    }
}
