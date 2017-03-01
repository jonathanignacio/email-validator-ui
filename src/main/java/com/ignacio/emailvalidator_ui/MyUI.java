package com.ignacio.emailvalidator_ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.ignacio.validator.emailvalidator.*; //logic from part 1 and 2

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField email = new TextField();
        email.setCaption("Type an email address here:");

        Button button = new Button("Submit");
        button.addClickListener( e -> {
        	layout.removeAllComponents();
            layout.addComponents(email, button);
        	int rules = 0;
        	rules = Validate.validate(email.getValue());
        	if(rules == 4){
        		layout.addComponent(new Label(email.getValue() + " is a valid email address!"));
        	}
        	else
        		layout.addComponent(new Label("Invalid email address entered. Only followed  " + rules + " rules."));
        });
        
        layout.addComponents(email, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "EmailValidator", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
