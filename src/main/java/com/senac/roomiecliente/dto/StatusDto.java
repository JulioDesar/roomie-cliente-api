package com.senac.roomiecliente.dto;

import com.senac.roomiecliente.model.Status;

public class StatusDto {
	
	private Status status;
	
	public StatusDto() {
		
	}
	
	public StatusDto(Status status) {
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}

}
