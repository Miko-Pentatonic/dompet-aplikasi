package com.spring.waldom.aplikasidompet.service;

import com.spring.waldom.aplikasidompet.model.Dompet;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DompetService {

    public List<Dompet> getAllDompet();
    void saveDompet(Dompet dompet);
    Dompet getDompetById(long id_dom);
    void deleteDompetById(long id_dom);
    Page<Dompet> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    Double sisaSaldo();
}
