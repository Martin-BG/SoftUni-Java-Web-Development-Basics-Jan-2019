package panda.config;

import org.ocpsoft.logging.Logger;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.*;
import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.HttpOperation;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.rule.Join;
import org.ocpsoft.rewrite.servlet.http.event.HttpInboundServletRewrite;
import org.ocpsoft.rewrite.servlet.http.event.HttpServletRewrite;
import org.ocpsoft.rewrite.servlet.impl.HttpInboundRewriteImpl;

import javax.servlet.ServletContext;

@RewriteConfiguration
public class RewriteServletConfig extends HttpConfigurationProvider {

    private static final String FACES_VIEW = "/faces/view";
    private static final String FACES_VIEW_GUEST = FACES_VIEW + "/guest";
    private static final String FACES_VIEW_USER = FACES_VIEW + "/user";

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public Configuration getConfiguration(final ServletContext context) {


        Condition authenticated = (rewrite, evaluationContext) -> {
            HttpInboundServletRewrite httpServletRewrite = (HttpInboundRewriteImpl) rewrite;
            Object username = httpServletRewrite.getRequest().getSession().getAttribute("username");
            return username != null;
        };

        Condition guest = (rewrite, evaluationContext) -> !authenticated.evaluate(rewrite, evaluationContext);

        // @formatter:off
        return ConfigurationBuilder.begin()
            .addRule()
                .when(Direction.isInbound().and(Path.matches("/{path}")))
                .perform(Log.message(Logger.Level.INFO, "Client requested path: {path}"))
                .where("path").matches(".*")

            .addRule()
                .when(Direction.isInbound().and(Path.matches("/logout")))
                .perform(new HttpOperation() {
                    @Override
                    public void performHttp(HttpServletRewrite event, EvaluationContext context) {
                        event.getRequest().getSession().invalidate();
                    }
                }.and(Redirect.temporary(context.getContextPath() + "/")))

            .addRule()
                .when(Direction.isInbound().and(Path.matches("/index")))
                .perform(Redirect.temporary(context.getContextPath() + "/"))

            .addRule(Join.path("/").to(FACES_VIEW_GUEST + "/index.xhtml"))
                .when(Direction.isInbound().and(Path.matches("/")).and(guest))

            .addRule(Join.path("/").to(FACES_VIEW_USER + "/home.xhtml"))
                .when(Direction.isInbound().and(Path.matches("/")).and(authenticated))

            .addRule(Join.path("/login").to(FACES_VIEW_GUEST + "/login.xhtml"))
            .addRule(Join.path("/register").to(FACES_VIEW_GUEST + "/register.xhtml"))

            ;
        // @formatter:on
    }
}
