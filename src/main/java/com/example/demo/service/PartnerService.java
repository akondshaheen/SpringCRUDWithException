package com.example.demo.service;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.Partners;
import com.example.demo.repo.PartnerRepo;

@Service
public class PartnerService {

	private final PartnerRepo partnerRepo;
	
	@Autowired
    public PartnerService(PartnerRepo partnerRepo) {
		this.partnerRepo = partnerRepo;
	}


	/**
	 * Get a Page contains list of partners
	 * @return Partners
	 */
	public Page<Partners> getPartnersList(){
		Pageable page = PageRequest.of(0, 10);
		return partnerRepo.findAll(page);
    }
	
	/**
	 * get partner with id
	 * @param id
	 * @return a specific partner
	 */
	public Partners getPartner(Long id) {
		boolean exists = partnerRepo.existsById(id);
		
		if(!exists) {
			throw new ApiRequestException("Partner with id " + id + " not found!");
		}
		return partnerRepo.findById(id).get();		
	}

	/**
	 * add partner to the system
	 * @param partner
	 */
	public void addNewPartner(Partners partner) {
		
		Optional<Partners>partnerRepoOptional = partnerRepo.findPartnersByCompanyName(partner.getCompanyName());
		
		if(partnerRepoOptional.isPresent()) {
			throw new ValidationException("Partner with company name "+ partner.getCompanyName() + " is already exist!");
		}
		partnerRepo.save(partner);
	}
	
	/**
	 * delete partner from database
	 * @param id
	 */
	public void deletePartner(Long id) {
		// TODO Auto-generated method stub
		boolean exists = partnerRepo.existsById(id);
		
		if(!exists) {
			throw new ApiRequestException("Company with id "+ id+" does not exist!");
		}
		partnerRepo.deleteById(id);		
	}

	/**
	 * Update partner table with id
	 * @param id
	 * @param companyName
	 * @param ref
	 * @param locale
	 * @param date
	 */
	@Transactional
	public void updatePartner(Long id, String companyName, String ref, Locale locale, LocalDate date) {
		
		Partners partners = partnerRepo.findById(id)
				.orElseThrow(() -> new ApiRequestException("Partner with id "+ id+ " does not exist!"));
		
		if(companyName != null && companyName.length() > 0 && !Objects.equals(partners.getCompanyName(), companyName)) {
			partners.setCompanyName(companyName);
		}
		
		if(ref != null && ref.length() > 0 && !Objects.equals(partners.getRef(), ref)) {
			partners.setRef(ref);
		}
		
		if(locale != null && !Objects.equals(partners.getLocale(), locale)) {
			partners.setLocale(locale);
		}
		
		if(date != null && !Objects.equals(partners.getExpires() , date)) {
			partners.setExpires(date);
		}
	}
}
