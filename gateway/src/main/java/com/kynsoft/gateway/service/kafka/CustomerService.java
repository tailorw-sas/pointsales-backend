package com.kynsoft.gateway.service.kafka;

import com.kynsoft.gateway.dto.RegisterDTO;
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
		try {
		this.customerEventsService.publish(customer);

		}catch (Exception ex){
			String message = ex.getMessage();
		}
		return customer;
		
	}

}
