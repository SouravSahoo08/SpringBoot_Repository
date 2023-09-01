package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Laptop {
	private String brand;
	private int ramSize;
	private int storage;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getRamSize() {
		return ramSize;
	}

	public void setRamSize(int ramSize) {
		this.ramSize = ramSize;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	void method() {
		System.out.println("laptop method");
	}

}
