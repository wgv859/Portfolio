package com.sg.interestcalculatorspringmvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InterestCalculatorController {
    
    @RequestMapping(value="/userInputs", method=RequestMethod.POST)
    public String userInputs(HttpServletRequest request, Map<String, Object> model) {
        
        List<String> yearlyBalanceList = new ArrayList<>();
        List<String> quarterlyGainsList = new ArrayList<>();
        List<String> yearlyInterestList = new ArrayList<>();
        
        String userOriginalAmount = request.getParameter("userOriginalAmount");
        String userInterestRate = request.getParameter("userInterestRate");
        String userYears = request.getParameter("userYears");
        
        double interestRate = Double.parseDouble(userInterestRate) / 100;
        double quarterlyInterestRate = interestRate / 4;
        double currentBalance = Double.parseDouble(userOriginalAmount);
        int numberOfQuarters = Integer.parseInt(userYears) * 4;
        double interestEarnedInYear = 0;
        
        for (int i = 1; i < numberOfQuarters + 1; i++) {
           double interestEarned;
            interestEarned = currentBalance * quarterlyInterestRate;
           String interestEarnedString = Double.toString(interestEarned);
           interestEarnedInYear = interestEarnedInYear + interestEarned;
           quarterlyGainsList.add(interestEarnedString);
           currentBalance = currentBalance + interestEarnedInYear;
           if(i % 4 == 0) {
               String currentBalanceString = Double.toString(currentBalance);
               yearlyBalanceList.add(currentBalanceString);
               String interestEarnedInYearString = Double.toString(interestEarnedInYear);
               yearlyInterestList.add(interestEarnedInYearString);
               interestEarnedInYear = 0;
           }
           
        }
        
        model.put("userOriginalAmount", userOriginalAmount);
        model.put("yearlyBalanceList", yearlyBalanceList);
        model.put("quarterlyGainsList", quarterlyGainsList);
        model.put("yearlyInterestList", yearlyInterestList);
        
        return "result";
    }
}
