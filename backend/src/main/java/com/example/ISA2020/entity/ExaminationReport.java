package com.example.ISA2020.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//izvestaj o pregledu
@Table(name="examinationReport")
@Entity
public class ExaminationReport {
	
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
    private Examination examination;

    @OneToOne(cascade = CascadeType.ALL)
    private Prescription prescription;
}
