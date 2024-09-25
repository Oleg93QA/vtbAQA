package org.example.pojo;

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
public class AddNewProduct{

	@JsonProperty("price")
	private Object price;

	@JsonProperty("name")
	private String name;

	@JsonProperty("discount")
	private Integer discount;

	@JsonProperty("category")
	private String category;
}