package com.fruit.es.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@Document(indexName="test_index", type="resource", shards = 5, replicas = 1)
@NoArgsConstructor
public class EsResource {
  
	@Id
	@Field(index=FieldIndex.not_analyzed,store=true)
	private String id;
	
	@Field(type=FieldType.String,store=true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String name;
	
	@Field(type=FieldType.String,store=true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String title;
	
	@Field(type=FieldType.Date)
	private Date createDate;
	
	@Field(type=FieldType.Double)
	private Double price=0d;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
