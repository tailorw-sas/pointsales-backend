package com.kynsoft.service.kafka;

import com.kynsoft.dto.RegisterDTO;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	private final CustomerEventsService customerEventsService;

	public CustomerService(CustomerEventsService customerEventsService) {
		super();
		this.customerEventsService = customerEventsService;
	}

	public RegisterDTO save(RegisterDTO customer) {
		System.out.println("Received " + customer);
		this.customerEventsService.publish(customer);
		return customer;
		
	}

}
