package com.example.ISA2020.entity;

import com.example.ISA2020.entity.users.Patient;
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
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;

    //KONTRUKTORI
    public Prescription() {}

	public Prescription(Date date, ExaminationReport examinationReport, ConsultationReport consultationReport,
			Patient patient) {
		super();
		this.date = date;
		this.examinationReport = examinationReport;
		this.consultationReport = consultationReport;
		this.patient = patient;
	}

	//GETERI I SETERI
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ExaminationReport getExaminationReport() {
		return examinationReport;
	}

	public void setExaminationReport(ExaminationReport examinationReport) {
		this.examinationReport = examinationReport;
	}

	public ConsultationReport getConsultationReport() {
		return consultationReport;
	}

	public void setConsultationReport(ConsultationReport consultationReport) {
		this.consultationReport = consultationReport;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
    
    


}
