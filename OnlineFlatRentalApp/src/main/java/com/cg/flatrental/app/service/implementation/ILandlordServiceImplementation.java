package com.cg.flatrental.app.service.implementation;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.flatrental.app.entity.Landlord;
import com.cg.flatrental.app.exception.LandlordNotFoundException;
import com.cg.flatrental.app.repository.ILandlordRepository;
import com.cg.flatrental.app.service.ILandlordService;

@Service
public class ILandlordServiceImplementation implements ILandlordService{
	
	@Autowired
	ILandlordRepository iLandlordRepository;

	@Override
	public List<Landlord> viewAllLandlord() {
		return iLandlordRepository.findAll();
	}

	@Override
	public Landlord viewLandlord(Long landlordId) {
		Optional<Landlord> landlord = iLandlordRepository.findById(landlordId);
		if(!landlord.isPresent()) {
			throw new LandlordNotFoundException("Landlord("+landlordId+") Not Found");
		}
		return landlord.get();
	}

	@Override
	public Landlord addLandlord(Landlord landlord) {
		iLandlordRepository.save(landlord);
		return landlord;
	}


	

	@Override
	public Landlord updateLandlord(Landlord landlord, Long landlordId) {
		Landlord lanUpdated = iLandlordRepository.findById(landlordId).orElse(null);
		if(!ObjectUtils.isEmpty(lanUpdated)) {
			throw new LandlordNotFoundException("Employee("+landlordId+") Not Found");
		}else {
			
			lanUpdated.setLandlordAge(landlord.getLandlordAge());
			lanUpdated.setLandlordName(landlord.getLandlordName());
			
			iLandlordRepository.save(lanUpdated);
		}		
		return lanUpdated;

	}

	@Override
	public void deleteLandlord(Long landlordId) {
		Optional<Landlord> lanOpt = iLandlordRepository.findById(landlordId);
		if(!lanOpt.isPresent()) {
			throw new LandlordNotFoundException("Employee("+landlordId+") Not Found");
		}else {
			iLandlordRepository.deleteById(landlordId);
		}
		
	}
}
