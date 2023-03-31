package com.example.coffeestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.coffeestore.domain.Employee;
import com.example.coffeestore.service.EmployeeService;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    private final LoginBean loginBean;

    public EmployeeController(LoginBean loginBean) {
        this.loginBean = loginBean;
    }


    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Employee> listemployee = service.listAll();
        model.addAttribute("listemployee", listemployee);
        System.out.print("Get / ");
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("employee", new Employee());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employee") Employee emp) {
        service.save(emp);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditEmployeePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Employee emp = service.get(id);
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