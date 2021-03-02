package com.spring.waldom.aplikasidompet.service;

import com.spring.waldom.aplikasidompet.model.Dompet;
import com.spring.waldom.aplikasidompet.repository.DompetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DompetServiceImpl implements DompetService{

    @Autowired
    private final DompetRepository dompetRepository;

    public DompetServiceImpl(DompetRepository dompetRepository) {
        this.dompetRepository = dompetRepository;
    }

    @Override
    public List<Dompet> getAllDompet() {
        return dompetRepository.findAll();
    }

    @Override
    public void saveDompet(Dompet dompet) {
        this.dompetRepository.save(dompet);
    }

    @Override
    public Dompet getDompetById(long id) {
        Optional<Dompet> optional = dompetRepository.findById(id);
        Dompet dompet;
        if (optional.isPresent()) {
            dompet = optional.get();
        } else {
            throw new RuntimeException(" Dompet not found for id :: " + id);
        }
        return dompet;
    }

    @Override
    public void deleteDompetById(long id) {
        this.dompetRepository.deleteById(id);
    }

    @Override
    public Page<Dompet> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.dompetRepository.findAll(pageable);
    }

    @Override
    public Double sisaSaldo() {
        return dompetRepository.hitungSisaSaldo();
    }

}

