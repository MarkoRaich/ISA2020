package com.example.ISA2020.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name="consultationReport")
@Entity
public class ConsultationReport {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Subject cannot be null.")
    @Column(nullable = false)
    private String subject;

    @NotNull(message = "Message cannot be null.")
    @Column(nullable = false)
    private String message;

    @OneToOne(mappedBy = "examinationReport")
    private Consultation consultation;

    @OneToOne(cascade = CascadeType.ALL)
    private Prescription prescription;
}
