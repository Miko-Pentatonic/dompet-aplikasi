package com.spring.waldom.aplikasidompet.repository;

import com.spring.waldom.aplikasidompet.model.Dompet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Dompet, Integer> {
    @Query(value = "SELECT sum(pendapatan - pengeluaran) FROM Dompet")
    Double sisaSaldo();
}
