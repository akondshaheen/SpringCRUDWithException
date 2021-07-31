package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.InternalException;
import com.example.demo.model.Partners;
import com.example.demo.repo.PartnerRepo;
import com.example.demo.service.PartnerService;

@RestController
@RequestMapping(path = "/")
public class PartnersController {
	
	@Autowired
	PartnerRepo repo;

    private final PartnerService partnerService;

    @Autowired
    public PartnersController(PartnerService partnerService){
        this.partnerService = partnerService;
    }

    /**
     * view all the partner from internal Database
     * @return List of Partners maximum 10 in a page
     */
    @GetMapping("/api/partners")
    public Page<Partners> getPartners(){
    	Page<Partners> partners= partnerService.getPartnersList();
    	if(partners.getSize()<=0) {
			throw new InternalException("Internal error happened!");
    	}
    	return partners;
      }
    
    /**
     * filter partner by id
     * @param id
     * @return Specific partner with the related id
     */
    @GetMapping("/api/partners/{partnerId}")
    public Partners getPartners(@PathVariable("partnerId") Long id){
    	Partners partner = partnerService.getPartner(id);
    	
    	if(partner==null) {
			throw new InternalException("Internal error happened!");
    	}
       return partner;
      }

    /**
     * add Partner to the Database
     * @param partner
     */
    @PostMapping(path = "/api/partners")
    public void addPartner(@RequestBody Partners partner) {
    	if(partner==null) {
			throw new InternalException("Internal error happened!");
    	}
    	partnerService.addNewPartner(partner);
    }
    
    /**
     * Delete partner from Database with the id
     * @param id
     */
    @DeleteMapping(path = "/api/partners/{partnerId}")
    public void deletePartner(@PathVariable("partnerId") Long id) {
    	if(repo.count()<1) {
			throw new InternalException("Internal error happened!");
		}
    	partnerService.deletePartner(id);
    }
    
    /**
     * Update Partner with related Id
     * @param id
     * @param companyName
     * @param ref
     * @param locale
     * @param date
     */
    @PutMapping(path = "/api/partners/{partnerId}")
    public void updatePartner(
    		@PathVariable("partnerId") Long id,
    		@RequestParam(required = false) String companyName,
    		@RequestParam(required = false)String ref,
    		@RequestParam(required = false) Locale locale,
    		@RequestParam(required = false) LocalDate date) {
    		if(repo.count()<1) {
    			throw new InternalException("Internal error happened!");
    		}
    	   	partnerService.updatePartner(id, companyName, ref, locale, date);  	
    }

}
