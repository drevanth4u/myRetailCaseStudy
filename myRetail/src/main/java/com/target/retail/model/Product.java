/**
 * 
 */
package com.target.retail.model;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Revanth
 *
 */
@Document(collection="products")
public class Product implements Serializable {
	

	/**
	 * default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String productId;

    @Transient
	private String title;
    
    public Map<String, String> current_price;


	public Product() {

	}

	public Product(String productId, String title, Map<String, String> current_price) {
		this.productId = productId;
		this.title = title;
		this.current_price = current_price;
	}
	

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Map<String, String> getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(Map<String, String> current_price) {
		this.current_price = current_price;
	}

}
