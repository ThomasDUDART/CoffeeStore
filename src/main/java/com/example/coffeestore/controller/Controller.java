package com.example.coffeestore.controller;

import com.example.coffeestore.domain.beans;
import com.example.coffeestore.service.CaffeService;
import jakarta.activation.FileTypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private CaffeService service;
    private List<beans> listebeansAvendre = null;
    private List<beans> listeachat = new ArrayList<beans>();

    private final LoginBean loginBean;

    public Controller(LoginBean loginBean) {
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

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable(name = "id") int id) {

        boolean contiens = true;
        int positionPanier = -1;

        for (int i = 0;i<listeachat.size();i++){
            if(listeachat.get(i).getNom().equals(listebeansAvendre.get(id-2).getNom())){
                contiens = false;
                positionPanier = i;
            }
        }

        if(contiens){
            listeachat.add(listebeansAvendre.get(id-2));
            listeachat.get(listeachat.indexOf(listebeansAvendre.get(id-2))).setQte(1);
        }
        else listeachat.get(positionPanier).setUnitePlusOne();

        return "redirect:/shopBeans";
    }

    @GetMapping("/addOne/{id}")
    public String addOneToCart(@PathVariable(name = "id") int id) {
        int posi = panierPosi(id);
        listeachat.get(posi).setUnitePlusOne();
        return "redirect:/panier";
    }

    @GetMapping("/removeOne/{id}")
    public String removeOneToCart(@PathVariable(name = "id") int id) {

        int posi = panierPosi(id);
        listeachat.get(posi).setUniteMinusOne();

        int tampon = listeachat.get(posi).getQte();

        if (tampon == 0){
            listeachat.remove(posi);
        }
        return "redirect:/panier";

    }

    public int panierPosi(int id){
        int positionPanier = -1;

        for (int i = 0;i<listeachat.size();i++){
            if(listeachat.get(i).getNom().equals(listebeansAvendre.get(id-2).getNom())){
                positionPanier = i;
            }
        }
        return positionPanier;
    }

    @GetMapping("/Buying")
    public String majStockAchat() {


        for (int i = 0; i<listebeansAvendre.size();i++){

            boolean contiens = false;

            int posi = panierPosi(i+2);
            if (posi > -1) contiens = true;

            if (contiens){
                listebeansAvendre.get(i).setQte(listebeansAvendre.get(i).getQte() - listeachat.get(posi).getQte());
                service.save(listebeansAvendre.get(i));
            }
        }

        return "redirect:/panier";
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

    @RequestMapping(value = "/Picture/{id}")
    public ResponseEntity<byte[]> getrealisateur(@PathVariable(name="id") String id) throws IOException {
        File img = new File("src/main/resources/templates/Picture/"+id+".jpg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
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

    @RequestMapping(value = "/login/{id},{pwd}", method = RequestMethod.GET, produces = "application/json")//pas utiliser
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