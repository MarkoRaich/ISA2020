package com.example.ISA2020.entity;

import javax.persistence.*;

@Table(name="promotion")
@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval period;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
    private Pharmacy pharmacy;


    
    //KONSTRUKTORI
    public Promotion() {}
    
    

    public Promotion(DateTimeInterval period, String content, Pharmacy pharmacy) {
		super();
		this.period = period;
		this.content = content;
		this.pharmacy = pharmacy;
	}


    //GETERI I SETERI
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTimeInterval getPeriod() {
        return period;
    }

    public void setPeriod(DateTimeInterval period) {
        this.period = period;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
    
}
