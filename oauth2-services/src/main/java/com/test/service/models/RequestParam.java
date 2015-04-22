package com.test.service.models;

public class RequestParam implements Comparable<RequestParam>{
	private String paramName;
	private String ParamValue;
	private int order;
	
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return ParamValue;
	}
	public void setParamValue(String paramValue) {
		ParamValue = paramValue;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	@Override
	public int compareTo(RequestParam arg0) {
		return this.order - arg0.getOrder();
	}
	
	@Override
	public String toString() {
		return "RequestParam [paramName=" + paramName + ", ParamValue="
				+ ParamValue + ", order=" + order + "]";
	}
	
}
