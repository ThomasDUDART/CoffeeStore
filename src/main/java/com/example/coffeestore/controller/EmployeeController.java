package com.example.coffeestore.controller;

import com.example.coffeestore.domain.beans;
import com.example.coffeestore.domain.origine;
import com.example.coffeestore.service.CaffeService;
import com.example.coffeestore.service.OrigineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class EmployeeController {


    @Autowired
    private CaffeService service;
    @Autowired
    private OrigineService origineService;
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
    public String viewShopBeans(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        List<beans> listebeans = service.listAll();

        model.addAttribute("listebeans", listebeans);
        return "Vitrine";
    }



    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("beans", new beans());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("beans") beans grain, Model model) {
        beans b = service.get(grain.getId());
        grain.setOrigine(b.getOrigine());
        grain.setNotes(b.getNotes());
        service.save(grain);
        List<beans> listebeans = service.listAll();
        model.addAttribute("newBeans", new beans());
        model.addAttribute("listebeans", listebeans);
        return "GestionDesStock";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditEmployeePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        beans grain = service.get(id);
        mav.addObject("beans", grain);
        return mav;

    }
    @RequestMapping("/EditOrigine/{id}")
    public ModelAndView showEditOrigine(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("EditOrigine");
        beans grain = service.get(id);
        List<origine> o = origineService.listAll();
        mav.addObject("beans", grain);
        mav.addObject("listeOrigine", o);
        mav.addObject("origine", grain.getOrigine());
        return mav;

    }
    @RequestMapping(value="/EditOrigine/{id}", params = "origine")
    public ModelAndView SaveOrigineBean(@PathVariable(name = "id") int id, @RequestParam("origine") int origineId) {
        beans grain = service.get(id);
        grain.setOrigine(origineService.get(origineId));
        service.save(grain);
        ModelAndView mav = new ModelAndView("new");
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
    public String deleteEmployeePage(@PathVariable(name = "id") int id, Model model) {
        service.delete(id);
        List<beans> listebeans = service.listAll();
        model.addAttribute("newBeans", new beans());
        model.addAttribute("listebeans", listebeans);
        return "GestionDesStock";
    }
}