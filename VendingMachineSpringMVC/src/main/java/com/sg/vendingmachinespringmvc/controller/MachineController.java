package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MachineController {
 
    VendingMachineDao dao;
    VendingMachineServiceLayer service;

    String userMoneyController = "0";
    String messageController = "";
    long itemNumberController = 0;
    double userDepositController = 0;
    String changeStringController = "$X:XX";

    @Inject
    public MachineController(VendingMachineDao dao, VendingMachineServiceLayer service) {
        this.dao = dao;
        this.service = service;
    }

    @RequestMapping(value = "/")
    public String firstLoad() {

        return "redirect:displayMachinePage";
    }

    @RequestMapping(value = "/displayMachinePage", method = RequestMethod.GET)
    public String displayMachinePage(Model model) {
        
        userDepositController = service.getUserMoneyTotal();
        model.addAttribute("userMoney", userDepositController);
        ArrayList<Item> itemList = service.getAllItems();
        model.addAttribute("itemList", itemList);
        model.addAttribute("itemNumber", itemNumberController);
        model.addAttribute("message", messageController);
        model.addAttribute("changeString", changeStringController);
        return "machine";
    }

    @RequestMapping(value = "/addDollar", method = RequestMethod.GET)
    public String addDollar() {
        userMoneyController = service.addDollar();
        return "redirect:displayDeposit";
    }

    @RequestMapping(value = "/addQuarter", method = RequestMethod.GET)
    public String addQuarter() {
        userMoneyController = service.addQuarter();
        return "redirect:displayDeposit";
    }

    @RequestMapping(value = "/addDime", method = RequestMethod.GET)
    public String addDime() {
        userMoneyController = service.addDime();
        return "redirect:displayDeposit";
    }

    @RequestMapping(value = "/addNickel", method = RequestMethod.GET)
    public String addNickel() {
        userMoneyController = service.addNickel();
        return "redirect:displayDeposit";
    }

    @RequestMapping(value = "/getItem", method = RequestMethod.GET)
    public String getItem() {
        return "machine";
    }

    @RequestMapping(value = "/displayDeposit", method = RequestMethod.GET)
    public String displayDeposit() {

        return "redirect:displayMachinePage";
    }

    @RequestMapping(value = "/displayMessage", method = RequestMethod.GET)
    public String displayMessage() {
        return "redirect:displayMachinePage";
    }

    @RequestMapping(value = "/displayItem", method = RequestMethod.GET)
    public String displayItem(HttpServletRequest request, Model model) {
        String itemIdParameter = request.getParameter("itemNumber");
        itemNumberController = Long.parseLong(itemIdParameter);

        return "redirect:displayMachinePage";
    }

    @RequestMapping(value = "/displayChange", method = RequestMethod.GET)
    public String displayChange() {
        service.refundMoney(itemNumberController);
        return "redirect:displayMachinePage";
    }

    @RequestMapping(value = "/clearFields", method = RequestMethod.GET)
    public String clearFields() {
        userMoneyController = "0";
        messageController = "";
        itemNumberController = 0;
        changeStringController = "$X.XX";
        service.clearUserMoneyTotal();
        

        return "redirect:displayMachinePage";
    }

    @RequestMapping(value = "/purchaseItem", method = RequestMethod.GET)
    public String purchaseItem() {
        messageController = service.purchaseItem(itemNumberController);
        changeStringController = service.refundMoney(itemNumberController);
        return "redirect:displayMachinePage";
    }

}
