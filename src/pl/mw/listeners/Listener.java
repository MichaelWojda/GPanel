package pl.mw.listeners;

import pl.mw.model.Visit;
import pl.mw.utils.VisitService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class Listener implements
        HttpSessionListener {


    public void sessionCreated(HttpSessionEvent se) {
        VisitService visitService = new VisitService();
        visitService.clearVisits();

    }

    public void sessionDestroyed(HttpSessionEvent se) {

    }

}
