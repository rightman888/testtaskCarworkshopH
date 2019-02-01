package com.haulmont.testtask;

import com.vaadin.ui.*;
import java.util.List;
import java.util.Date;

public class WindowEditOrder extends Window {
    private static final long serialVersionUID = 1L;
    
    FormLayout layout = new FormLayout();
    TextField descriptionField = new TextField("Описание");
    ComboBox clientBox = new ComboBox("Клиент");
    ComboBox mechanicBox = new ComboBox("Механик");
    DateField startDate = new DateField("Дата начала работ");
    DateField finishDate = new DateField("Дата начала работ");
    TextField costField = new TextField("Стоимость");
    ComboBox statusBox = new ComboBox("Статус");
    Button okButton = new Button("OK");
    Button calcelButton = new Button("Отменить");
    CarWorkshopDB database = new CarWorkshopDB();    
          
    public WindowEditOrder(long orderID) {
        List<Client> clientList = database.getClientList();
        List<Mechanic> mechanicList = database.getMechanicList();
        
        startDate.setDateFormat("yyyy-MM-dd");
        finishDate.setDateFormat("yyyy-MM-dd");
        
        for (Client client : clientList) {
            clientBox.addItem(client.getFullName());
        }
        
        for (Mechanic mechanic : mechanicList) {
            mechanicBox.addItem(mechanic.getFullName());
        }
        
        for (OrderStatusEnum tempEnum : OrderStatusEnum.values()) {
            statusBox.addItems(tempEnum);
        }        
        
        if (orderID != 0) {
            Order inputOrder = database.getOrder(orderID);
            descriptionField.setValue(inputOrder.getDescription());            
            String inputClientFullname;
            String inputMechanicFullname;
            for (Client client : clientList) {
                if (client.getID() == inputOrder.getClient()) {                    
                    clientBox.setValue(client.getFullName());
                };
            }        
            for (Mechanic mechanic : mechanicList) {
                if (mechanic.getID() == inputOrder.getMechanic()) {                    
                    mechanicBox.setValue(mechanic.getFullName());
                }
            }     
            startDate.setValue(inputOrder.getStartDate());
            finishDate.setValue(inputOrder.getFinishDate());
            costField.setValue(inputOrder.getCost());
            statusBox.setValue(inputOrder.getStatus());                        
        }       
        
        descriptionField.setImmediate(true);
        clientBox.setImmediate(true);
        mechanicBox.setImmediate(true);
        startDate.setImmediate(true);
        finishDate.setImmediate(true);
        costField.setImmediate(true);
        statusBox.setImmediate(true);        
        
        okButton.addClickListener((Button.ClickListener) clickEvent -> {             
            OrderStatusEnum outputStatus = OrderStatusEnum
                    .parseString(statusBox.getValue().toString());
            long outputClient = 0;
            long outputMechanic = 0;
            
            for (Client client : clientList) {
                if (client.getFullName().equals(clientBox
                        .getValue().toString())) {
                    outputClient = client.getID();
                }                
            }
            
            for (Mechanic mechanic : mechanicList) {
                if (mechanic.getFullName().equals(mechanicBox
                        .getValue().toString())) {
                    outputMechanic = mechanic.getID();
                }                
            }    
            
            Order outputOrder = new Order(orderID, descriptionField.getValue(), 
                    outputClient, outputMechanic, 
                    startDate.getValue(), finishDate.getValue(), 
                    costField.getValue(), outputStatus);
            if (orderID != 0) {
                database.editOrder(outputOrder);
            } else {
                database.addOrder(outputOrder);
            } 
            close();
        });
        
        calcelButton.addClickListener((Button.ClickListener) clickEvent -> { 
            close();
        });
        
        layout.addComponents(descriptionField, clientBox, mechanicBox, 
                startDate, finishDate, costField, statusBox, 
                okButton, calcelButton);
        layout.setSpacing(true);
        setWidth("50%");
        
        setModal(true);
        setContent(layout);       
    }    
}
