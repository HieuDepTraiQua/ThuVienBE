package com.quanly.thuvien.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quanly.thuvien.model.AuthorModel;
import com.quanly.thuvien.model.StaffModel;
import com.quanly.thuvien.repository.StaffRepository;

@Service
public class StaffService {
	@Autowired
	private StaffRepository staffRepository;
	
	public StaffModel create(StaffModel staff) {
		Optional<StaffModel> opStaff = staffRepository.findByPhoneNumber(staff.getPhoneNumber());
		if (opStaff.isPresent()) {
			throw new EntityNotFoundException("This phone number already exists!");
		}
		return staffRepository.save(staff);
	};
	
	public List<StaffModel> getAll() {
		return staffRepository.findAll();
	};
	
	public void deleteById(String id) {
		Optional<StaffModel> optional = staffRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			staffRepository.deleteById(id);
		}
	};
	
	public StaffModel update(StaffModel staff, String id) {
		Optional<StaffModel> optional = staffRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			staff.setId(id);
			if (staff.getName() == null || staff.getName().isEmpty()) {
				staff.setName(optional.get().getName());
			}
			if (staff.getPhoneNumber() == null || staff.getPhoneNumber().isEmpty()) {
				staff.setPhoneNumber(optional.get().getPhoneNumber());
			}
			if (staff.getDob() == null || staff.getDob().isEmpty()) {
				staff.setDob(optional.get().getDob());
			}
			return staffRepository.save(staff);
		}
	};
	
	public Page<StaffModel> get(Pageable pageable) {
		return staffRepository.findAll(pageable);
	};
}
