package com.haulmont.testtask;

import com.vaadin.ui.*;
import java.util.List;

public class LayoutMechanicsList extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    private final Label templabel = new Label("Список механиков");    
    private final Button addMechanic = new Button("Добавить");
    private final Button editMechanic = new Button("Изменить");
    private final Button deleteMechanic = new Button("Удалить");
    private final Button showStatistic = new Button("Показать статистику");
    private final HorizontalLayout buttonsLayout = new HorizontalLayout();
    private final Label resultLabel = new Label("");
    private final CarWorkshopDB carWorkshopDB = new CarWorkshopDB();
    private List<Mechanic> mechanicList;
    
    public LayoutMechanicsList() {
        buttonsLayout.addComponents(addMechanic, editMechanic, 
                deleteMechanic, showStatistic);
        buttonsLayout.setSpacing(true);
        
        Table tableMechanics = new Table("Список механиков");
        tableMechanics.setSelectable(true);
        tableMechanics.setImmediate(true);
        tableMechanics.addContainerProperty("Имя", String.class, null);
        tableMechanics.addContainerProperty("Фамилия", String.class, null);
        tableMechanics.addContainerProperty("Отчество", String.class, null);
        tableMechanics.addContainerProperty("Почасовая оплата", String.class, null);
        
        mechanicList = carWorkshopDB.getMechanicList();
        for (Mechanic mechanic : mechanicList) {
            tableMechanics.addItem(new Object[]{mechanic.getFirstName(), 
                mechanic.getLastName(), mechanic.getPatronymic(), 
                mechanic.getHourlyRate()}, mechanic.getID());
        }
        
        addMechanic.addClickListener((Button.ClickListener) clickEvent -> { 
            UI.getCurrent().addWindow(new WindowEditMechanic(0));           
        });
        
        editMechanic.addClickListener((Button.ClickListener) clickEvent -> { 
            long currentMechanicID = (long) tableMechanics.getValue();
            UI.getCurrent().addWindow(new WindowEditMechanic(currentMechanicID));            
        });
        
        deleteMechanic.addClickListener((Button.ClickListener) clickEvent -> {
            long currentMechanicID = (long) tableMechanics.getValue();
            if (carWorkshopDB.deleteMechanic(currentMechanicID)) {
                tableMechanics.removeItem(tableMechanics.getValue());
                resultLabel.setValue("Удалено");
            } else {
                resultLabel.setValue("Не удалено");
            }                       
        });
        
        showStatistic.addClickListener((Button.ClickListener) clickEvent -> { 
                      
        });
        
        addComponents(templabel, buttonsLayout, resultLabel, tableMechanics);
    }    
}
