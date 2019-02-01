package com.haulmont.testtask;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        Label titleLabel = new Label("Haulmont Test Task: Car Workshop");
        
        HorizontalLayout mainButtonsLayout = new HorizontalLayout();
        Button buttonOrdersList = new Button("Список заказов");
        Button buttonClientsList = new Button("Список клиентов");
        Button buttonMechanicsList = new Button("Список механиков");
        mainButtonsLayout.addComponents(buttonOrdersList, buttonClientsList, 
                buttonMechanicsList);
        mainButtonsLayout.setSpacing(true);
        
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.addComponents(titleLabel, mainButtonsLayout, 
                new LayoutOrdersList());
        setContent(mainLayout);
                
       
        buttonOrdersList.addClickListener((Button.ClickListener) clickEvent -> { 
            mainLayout.removeAllComponents();
            mainLayout.addComponents(titleLabel, mainButtonsLayout, 
                    new LayoutOrdersList());            
        });
        
        buttonClientsList.addClickListener((Button.ClickListener) clickEvent -> { 
            mainLayout.removeAllComponents();
            mainLayout.addComponents(titleLabel, mainButtonsLayout, 
                    new LayoutClientsList());            
        });
        
        buttonMechanicsList.addClickListener((Button.ClickListener) clickEvent -> { 
            mainLayout.removeAllComponents();
            mainLayout.addComponents(titleLabel, mainButtonsLayout, 
                    new LayoutMechanicsList());            
        });                     
    }
}