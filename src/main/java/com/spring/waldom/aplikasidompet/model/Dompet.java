package com.spring.waldom.aplikasidompet.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dompetsaya")
public class Dompet {

    @Id
    @Column(name="id_dom")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_dom;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "date")
    private Date tanggal;

    @Column(name = "pendapatan")
    private Double pendapatan = Double.valueOf(0);

    @Column(name = "pengeluaran")
    private Double pengeluaran = Double.valueOf(0);



    public Long getId_dom() {
        return id_dom;
    }

    public void setId_dom(Long id_dom) {
        this.id_dom = id_dom;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Double getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(Double pendapatan) {
        this.pendapatan = pendapatan;
    }

    public Double getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(Double pengeluaran) {
        this.pengeluaran = pengeluaran;
    }
}

