package sbojbg.config;

import org.ocpsoft.logging.Logger;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.config.Log;
import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.HttpOperation;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.rule.Join;
import org.ocpsoft.rewrite.servlet.http.event.HttpServletRewrite;

import javax.servlet.ServletContext;

@RewriteConfiguration
public class RewriteServletConfig extends HttpConfigurationProvider {

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public Configuration getConfiguration(final ServletContext context) {
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

                .addRule(Join.path("/").to("/faces/view/index.xhtml"))
                .addRule(Join.path("/home").to("/faces/view/home.xhtml"))
                .addRule(Join.path("/login").to("/faces/view/login.xhtml"))
                .addRule(Join.path("/register").to("/faces/view/register.xhtml"))
                .addRule(Join.path("/jobs/add").to("/faces/view/job-add.xhtml"))
                .addRule(Join.path("/jobs/details").to("/faces/view/job-details.xhtml"))
                .addRule(Join.path("/jobs/delete").to("/faces/view/job-delete.xhtml"));
    }
}
