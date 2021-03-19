package com.spring.waldom.aplikasidompet;

import com.spring.waldom.aplikasidompet.model.Dompet;
import com.spring.waldom.aplikasidompet.repository.DompetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("Dompet/Service")
@ResponseBody
@SpringBootApplication
public class AplikasidompetApplication {

	@Autowired
	private DompetRepository repository;

	@PostMapping("/transaksiShow")
	public String dompetShow(@RequestBody Dompet dompetRequest) {
		Dompet response = repository.save(dompetRequest);
		return "Keterangan: " + response.getKeterangan() + " Requestmu " + response.getPendapatan() + " di waktu "
				+ response.getTanggal() + " Transaski sukses..";
	}

	@GetMapping("/getAllTransaksi")
	public List<Dompet> getAllTransaski() {
		return repository.findAll();
	}

	@GetMapping("/getDompet/{id_dom}")
	public Dompet getTransaski(@PathVariable long id_dom) {
		return repository.findById(id_dom).get();
	}

	@GetMapping("/getSaldo")
	public Double getSaldo() {
		return repository.hitungSisaSaldo();
	}

	@DeleteMapping("/cancelTransaski/{id_dom}")
	public String cancelTransaaski(@PathVariable long id_dom) {
		repository.deleteById(id_dom);
		return "Transaski di cancel, dompetId : " + id_dom;
	}

	@PutMapping("/updateDompet/{id_dom}")
	public Dompet updateDompet(@RequestBody Dompet updateDompetRequest, @PathVariable long id_dom) {
		Dompet dbResponse = repository.findById(id_dom).get();
		dbResponse.setTanggal(updateDompetRequest.getTanggal());
		dbResponse.setKeterangan(updateDompetRequest.getKeterangan());
		dbResponse.setPendapatan(updateDompetRequest.getPendapatan());
		dbResponse.setPengeluaran(updateDompetRequest.getPengeluaran());
		repository.saveAndFlush(dbResponse);
		return dbResponse;
	}

	public static void main(String[] args) {
		SpringApplication.run(AplikasidompetApplication.class, args);
	}

}
