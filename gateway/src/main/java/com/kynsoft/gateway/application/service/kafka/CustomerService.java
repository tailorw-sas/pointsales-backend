package com.kynsoft.gateway.application.service.kafka;

import com.kynsoft.gateway.application.dto.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	private final CustomerEventsService customerEventsService;

	public CustomerService(CustomerEventsService customerEventsService) {
		super();
		this.customerEventsService = customerEventsService;
	}

	public UserRequest save(UserRequest customer) {
		System.out.println("Received " + customer);
		try {
		this.customerEventsService.publish(customer);

		}catch (Exception ex){
			String message = ex.getMessage();
		}
		return customer;
		
	}

}
