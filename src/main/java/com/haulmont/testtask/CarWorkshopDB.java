package com.haulmont.testtask;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
//import java.net.URL;

public class CarWorkshopDB implements InterfaceDB {
    private final String databaseName = "CarWorkshopDB/CarWorkshopDB";
    private final String databasePreConfig = "jdbc:hsqldb:file:";
    private final String databaseURL;
    private final String jdbcDriver = "org.hsqldb.jdbc.JDBCDriver";
    private Connection connection;
    private Statement statement;    
    
    public CarWorkshopDB() {
        //according to the technical task database should be in the same folder
        String dbfilepath = getClass().getProtectionDomain().getCodeSource()
                .getLocation().getPath();
        int indexEnd = dbfilepath.indexOf("target");
        databaseURL = databasePreConfig + dbfilepath.substring(1, indexEnd)
                + databaseName;
    }  
    
    private ResultSet toolQuery(String sqlRequest) 
            throws ClassNotFoundException, SQLException {        
        Class.forName(jdbcDriver);
        connection = DriverManager.getConnection(databaseURL, "SA", "");
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlRequest);
        if (statement != null)
            statement.close();
        if (connection != null)
            connection.close();
        return resultSet;        
    }
    
    private boolean toolUpdate(String sqlRequest) {
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(databaseURL, "SA", "");
            statement = connection.createStatement();
            int affected = statement.executeUpdate(sqlRequest);
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
            if (affected > 0)
                return true;
        } catch (Exception e) {
            return false;
        }        
        return false;          
    }
    
    @Override
    public List<Client> getClientList(){
        List<Client> clientList = new ArrayList<Client>();
        String sqlRequest = "SELECT * FROM CLIENTS";
        try {
            ResultSet resultSet = toolQuery(sqlRequest);            
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String patronymic = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                Client client = new Client(id, firstName, lastName, 
                        patronymic, phoneNumber);
                clientList.add(client);                              
            } 
        } catch (Exception e) {
            return clientList;
        }                   
        return clientList;
    };
    
    @Override
    public Client getClient(long ID) {
        Client client;
        String sqlRequest = "SELECT * FROM clients WHERE clientID = " + ID;
        try {
            ResultSet resultSet = toolQuery(sqlRequest);            
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String patronymic = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                client = new Client(id, firstName, lastName, 
                        patronymic, phoneNumber);
                return client;
            }
        } catch (Exception e) {
            client = new Client(0, "", "", "", "");
            return client;
        } 
        client = new Client(0, "", "", "", "");
        return client;
    };
    
    @Override
    public boolean addClient(Client client) {
        String sqlRequest = "INSERT INTO clients " 
                + "(firstName, lastName, patronymic, phoneNumber) VALUES ('" 
                + client.getFirstName() + "', '" 
                + client.getLastName() + "', '" 
                + client.getPatronymic() + "', '" 
                + client.getPhone() + "');";
        return toolUpdate(sqlRequest);
    };
    
    @Override
    public boolean editClient(Client client) {
        String sqlRequest = "UPDATE clients SET (firstName, lastName, "
                + "patronymic, phoneNumber) = ('" 
                + client.getFirstName() + "', '" 
                + client.getLastName() + "', '" 
                + client.getPatronymic() + "', '" 
                + client.getPhone() + "') WHERE clientID = " 
                + client.getID();
        return toolUpdate(sqlRequest);
    };
    
    @Override
    public boolean deleteClient(long ID) {
        String sqlRequest = "DELETE FROM clients WHERE clientID = " + ID;
        return toolUpdate(sqlRequest);
    };
    
    @Override
    public List<Mechanic> getMechanicList() {
        List<Mechanic> mechanicList = new ArrayList<Mechanic>();
        String sqlRequest = "SELECT * FROM MECHANICS";
        try {
            ResultSet resultSet = toolQuery(sqlRequest);            
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String patronymic = resultSet.getString(4);
                String hourlyRate = resultSet.getString(5);
                Mechanic mechanic = new Mechanic(id, firstName, lastName, 
                        patronymic, hourlyRate);
                mechanicList.add(mechanic);
            }
        } catch (Exception e) {
            return mechanicList;
        }  
        return mechanicList;
    };
    
    @Override
    public Mechanic getMechanic(long ID) {
        Mechanic mechanic;
        String sqlRequest = "SELECT * FROM MECHANICS WHERE MECHANICID = " + ID;
        try {
            ResultSet resultSet = toolQuery(sqlRequest);            
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String patronymic = resultSet.getString(4);
                String hourlyRate = resultSet.getString(5);
                mechanic = new Mechanic(id, firstName, lastName, 
                        patronymic, hourlyRate);
                return mechanic;
            }
        } catch (Exception e) {
            mechanic = new Mechanic(0, "", "", "", "");
            return mechanic;
        }   
        mechanic = new Mechanic(0, "", "", "", "");
        return mechanic;
    };
    
    @Override
    public boolean addMechanic(Mechanic mechanic) {
        String sqlRequest = "INSERT INTO mechanics " 
                + "(firstName, lastName, patronymic, hourlyRate) VALUES ('" 
                + mechanic.getFirstName() + "', '" 
                + mechanic.getLastName() + "', '" 
                + mechanic.getPatronymic() + "', '" 
                + mechanic.getHourlyRate() + "');";
        return toolUpdate(sqlRequest);
    };
    
    @Override
    public boolean editMechanic(Mechanic mechanic) {
        String sqlRequest = "UPDATE mechanics SET (firstName, lastName, "
                + "patronymic, hourlyRate) = ('" 
                + mechanic.getFirstName() + "', '" 
                + mechanic.getLastName() + "', '" 
                + mechanic.getPatronymic() + "', '" 
                + mechanic.getHourlyRate() + "') WHERE clientID = " 
                + mechanic.getID();
        return toolUpdate(sqlRequest);
    };
    
    @Override
    public boolean deleteMechanic(long ID) {
        String sqlRequest = "DELETE FROM mechanics WHERE mechanicID = " + ID;
        return toolUpdate(sqlRequest);
    };    
    
    @Override
    public List<Order> getOrderList() {
        List<Order> orderList = new ArrayList<Order>();        
        String sqlRequest = "SELECT * FROM ORDERS";
        try {
            ResultSet resultSet = toolQuery(sqlRequest);            
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                long client = resultSet.getLong(2);
                long mechanic = resultSet.getLong(3);
                String description = resultSet.getString(4);
                Date startDate = resultSet.getDate(5);
                Date finishDate = resultSet.getDate(6);
                String cost = resultSet.getString(7);
                int statusInt = resultSet.getInt(8);
                OrderStatusEnum status;
                switch (statusInt) {
                    case 0:
                        status = OrderStatusEnum.scheduled;
                        break;
                    case 1:
                        status = OrderStatusEnum.done;
                        break;
                    case 2:
                        status = OrderStatusEnum.accepted;
                        break;
                    default:
                        status = OrderStatusEnum.accepted;                        
                }
                Order order = new Order(id, description, client, mechanic, 
                        startDate, finishDate, cost, status);
                orderList.add(order);                
            }
        } catch (Exception e) {
            return orderList;
        }        
        return orderList;
    };
    
    @Override
    public Order getOrder(long ID) {
        Order order;
        String sqlRequest = "SELECT * FROM ORDERS WHERE ORDERID = " + ID;
        try {
            ResultSet resultSet = toolQuery(sqlRequest);            
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                long client = resultSet.getLong(2);
                long mechanic = resultSet.getLong(3);
                String description = resultSet.getString(4);
                Date startDate = resultSet.getDate(5);
                Date finishDate = resultSet.getDate(6);
                String cost = resultSet.getString(7);
                int statusInt = resultSet.getInt(8);
                OrderStatusEnum status;
                switch (statusInt) {
                    case 0:
                        status = OrderStatusEnum.scheduled;
                        break;
                    case 1:
                        status = OrderStatusEnum.done;
                        break;
                    case 2:
                        status = OrderStatusEnum.accepted;
                        break;
                    default:
                        status = OrderStatusEnum.accepted;                        
                }
                order = new Order(id, description, client, mechanic, 
                        startDate, finishDate, cost, status);
                return order;
            }
        } catch (Exception e) {
            order = new Order(0, "", 0, 0, new Date(), new Date(), 
                    "", OrderStatusEnum.accepted);
            return order;
        } 
        order = new Order(0, "", 0, 0, new Date(), new Date(), 
                "", OrderStatusEnum.accepted);
        return order;
    };
    
    @Override
    public boolean addOrder(Order order) {
        int sqlStatus;
        switch (order.getStatus()) {
        case scheduled:
            sqlStatus = 0;
            break;
        case done:
            sqlStatus = 1;
            break;
        case accepted:
            sqlStatus = 2;
            break;
        default:
            sqlStatus = 2;
        }
        Date startDate = order.getStartDate();
        Date finishDate = order.getFinishDate();        
        String stringStartDate = (startDate.getYear()+1900) + "-" + (startDate.getMonth()+1) + "-" + startDate.getDate();
        String stringFinishDate = (finishDate.getYear()+1900) + "-" + (finishDate.getMonth()+1) + "-" + finishDate.getDate();
        String sqlRequest = "INSERT INTO orders (clientID, mechanicID, " 
                + "description, dateStart, dateEnd, cost, status) VALUES ('" 
                + order.getClient() + "', '" 
                + order.getMechanic() + "', '" 
                + order.getDescription() + "', '" 
                + stringStartDate + "', '"
                + stringFinishDate + "', '"
                + order.getCost() + "', '"
                + sqlStatus + "');";
        return toolUpdate(sqlRequest);
    };
    
    @Override
    public boolean editOrder(Order order) {
        int sqlStatus;
        switch (order.getStatus()) {
        case scheduled:
            sqlStatus = 0;
            break;
        case done:
            sqlStatus = 1;
            break;
        case accepted:
            sqlStatus = 2;
            break;
        default:
            sqlStatus = 2;
        }
        String sqlRequest = "UPDATE orders SET (clientID, mechanicID, "
                + "description, dateStart, dateEnd, cost, status) = ('" 
                + order.getClient() + "', '" 
                + order.getMechanic() + "', '" 
                + order.getDescription() + "', '" 
                + order.getStartDate() + "', '" 
                + order.getFinishDate() + "', '" 
                + order.getCost() + "', '" 
                + sqlStatus + "') WHERE orderID = " 
                + order.getID();
        return toolUpdate(sqlRequest);
    };
    
    @Override
    public boolean deleteOrder(long ID) {
        String sqlRequest = "DELETE FROM orders WHERE orderID = " + ID;
        return toolUpdate(sqlRequest);
    };    
}
