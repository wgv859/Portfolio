/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.ui;

import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author wgv85
 */
public class FlooringView {

    private UserIO io;

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("********************************************************");
        io.print("*");
        io.print("*");
        io.print("* <<FlOOR MASTERS ORDER MAINTENENCE PROGRAM MENU>> *");
        io.print("*");
        io.print("*");
        io.print("* 1. Display All Orders");
        io.print("* 2. Display Orders For Specific Date");
        io.print("* 3. Add A New Order");
        io.print("* 4. Edit An Order");
        io.print("* 5. Remove An Order");
        io.print("* 6: Save Current Work");
        io.print("* 7. Exit Floor Masters Order Maintenence Program");
        io.print("* 8: Set Training or Production Mode");
        io.print("* 9: View which mode you are currently in.");
        io.print("*");
        io.print("*");
        io.print("*");
        io.print("********************************************************");
        io.print("");
        return io.readInt("Please select from the above choices", 1, 9);
    }

    public void displayDisplayOrdersBanner() {
        io.print("*** VIEW ORDERS ***");
    }

    public void displayListOfOrders(List<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            String customerName = orders.get(i).getCustomerName();
            String orderDate = orders.get(i).getOrderDate().toString();
            String productName = orders.get(i).getProductType().getProductName();
            String customerState = orders.get(i).getStateTax().getState();
            String totalTax = orders.get(i).getTotalTax().toString();
            String subTotal = orders.get(i).getSubTotal().toString();
            String totalCost = orders.get(i).getTotalCost().toString();

            io.print(" # " + (i + 1)
                    + ": " + customerName
                    + "| " + orderDate
                    + " | Product: " + productName
                    + " | State: " + customerState
                    + " | Tax Due: $" + totalTax
                    + " | Subtotal Due: $" + subTotal
                    + " | Total Cost: $" + totalCost);
        }
    }

    public void displayListOfOrdersSuccessBanner() {
        io.readString("Hit enter to continue.");
    }

    public void displayCreateOrderBanner() {
        io.print("*** CREATE NEW ORDER ***");
    }

    public Order createOrder(List<Product> productList, List<Tax> taxList, int orderNumber) {
        Order order = new Order();
        order.setOrderNumber(orderNumber);
        boolean userConfirm = false;

        while (userConfirm == false) {
            displayBlankLine();
            String customerName = getCustomerName();
            displayBlankLine();
            Product productChoice = getProductChoice(productList);
            displayBlankLine();
            Tax stateTaxChoice = getTaxChoice(taxList);
            displayBlankLine();
            BigDecimal squareFootageChoice = getSquareFootage();
            displayBlankLine();
            LocalDate dateChoice = getDate();
            displayBlankLine();

            int confirmation = io.readInt("ID number: " + order.getOrderNumber() + "\nCustomer Name: " + customerName + "\nProduct: " + productChoice.getProductName() + "\nState: " + stateTaxChoice.getState() + "\nSquare footage: " + squareFootageChoice.toString() + "\nDate: " + dateChoice.toString() + "\n" + "\n Is the Information Correct? (1 = yes / 2 = no (re-enter information) / 3 = no (return to main menu) )", 1, 3);

            if (confirmation == 1) {
                order.setCustomerName(customerName);
                order.setCustomerName(customerName);
                order.setProductType(productChoice);
                order.setStateTax(stateTaxChoice);
                order.setTotalSquareFootage(squareFootageChoice);
                order.setOrderDate(dateChoice);
                userConfirm = true;
                
            }
            if (confirmation == 2) {
                userConfirm = false;
            }
            if (confirmation == 3) {
                userConfirm = true;
                order.setOrderNumber(0);
                order.setCustomerName(customerName);
                order.setCustomerName(customerName);
                order.setProductType(productChoice);
                order.setStateTax(stateTaxChoice);
                order.setTotalSquareFootage(squareFootageChoice);
                order.setOrderDate(dateChoice);
            }
        }

    return order;    
    }

    public void displayCreateOrderFailureBanner() {
        io.readString("Order not created. Hit ENTER to continue.");
    }

    private int getIdNumber() {
        int newOrderNumber = io.readInt("Please enter order number: ");
        return newOrderNumber;
    }

    private String getCustomerName() {
        String firstName = io.readString("Please enter the customer's first Name :");
        String lastName = io.readString("Please enter the customer's last name: ");
        String returnString = firstName + " " + lastName;
        return returnString;
    }

    private Product getProductChoice(List<Product> productList) {
        int productSelection = 0;
        io.print("*** PRODUCTS ***");

        for (int i = 0; i < productList.size(); i++) {
            io.print("  " + (i + 1) + " - " + productList.get(i).getProductName());
        }
        productSelection = io.readInt("Choice: ", 1, productList.size()) - 1;
        return productList.get(productSelection);
    }

    private Tax getTaxChoice(List<Tax> taxList) {
        int stateTaxSelection = 0;
        io.print("*** STATES ***");
        displayBlankLine();
        for (int i = 0; i < taxList.size(); i++) {
            io.print("  " + (i + 1) + " - " + taxList.get(i).getState());
        }
        stateTaxSelection = io.readInt(" Choice: ", 1, taxList.size()) - 1;
        return taxList.get(stateTaxSelection);
    }

    private BigDecimal getSquareFootage() {
        BigDecimal squareFootage;
        while (true) {
            try {
                squareFootage = new BigDecimal(io.readString("Enter customer's square footage: "));
                if (squareFootage.compareTo(BigDecimal.ZERO) < 1) {
                    io.print("Must be a positive number. Try again.\n");
                } else {
                    return squareFootage;
                }
            } catch (Exception e) {
                io.print("Please enter a real number.\n");
            }
        }
    }

    public LocalDate getDate() {
        boolean dataValid = false;

        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.parse("2000-01-01");
        while (dataValid == false) {
            try {
                String stringDate = io.readString("Please enter a date (YYYY-MM-DD): ");
                date = LocalDate.parse(stringDate);
                if (date.isBefore(LocalDate.now())) {
                    io.print("Order date must be in the future. Try again.");
                } else if (date.isAfter(now)) {
                    dataValid = true;
                }
            } catch (Exception e) {
                io.print("Invalid Date. Try again.");
            }

        }
        return date;
    }

    public void displayCreateOrderSuccessBanner() {
        io.readString("Order successfully created. Please hit enter to continue.");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayEditOrderBanner() {
        io.print("*** EDIT ORDER ***");
    }

    public int displayGetOrderNumber() {
        return io.readInt("Please enter a valid order number: ");
    }

    public Order getUpdateOrderInfo(Order order, List<Product> productList, List<Tax> taxList) {
        Order bailOutOrder = new Order();
        bailOutOrder.setOrderNumber(order.getOrderNumber());
        bailOutOrder.setCustomerName(order.getCustomerName());
        bailOutOrder.setOrderDate(order.getOrderDate());
        bailOutOrder.setProductType(order.getProductType());
        bailOutOrder.setStateTax(order.getStateTax());
        bailOutOrder.setTotalSquareFootage(order.getTotalSquareFootage());
        
        Order updatedOrder = order;
        boolean userConfirm = false;

        while (userConfirm == false) {
            displayBlankLine();
            io.print("Current customer name: " + order.getCustomerName());
            String customerName = getCustomerName();
            if (customerName.equals(order.getCustomerName()) == false && customerName.isEmpty() == false && customerName.equals(" ") == false) {
                updatedOrder.setCustomerName(customerName);

            } else {
                updatedOrder.setCustomerName(bailOutOrder.getCustomerName());
            }
            displayBlankLine();
            io.print("Current Product Choice: " + order.getProductType().getProductName());
            Product productChoice = getProductChoice(productList);
            if (productChoice.getProductName().equals(order.getProductType().getProductName()) == false && productChoice.getProductName().isEmpty() == false) {
                updatedOrder.setProductType(productChoice);

            } else {
                updatedOrder.setProductType(bailOutOrder.getProductType());
            }
            displayBlankLine();
            io.print("Current State: " + order.getStateTax().getState());
            Tax stateTaxChoice = getTaxChoice(taxList);
            if (stateTaxChoice.getState().equals(order.getStateTax().getState()) == false && stateTaxChoice.getState().isEmpty() == false) {
                updatedOrder.setStateTax(stateTaxChoice);

            } else {
                updatedOrder.setStateTax(bailOutOrder.getStateTax());
            }
            displayBlankLine();
            io.print("Current Square Footage: " + order.getTotalSquareFootage());
            BigDecimal squareFootageChoice = getSquareFootage();
            if (squareFootageChoice.equals(order.getTotalMaterialCost()) == false && squareFootageChoice.toString().isEmpty() == false) {
                updatedOrder.setTotalSquareFootage(squareFootageChoice);

            } else {
                updatedOrder.setTotalSquareFootage(bailOutOrder.getTotalSquareFootage());
            }
            displayBlankLine();
            io.print("Current Date: " + order.getOrderDate().toString());
            LocalDate dateChoice = getDate();
            if (dateChoice.equals(order.getOrderDate()) == false && dateChoice.toString().isEmpty() == false) {
                updatedOrder.setOrderDate(dateChoice);

            } else {
                updatedOrder.setOrderDate(bailOutOrder.getOrderDate());
            }
            displayBlankLine();

            int confirmation = io.readInt("\nCustomer Name: " + customerName + "\nProduct: " + productChoice.getProductName() + "\nState: " + stateTaxChoice.getState() + "\nSquare footage: " + squareFootageChoice.toString() + "\nDate: " + dateChoice.toString() + "\n" + "\n Is the new information Correct? (1 = yes / 2 = no (re-enter information) / 3 = no (return to main menu) )", 1, 3);
            if (confirmation == 1) {
                userConfirm = true;
            }
            if (confirmation == 2) {
                userConfirm = false;
            }
            if (confirmation == 3) {
                updatedOrder = bailOutOrder;
                userConfirm = true;
            }
        }
        return updatedOrder;
    }

    public void displayEditOrderFailureBanner() {
        io.readString("Failed to update order. Please hit enter to continue.");
    }

    public void displayEditOrderSuccessBanner() {
        io.readString("Order successfully updated. Please hit enter to continue.");
    }
    
    public void displayRemoveOrderBanner() {
        io.print("*** REMOVE ORDER ***");
    }
    
    public void displayRemoveOrderSuccessBanner() {
        io.readString("Order successfully deleted. Please hit enter to continue.");
    }
    
    public int removeOrderConfirmation(Order order) {
        int userChoice = io.readInt("\n\nORDER TO BE DELETED\n:ID number: " + order.getOrderNumber() + "\nCustomer Name: " + order.getCustomerName() + "\nProduct: " + order.getProductType().getProductName() + "\nState: " + order.getStateTax().getState() + "\nSquare footage: " + order.getTotalSquareFootage().toString() + "\nDate: " + order.getOrderDate().toString() + "\n" + "\n *WARNING* Are you sure you want to DELETE this order? (1 = yes / 2 = no)", 1, 2);
        return userChoice;
    }
    
    public void displayRemoveOrderFailBanner() {
        io.readString("Failed to delete order. Please hit enter to continue.");
    }
    
    public void displaySaveWorkBanner() {
        io.print("*** SAVE CURRENT WORK ***");
    }
    
    public int getSaveWorkConfirmation() {
        return io.readInt("\nAre you sure you want to save your work? (1 = yes, 2 = no)", 1, 2);
    }
    
    public void displaySaveWorkSuccessBanner() {
        io.readString("Work saved successfully. Please hit enter to continue.");
    }
    
    public void displaySaveWorkFailureBanner() {
        io.readString("Failed to save work. Please hit enter to continue.");
    }
    
    public void displaySetModeBanner() {
        io.print("*** SET PROGRAM MODE ***");
    }
    
    public int displayGetModeSelection() {
        return io.readInt("Please select a mode (1 = production, 2 = training):", 1, 2);
    }
    
    public void displayTrainingModeSwitchSuccessBanner() {
        io.readString("Application is now in training mode. Press ENTER to continue");
    }
    
    public void displayProductionModeSwitchSuccessBanner() {
        io.readString("Application is now in production mode. Press ENTER to continue.");
    }

    public void displayBlankLine() {
        io.print(" ");
    }

    public void displayGoodbyeMessage() {
        io.print("Goodbye!");
    }

    public void displayPlaceHolderMessage() {
        io.readString("This feature not yet implemented. Hit enter to continue.");
    }

    public void displayUnknownCommandBanner() {
        io.print("UNKNOWN COMMAND");
    }
    
    public void displayBailOutMessage() {
        io.readString("Returning to main menu. hit ENTER to continue.");
    }

}
