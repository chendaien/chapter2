package org.smart4j.chapter2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.util.PropsUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private static final String  DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties properties = PropsUtil.loadProps("config.properties");
        DRIVER = properties.getProperty("jdbc.driver");
        URL = properties.getProperty("jdbc.url");
        USERNAME = properties.getProperty("jdbc.username");
        PASSWORD = properties.getProperty("jdbc.password");
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            LOGGER.error("can not load jdbc driver",e);
        }
    }

    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList(String keyword){
        Connection connection = null;
        List<Customer> customers = null;
        try {
            customers = new ArrayList<Customer>();
            String sql = "select * from customer";
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setContact(resultSet.getString("contact"));
                customer.setEmail(resultSet.getString("email"));
                customer.setRemark(resultSet.getString("remark"));
                customer.setTelephone(resultSet.getString("telephone"));
                customers.add(customer);
            }
        }catch (SQLException e){
            LOGGER.error(" execute sql failure ",e);
        }finally {
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    LOGGER.error(" close connection failure ",e);
                }
            }
        }
        return customers;
    }

    /**
     * 获取客户
     */
    public Customer getCustomer(long id){
        //TODO
        return null;
    }

    /**
     * 创建客户
     */
    public boolean createCustomer(Map<String,Object> fieldMap){
        //TODO
        return false;
    }

    /**
     * 更新客户
     */
    public boolean updateCustomer(long id ,Map<String,Object> fieldMap){
        //TODO
        return false;
    }

    /**
     * 删除客户
     */
    public boolean deleteCustomer(long id){
        //TODO
        return false;
    }

}
