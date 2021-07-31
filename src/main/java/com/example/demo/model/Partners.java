package com.example.demo.model;

import java.time.LocalDate;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Partners {
	
	@Id
	@SequenceGenerator(
			name= "partner_sequence",
			sequenceName = "partner_sequence",
			allocationSize =1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
			)
    private Long id;
	
    private String companyName;
    private String ref;
    private Locale locale;
    private LocalDate expires;

    public Partners() {
    }

    public Partners(String companyName, String ref, Locale locale, LocalDate expires) {
        this.companyName = companyName;
        this.ref = ref;
        this.locale = locale;
        this.expires = expires;
    }


    public Partners(Long id, String companyName, String ref, Locale locale, LocalDate expires) {
        this.id = id;
        this.companyName = companyName;
        this.ref = ref;
        this.locale = locale;
        this.expires = expires;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public LocalDate getExpires() {
        return expires;
    }

    public void setExpires(LocalDate expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "PartnersModel{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", ref='" + ref + '\'' +
                ", locale=" + locale +
                ", expires=" + expires +
                '}';
    }
}
