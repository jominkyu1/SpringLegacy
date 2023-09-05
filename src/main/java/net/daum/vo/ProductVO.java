package net.daum.vo;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ProductVO {
	private String name;
	private double price;
	
	public ProductVO() {};
	public ProductVO(String name, double price) {
		this.name = name;
		this.price = price;
	}
}
