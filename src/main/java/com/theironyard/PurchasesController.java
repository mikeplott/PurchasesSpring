package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by michaelplott on 10/24/16.
 */
@Controller
public class PurchasesController {
    @Autowired
    PurchaseRepository purchases;

    @Autowired
    CustomerRepository customers;

    @PostConstruct
    public void init() throws FileNotFoundException {
        List<Customer> cList = customers.findAll();
        List<Purchase> pList = purchases.findAll();
        if (cList.size() == 0) {
            customerImporter();
        }
        if (pList.size() == 0) {
            purchaseImporter();
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category, Integer page) {
        page = (page == null) ? 0 : page;
        PageRequest pr = new PageRequest(page, 10);

        Page<Purchase> pList;

        if (category != null) {
            pList = purchases.findByCategory(pr, category);
        }
        else {
            pList = purchases.findAll(pr);
        }
        model.addAttribute("purchases", pList);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("showNext", pList.hasNext());
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("showPrev", pList.hasPrevious());
        model.addAttribute("category", category);
        return "index";
    }

    public void customerImporter() throws FileNotFoundException {
        File f = new File("customers.csv");
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] columns = line.split(",");
            String name = columns[0];
            String email = columns[1];
            Customer customer = new Customer(name, email);
            customers.save(customer);
        }
    }

    public void purchaseImporter() throws FileNotFoundException {
        File f = new File("purchases.csv");
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] columns = line.split(",");
            String cID = columns[0];
            String date = columns[1];
            String credit = columns[2];
            String cv = columns[3];
            String category = columns[4];
            Purchase purchase = new Purchase(cID, date, credit, cv, category, customers.findOne(Integer.parseInt(cID)));
            purchases.save(purchase);
        }
    }
}
