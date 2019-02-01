package com.haulmont.testtask;

import java.util.List;

public interface InterfaceDB {
    public List<Client> getClientList();
    public Client getClient(long ID);
    public boolean addClient(Client client);
    public boolean editClient(Client client);
    public boolean deleteClient(long ID);
    
    public List<Mechanic> getMechanicList();
    public Mechanic getMechanic(long ID);
    public boolean addMechanic(Mechanic mechanic);
    public boolean editMechanic(Mechanic mechanic);
    public boolean deleteMechanic(long ID);    
    
    public List<Order> getOrderList();
    public Order getOrder(long ID);
    public boolean addOrder(Order order);
    public boolean editOrder(Order order);
    public boolean deleteOrder(long ID);
}
