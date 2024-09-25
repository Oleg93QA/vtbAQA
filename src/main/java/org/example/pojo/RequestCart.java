package org.example.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestCart{

	@JsonProperty("total_discount")
	private Object totalDiscount;

	@JsonProperty("total_price")
	private Object totalPrice;

	@JsonProperty("cart")
	private List<CartItem> cart;
}