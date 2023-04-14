package com.example.coffeestore.controller;

import com.example.coffeestore.domain.beans;
import com.example.coffeestore.domain.note;
import com.example.coffeestore.domain.origine;
import com.example.coffeestore.service.CaffeService;
import com.example.coffeestore.service.NoteService;
import com.example.coffeestore.service.OrigineService;
import jakarta.activation.FileTypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CoffeeStoreController {

    @Autowired
    private CaffeService service;
    @Autowired
    private OrigineService origineService;
    @Autowired
    private NoteService noteService;
    private List<beans> listebeansAvendre = null;
    private List<beans> listeachat = new ArrayList<beans>();

    private final LoginBean loginBean;


    public CoffeeStoreController(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    @GetMapping("/Admin")
    public ModelAndView viewGestionPage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        ModelAndView mav = new ModelAndView("GestionDesStock");
        List<origine> orig = origineService.listAll();
        mav.addObject("listeOrigine", orig);
        List<note> notNote = noteService.listAll();
        mav.addObject("listeNotes", notNote);
        List<beans> listebeans = service.listAll();
        model.addAttribute("newBeans", new beans());
        model.addAttribute("listebeans", listebeans);
        return mav;
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

        boolean contiens = true;
        int positionPanier = -1;
        for (int i = 0;i<listeachat.size();i++){
            if(listeachat.get(i).getNom().equals(listebeansAvendre.get(id-1).getNom())){
                contiens = false;
                positionPanier = i;
            }
        }

        if(contiens){
            listeachat.add(listebeansAvendre.get(id-1));
            listeachat.get(listeachat.indexOf(listebeansAvendre.get(id-1))).setQte(1);
        }
        else listeachat.get(positionPanier).setUnitePlusOne();

        return "redirect:/shopBeans";
    }

    @GetMapping("/addOne/{id}")
    public String addOneToCart(@PathVariable(name = "id") int id) {

        listeachat.get(id-1).setUnitePlusOne();
        return "redirect:/panier";
    }

    @GetMapping("/removeOne/{id}")
    public String removeOneToCart(@PathVariable(name = "id") int id) {

        int posi = id-1;
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
            if(listeachat.get(i).getNom().equals(listebeansAvendre.get(id).getNom())){
                positionPanier = i;
            }
        }
        return positionPanier;
    }

    @GetMapping("/Buying")
    public String majStockAchat() {


        for (int i = 0; i<listebeansAvendre.size();i++){

            boolean contiens = false;

            int posi = panierPosi(i);
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
        int poidsTotal = 0;
        int prixTotal = 0;

        for(int i = 0; i<listeachat.size();i++){
            poidsTotal+=listeachat.get(i).getPoidsTotal();
            prixTotal+=listeachat.get(i).getPrixTotal();
        }

        model.addAttribute("poidsTotal", poidsTotal);
        model.addAttribute("prixTotal", prixTotal);
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
    public String ModifierBean(@ModelAttribute("beans") beans grain, Model model) {
        beans b = service.get(grain.getId());
        grain.setOrigine(b.getOrigine());
        grain.setNotes(b.getNotes());
        service.save(grain);
        return "redirect:/Admin";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Save(@ModelAttribute("beans") beans grain, Model model) {
        service.save(grain);
        return "redirect:/Admin";
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
        mav.addObject("listeOrigine", o);
        mav.addObject("beans", grain);
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
        return "redirect:/Admin";
    }
}