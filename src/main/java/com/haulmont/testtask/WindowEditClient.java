package com.haulmont.testtask;

import com.vaadin.ui.*;

public class WindowEditClient extends Window {
    private static final long serialVersionUID = 1L;
    
    FormLayout layout = new FormLayout();
    TextField firstNameField = new TextField("Имя");
    TextField lastNameField = new TextField("Фамилия");
    TextField patronymicField = new TextField("Отчество");
    TextField phoneField = new TextField("Телефон");
    Button okButton = new Button("OK");
    Button calcelButton = new Button("Отменить");
    CarWorkshopDB database = new CarWorkshopDB();
    
    public WindowEditClient(long clientID) {
        if (clientID != 0) {
            Client inputClient = database.getClient(clientID);
            firstNameField.setValue(inputClient.getFirstName());
            lastNameField.setValue(inputClient.getLastName());
            patronymicField.setValue(inputClient.getPatronymic());
            phoneField.setValue(inputClient.getPhone());            
        }
        firstNameField.setImmediate(true);
        lastNameField.setImmediate(true);
        patronymicField.setImmediate(true);
        phoneField.setImmediate(true);
        
        okButton.addClickListener((Button.ClickListener) clickEvent -> { 
            Client outputClient = new Client(clientID, 
                    firstNameField.getValue(), lastNameField.getValue(), 
                    patronymicField.getValue(), phoneField.getValue());
            if (clientID != 0) {
                database.editClient(outputClient);
            } else {
                database.addClient(outputClient);
            }
            close();
        });
        
        calcelButton.addClickListener((Button.ClickListener) clickEvent -> { 
            close();
        });
        
        layout.addComponents(firstNameField, lastNameField, patronymicField, 
                phoneField, okButton, calcelButton);
        layout.setSpacing(true);
        setWidth("50%");
        
        setModal(true);
        setContent(layout);               
    }    
}
