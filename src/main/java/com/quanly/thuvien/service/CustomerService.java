package com.quanly.thuvien.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quanly.thuvien.dto.BookDTO;
import com.quanly.thuvien.dto.CustomerDTO;
import com.quanly.thuvien.model.AuthorModel;
import com.quanly.thuvien.model.BookModel;
import com.quanly.thuvien.model.CategoryModel;
import com.quanly.thuvien.model.CustomerModel;
import com.quanly.thuvien.model.LibraryCardModel;
import com.quanly.thuvien.repository.CustomerRepository;
import com.quanly.thuvien.repository.LibraryCardRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private LibraryCardRepository cardRepository;
	
	public CustomerModel create(CustomerModel customer) {
		Optional<LibraryCardModel> opCard = cardRepository.findById(customer.getLibraryCardId());
		if (!opCard.isPresent()) {
			throw new EntityNotFoundException("Library card Id not found!");
		}
		return customerRepository.save(customer);
	};
	
	public List<CustomerModel> getAll() {
		return customerRepository.findAll();
	};
	
	public void deleteById(String id) {
		Optional<CustomerModel> optional = customerRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			customerRepository.deleteById(id);
		}
	};
	
	public CustomerModel update(CustomerModel customer, String id) {
		Optional<CustomerModel> optional = customerRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			customer.setId(id);
			if (customer.getName() == null || customer.getName().isEmpty()) {
				customer.setName(optional.get().getName());
			}
			if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
				customer.setAddress(optional.get().getAddress());
			}
			if (customer.getLibraryCardId() == null || customer.getLibraryCardId().isEmpty()) {
				customer.setLibraryCardId(optional.get().getLibraryCardId());
			}
			return customerRepository.save(customer);
		}
	};
		
	public Page<CustomerDTO> get(Pageable pageable) {
		List<CustomerDTO> list = getListCustomerDto(customerRepository.findAll());
		return new PageImpl<>(list, pageable, list.size());
	};
	
	
	public List<CustomerDTO> getListCustomerDto(List<CustomerModel> listCustomer) {
		List<CustomerDTO> listDto = new ArrayList<>();
		for (CustomerModel customer : listCustomer) {
			CustomerDTO dto = new CustomerDTO();
			BeanUtils.copyProperties(customer, dto);
			Optional<LibraryCardModel> opCard = cardRepository.findById(dto.getLibraryCardId());
			if (!opCard.isPresent()) {
				throw new EntityNotFoundException("Id Card not found!");
			}
			dto.setLibraryCard(opCard.get().getIdCard());

			listDto.add(dto);
		}
		return listDto;
	};
	
	public CustomerDTO getCustomerDto(CustomerModel customer) {
		CustomerDTO dto = new CustomerDTO();
		Optional<LibraryCardModel> opCard = cardRepository.findById(customer.getLibraryCardId());
		if (!opCard.isPresent()) {
			throw new EntityNotFoundException("Id Card not found!");
		}
		dto.setLibraryCard(opCard.get().getIdCard());
		BeanUtils.copyProperties(customer, dto);
		return dto;
	};

	
}
