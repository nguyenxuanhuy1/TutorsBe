package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "dia_chinh")
public class DiaChinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "cap_dia_chinh")
    private String capDiaChinh;

    @Column(name = "dia_chinh_cha_id")
    private Long diaChinhChaId;

    @Column(name = "hieu_luc")
    private String hieuLuc;
}
