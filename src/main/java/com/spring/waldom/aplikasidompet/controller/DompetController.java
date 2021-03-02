package com.spring.waldom.aplikasidompet.controller;

import com.spring.waldom.aplikasidompet.model.Dompet;
import com.spring.waldom.aplikasidompet.service.DompetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DompetController {

    Logger logger = LoggerFactory.getLogger(DompetController.class);

    @Autowired
    private DompetService dompetService;

    @GetMapping("/")
    public String viewBeranda(Model model) {
//        model.addAttribute("listDompet", dompetService.getAllDompet());
//        return "index";
        return findPaginated(1, "tanggal", "desc", model);
    }

    @GetMapping("/pendapatanForm")
    public String pendapatanForm(Model model) {
        // create model attribute to bind form data
        Dompet dompet = new Dompet();
        model.addAttribute("dompet", dompet);
        return "pendapatan";
    }

    @GetMapping("/pengeluaranForm")
    public String pengeluaranForm(Model model) {
        // create model attribute to bind form data
        Dompet dompet = new Dompet();
        model.addAttribute("dompet", dompet);
        return "pengeluaran";
    }

    @PostMapping("/saveDompet")
    public String saveDompet(@ModelAttribute("dompetku") Dompet dompet) {
        // save dompet to database
        dompetService.saveDompet(dompet);
        return "redirect:/";
    }

//    @GetMapping("/deleteDompet/{id}")
    @RequestMapping(method = RequestMethod.POST, value = "/deleteDompet/{id}")
    public String deleteDompet(@PathVariable (value = "id") long id) {

        // call delete dompet method
        this.dompetService.deleteDompetById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Dompet> page = dompetService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Dompet> listDompet = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listDompet", listDompet);
        model.addAttribute("sisaSaldo", dompetService.sisaSaldo());
        return "index";
    }

    @GetMapping("/detail_form")
    public ModelMap tampilFormDetail(@RequestParam(required = false, value = "id") Dompet dompet) {
        if (dompet == null) {
            dompet = new Dompet();
        }
        return new ModelMap().addAttribute("dompet", dompet);
    }

}
