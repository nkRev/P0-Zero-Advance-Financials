package com.nkayyarath.driver;

import com.nkayyarath.dao.customer.CustomerDAO;
import com.nkayyarath.dao.customer.CustomerDAOFactory;
import com.nkayyarath.dao.user.UserDAO;
import com.nkayyarath.dao.user.UserDaoFactory;
import com.nkayyarath.display.LandingDisplay;
import com.nkayyarath.model.Customer;
import com.nkayyarath.model.User;

import java.io.Console;
import java.sql.SQLException;

import static com.nkayyarath.driver.Main.in;
import static com.nkayyarath.driver.Main.menuLog;

public class LandingMenu {
    public void mainMenu() throws SQLException {
        boolean finished = false;
        Customer customer = new Customer();
        CustomerDAO customerDAO = CustomerDAOFactory.getCustomerDAO();
        User user = new User();
        UserDAO userDAO = UserDaoFactory.getUserDAO();
        LandingDisplay mainMenu = new LandingDisplay();
        menuLog.debug("Reached Landing Menu");

        do {
            mainMenu.display();
            System.out.print(">> ");
            int input = in.nextInt();
            switch (input) {
                //become a customer.
                case 1:
                    menuLog.info("New Customer Account");
                    //Create Customer ACC
                    System.out.println("please enter first name: ");
                    String firstName = in.next();

                    System.out.println("Please enter last name: ");
                    String lastName = in.next();
                    in.nextLine(); //consumes a line so address's scanner doesn't skip

                    System.out.println("please enter your address: ");
                    String address = in.nextLine();

                    System.out.println("Please enter your city: ");
                    String city = in.nextLine();

                    System.out.println("please enter your State's initials: ");
                    String state = in.next();
                    in.nextLine();

                    System.out.println("Phone number: ");
                    String phone = in.nextLine();

                    System.out.println("SSN: ");
                    String ssn = in.nextLine();

                    //get realtime date.
                    java.util.Date date = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    //set customer object
                    customer.setFirstName(firstName);
                    customer.setLastName(lastName);
                    customer.setAddress(address);
                    customer.setCity(city);
                    customer.setState(state);
                    customer.setPhoneNumber(phone);
                    customer.setSsn(ssn);
                    customer.setDate(sqlDate);

                    //add customer to db
                    customerDAO.addCustomer(customer);

                    System.out.println("IMPORTANT INFORMATION PLEASE KEEP SAFE");
                    System.out.println(customer);
                    break;
                case 2:
                    System.out.println("*******************");
                    menuLog.info("Create a new Account");
                    System.out.println("Username: ");
                    String username = in.next();


                    System.out.println("Password (alphanumeric no white space): ");
                    String strpw = in.next();

                    user.setUsername(username);
                    user.setPassword(strpw);
                    user.setUserType("customer");
                    userDAO.createUser(user);

                    System.out.println("IMPORTANT INFORMATION PLEASE KEEP SAFE");
                    System.out.println(user);
                    break;
                //log-in for customer
                case 3:
                    System.out.println("***********************");
                    menuLog.info("Customer Login");
                    System.out.println("username: ");
                    username = in.next();

                    System.out.println("password: ");
                    strpw = in.next();

                    userDAO.authenticateCustomerLogin(username, strpw);
                    break;

                //login for employee
                case 4:
                    //EmpMenu.employeeMenu();
                    System.out.println("***********************");
                    menuLog.info("Employee Login");
                    System.out.println("username: ");
                    username = in.next();

                    System.out.println("password: ");
                    strpw = in.next();

                    userDAO.authenticateEmployeeLogin(username, strpw);
                    break;
                //quit
                case 0:
                    menuLog.info("Quitting");
                    System.out.println("Thank You for doing business with Zero Advance Financials!");
                    finished = true;
                    break;
            }
        } while (!finished);
    }

}
