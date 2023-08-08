package com.alex.controllers;

import com.alex.servlets.ReturnField;
import com.alex.servlets.Seq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchController {
    /**
     * Return JSON information of Seq<Seq<String>> object; uses servlet import.
     * Return response back to the user via the response object.
     */
    @RequestMapping("/dmfSearch")
    protected ModelAndView search (HttpServletRequest request, HttpServletResponse response) throws IOException{

        // read form fields
        String searchSSN = request.getParameter("ssn");

        String htmlResponse = "";

        File f = new File(request.getServletContext().getRealPath("/WEB-INF")+"/dmf1.csv");
        String absolute = f.getAbsolutePath();

//        Seq<String> info = ReturnField.ssnInfo("input-tests/dmf/dmf1.csv", searchSSN);
        Seq<String> info = ReturnField.ssnInfo(absolute, searchSSN);

        if (info != null) {
            String json = ReturnField.toJson(info);
            String description = ReturnField.getDescription(info);
            String ssn = ReturnField.getSSN(info);
            String lastName = ReturnField.getLastName(info);
            String suffix = ReturnField.getSuffix(info);
            String firstName = ReturnField.getFirstName(info);
            String middleName = ReturnField.getMiddleName(info);
            String vCode = ReturnField.getVCode(info);
            String deathDate = ReturnField.getDeathDate(info);
            String birthDate = ReturnField.getBirthDate(info);

            htmlResponse += "SSN: " + ssn + " is found in DMF.<br/>";
            htmlResponse += "Last name: " + lastName + "<br/>";
            htmlResponse += "Suffix: " + suffix + "<br/>";
            htmlResponse += "First name: " + firstName + "<br/>";
            htmlResponse += "Middle name: " + middleName + "<br/>";
            htmlResponse += "Birth date: " + birthDate + "<br/>";
            htmlResponse += "Death date: " + deathDate + "<br/>";
            htmlResponse += "Description: " + description + "<br/>";
            htmlResponse += "vCode: " + vCode + "<br/>";
        } else {
            htmlResponse += "SSN: " + searchSSN + " is not found in DMF.<br/>";
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("display.jsp");
        mv.addObject("result",htmlResponse);

        return mv;


    }


}
