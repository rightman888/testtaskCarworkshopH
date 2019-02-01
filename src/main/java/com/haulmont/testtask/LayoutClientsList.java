package com.haulmont.testtask;

import com.vaadin.ui.*;
import java.util.List;

public class LayoutClientsList extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    private final Label templabel = new Label("Список клиентов");    
    private final Button addClient = new Button("Добавить");
    private final Button editClient = new Button("Изменить");
    private final Button deleteClient = new Button("Удалить");
    private final HorizontalLayout buttonsLayout = new HorizontalLayout();
    private final CarWorkshopDB carWorkshopDB = new CarWorkshopDB();
    private List<Client> clientList;
    private final Label resultLabel = new Label("");   
    
    public LayoutClientsList() { 
        buttonsLayout.addComponents(addClient, editClient, deleteClient);
        buttonsLayout.setSpacing(true);
        Table tableClients = new Table();
        tableClients.setSelectable(true);
        tableClients.setImmediate(true);
        tableClients.addContainerProperty("Фамилия", String.class, null);
        tableClients.addContainerProperty("Имя", String.class, null);
        tableClients.addContainerProperty("Отчество", String.class, null);
        tableClients.addContainerProperty("Телефон", String.class, null);        
        
        clientList = carWorkshopDB.getClientList();     
        for (Client client : clientList) {
            tableClients.addItem(new Object[]{client.getFirstName(), client.getLastName(), 
                    client.getPatronymic(), client.getPhone()}, client.getID());            
        }        
        
        addClient.addClickListener((Button.ClickListener) clickEvent -> { 
            UI.getCurrent().addWindow(new WindowEditClient(0));            
        });
        
        editClient.addClickListener((Button.ClickListener) clickEvent -> { 
            long currentClientID = (long)tableClients.getValue();
            UI.getCurrent().addWindow(new WindowEditClient(currentClientID));                                   
        });
        
        deleteClient.addClickListener((Button.ClickListener) clickEvent -> {
            long currentClientID = (long)tableClients.getValue();
            if (carWorkshopDB.deleteClient(currentClientID)) {
                tableClients.removeItem(tableClients.getValue());
                resultLabel.setValue("Удалено");
            } else {
                resultLabel.setValue("Не удалено");
            }              
        });               
        
        addComponents(templabel, buttonsLayout, resultLabel, tableClients);
    }    
}
