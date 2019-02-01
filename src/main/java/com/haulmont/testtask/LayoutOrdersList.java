package com.haulmont.testtask;

import com.vaadin.ui.*;
import com.vaadin.data.util.filter.*;
import com.vaadin.data.Container.*;
import java.util.List;


public class LayoutOrdersList extends VerticalLayout {
    private static final long serialVersionUID = 1L;
    
    private final Label templabel = new Label("Список заказов");    
    private final Button addOrder = new Button("Добавить");
    private final Button editOrder = new Button("Изменить");
    private final Button deleteOrder = new Button("Удалить");
    private final HorizontalLayout buttonsLayout = new HorizontalLayout();
    
    private final ComboBox filterClient = new ComboBox();
    private final ComboBox filterStatus = new ComboBox();
    private final TextField filterDescription = new TextField("Описание");
    private final Button applyFilter = new Button("Применить");
    
    private final HorizontalLayout filterLayout = new HorizontalLayout();
    private Label resultLabel = new Label("");
    private final CarWorkshopDB carWorkshopDB = new CarWorkshopDB();
    private List<Order> orderList;
    private List<Client> clientList;
    private List<Mechanic> mechanicList;
    
    
    public LayoutOrdersList() {
        buttonsLayout.addComponents(addOrder, editOrder, deleteOrder);
        buttonsLayout.setSpacing(true);
        

        
        Table orderTable = new Table("Список заказов");
        orderTable.setSelectable(true);
        orderTable.setImmediate(true);
        orderTable.addContainerProperty("Описание", String.class, null);
        orderTable.addContainerProperty("Клиент", String.class, null);
        orderTable.addContainerProperty("Механик", String.class, null);
        orderTable.addContainerProperty("Дата создания", String.class, null);
        orderTable.addContainerProperty("Дата окончания работ", String.class, null);
        orderTable.addContainerProperty("Стоимость", String.class, null);
        orderTable.addContainerProperty("Статус", String.class, null);
        
        orderList = carWorkshopDB.getOrderList();
        clientList = carWorkshopDB.getClientList();
        mechanicList = carWorkshopDB.getMechanicList();
        for (Order order : orderList) {
            String currentMechanic = "";
            String currentClient = "";
            for (Client client : clientList) {
                if (client.getID() == order.getClient()) {                    
                    currentClient = client.getFullName();
                };
            } 
            for (Mechanic mechanic : mechanicList) {
                if (mechanic.getID() == order.getMechanic()) {                    
                    currentMechanic = mechanic.getFullName();
                };
            }
            orderTable.addItem(new Object[]{order.getDescription(), 
                currentClient, currentMechanic, 
                order.getStartDate().toString(), 
                order.getFinishDate().toString(), 
                order.getCost(), 
                order.getStatus().toString()}, order.getID());
        }
        
        addOrder.addClickListener((Button.ClickListener) clickEvent -> {
            UI.getCurrent().addWindow(new WindowEditOrder(0));
        });
        
        editOrder.addClickListener((Button.ClickListener) clickEvent -> {
            long currentOrderID = (long) orderTable.getValue();
            UI.getCurrent().addWindow(new WindowEditOrder(currentOrderID));            
        });
        
        deleteOrder.addClickListener((Button.ClickListener) clickEvent -> {   
            long currentOrderID = (long) orderTable.getValue();
            if (carWorkshopDB.deleteOrder(currentOrderID)) {
                orderTable.removeItem(orderTable.getValue());
                resultLabel.setValue("Удалено");
            } else {
                resultLabel.setValue("Не удалено");
            }            
        });
               
        for (OrderStatusEnum tempEnum : OrderStatusEnum.values()) {
            filterStatus.addItems(tempEnum.toString());
        }
        
        for (Client client : clientList) {
            filterClient.addItem(client.getFullName());
        }
        
        filterLayout.addComponents(filterClient, filterStatus, 
                filterDescription, applyFilter);
        filterLayout.setSpacing(true);

        applyFilter.addClickListener((Button.ClickListener) clickEvent -> { 
            SimpleStringFilter filter1 = null;
            SimpleStringFilter filter2 = null;
            SimpleStringFilter filter3 = null;
            Filterable f = (Filterable) orderTable.getContainerDataSource();
            if (filter1 != null) {
                f.removeContainerFilter(filter1);
            }
            if (filter2 != null) {
                f.removeContainerFilter(filter2);
            }
            if (filter3 != null) {
                f.removeContainerFilter(filter3);
            }
            filter1 = new SimpleStringFilter("Описание", 
                    filterDescription.getValue(), true, false);
            filter2 = new SimpleStringFilter("Статус", 
                    filterStatus.getValue().toString(), true, false);
            filter3 = new SimpleStringFilter("Клиент", 
                    filterClient.getValue().toString(), true, false);
            f.addContainerFilter(filter1);
            f.addContainerFilter(filter2);                                   
            f.addContainerFilter(filter3);
        });
        
        addComponents(templabel, buttonsLayout, filterLayout, resultLabel, 
                orderTable);
    }    
}
