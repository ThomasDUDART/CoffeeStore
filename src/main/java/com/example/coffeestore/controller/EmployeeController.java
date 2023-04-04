package com.example.coffeestore.controller;

import com.example.coffeestore.domain.beans;
import com.example.coffeestore.service.CoffeesstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;


@Controller
public class EmployeeController {

    @Autowired
    private CoffeesstoreService service;
    private final LoginBean loginBean;

    public EmployeeController(LoginBean loginBean) {
        this.loginBean = loginBean;
    }



    @GetMapping("/")
    public String viewHomePage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        List<beans> listebeans = service.listAll();
        System.out.println(listebeans.get(1));
        model.addAttribute("listebeans", listebeans);
        //System.out.print("Get / ");
        System.out.print(model.getAttribute("listebeans"));
        return "index";
    }

    @GetMapping("/HomeSweetHome")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "Vitrine";
    }

    @GetMapping("/Maison")
    public String showHome(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home.html");
        return mav.toString();
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("beans", new beans());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employee") beans emp) {
        service.save(emp);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditEmployeePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        beans emp = service.get(id);
        mav.addObject("employee", emp);
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