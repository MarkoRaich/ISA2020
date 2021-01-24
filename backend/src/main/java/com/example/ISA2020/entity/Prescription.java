package com.example.ISA2020.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;


//Recept koji se izdaje nakon pregleda/savetovanja
@Table(name="prescription")
@Entity
public class Prescription {
	
	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
	private Date date;

	//lek i kako se pije ...
	
	@OneToOne(mappedBy = "prescription")
    @JoinColumn(name = "examinationReport_id", referencedColumnName = "id")
    private ExaminationReport examinationReport;

    @OneToOne(mappedBy = "prescription")
    @JoinColumn(name = "consultationReport_id", referencedColumnName = "id")
    private ConsultationReport consultationReport;

    public Prescription() {}


}
