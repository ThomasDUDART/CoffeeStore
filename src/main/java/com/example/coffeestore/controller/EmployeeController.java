package com.example.coffeestore.controller;

import com.example.coffeestore.domain.beans;
import com.example.coffeestore.service.CoffeesstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;


@Controller
public class EmployeeController {

    @Autowired
    private CoffeesstoreService service;
    private List<beans> listebeansAvendre = null;
    private List<beans> listeachat = new ArrayList<beans>();
    private final LoginBean loginBean;

    public EmployeeController(LoginBean loginBean) {
        this.loginBean = loginBean;
    }



    @GetMapping("/Home")
    public String viewHomePage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        List<beans> listebeans = service.listAll();
        model.addAttribute("listebeans", listebeans);
        return "index";
    }

    @GetMapping("/Admin")
    public String viewGestionPage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        List<beans> listebeans = service.listAll();
        model.addAttribute("newBeans", new beans());
        model.addAttribute("listebeans", listebeans);
        return "GestionDesStock";
    }

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "Home";
    }

    @GetMapping("/shopBeans")
    public String viewShopBeans(Model model) {
        listebeansAvendre = service.listAll();
        model.addAttribute("listebeans", listebeansAvendre);
        model.addAttribute("listeachat", listeachat);
        return "ShopBeans";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable(name = "id") int id) {
        if(!listeachat.contains(listebeansAvendre.get(id-2))){
            listeachat.add(listebeansAvendre.get(id-2));
            listeachat.get(listeachat.indexOf(listebeansAvendre.get(id-2))).setUnite(1);
        }
        else listeachat.get(listeachat.indexOf(listebeansAvendre.get(id-2))).setUnite(listeachat.get(listeachat.indexOf(listebeansAvendre.get(id-2))).getUnite()+1);

        return "redirect:/shopBeans";
    }

    @GetMapping("/accueil")
    public String backHome() {
        return "redirect:/";
    }

    @GetMapping("/panier")
    public String viewCart(Model model) {
        model.addAttribute("listeachat", listeachat);
        return "Panier";
    }



    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("beans", new beans());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("beans") beans grain) {
        service.save(grain);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditEmployeePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        beans grain = service.get(id);
        mav.addObject("beans", grain);
        return mav;

    }

    @RequestMapping(value = "/login/{id},{pwd}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody LoginBean loginService(@PathVariable String id, @PathVariable String pwd) {
        //LoginBean loginBean = new LoginBean();
        loginBean.setUserId(id);
        loginBean.setPwd(pwd);
        return loginBean;
    }

    @RequestMapping("/delete/{id}")
    public String deleteEmployeePage(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}