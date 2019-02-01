package com.haulmont.testtask;

import com.vaadin.ui.*;

public class WindowEditMechanic extends Window {
    private static final long serialVersionUID = 1L;

    FormLayout layout = new FormLayout();
    TextField firstNameField = new TextField("Имя");
    TextField lastNameField = new TextField("Фамилия");
    TextField patronymicField = new TextField("Отчество");
    TextField hourlyRateField = new TextField("Почасовая оплата");
    Button okButton = new Button("OK");
    Button calcelButton = new Button("Отменить");
    CarWorkshopDB database = new CarWorkshopDB();
    
    public WindowEditMechanic(long mechanicID) {
        if (mechanicID != 0) {
            Mechanic inputMechanic = database.getMechanic(mechanicID);
            firstNameField.setValue(inputMechanic.getFirstName());
            lastNameField.setValue(inputMechanic.getLastName());
            patronymicField.setValue(inputMechanic.getPatronymic());
            hourlyRateField.setValue(inputMechanic.getHourlyRate());
        }
        firstNameField.setImmediate(true);
        lastNameField.setImmediate(true);
        patronymicField.setImmediate(true);
        hourlyRateField.setImmediate(true);
        
        okButton.addClickListener((Button.ClickListener) clickEvent -> { 
            Mechanic outputMechanic = new Mechanic(mechanicID, 
                    firstNameField.getValue(), lastNameField.getValue(), 
                    patronymicField.getValue(), hourlyRateField.getValue());
            if (mechanicID != 0) {
                database.editMechanic(outputMechanic);
            } else {
                database.addMechanic(outputMechanic);
            }
            close();
        });
        
        calcelButton.addClickListener((Button.ClickListener) clickEvent -> { 
            close();
        });
        
        layout.addComponents(firstNameField, lastNameField, patronymicField, 
                hourlyRateField, okButton, calcelButton);
        layout.setSpacing(true);
        setWidth("50%");
        
        setModal(true);
        setContent(layout); 
    }    
}
