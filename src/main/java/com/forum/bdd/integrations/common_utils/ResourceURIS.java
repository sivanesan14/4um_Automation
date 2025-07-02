package com.forum.bdd.integrations.common_utils;

public class ResourceURIS {

	private static final String Get_Combo_Values = "/getComboValues";
	private static final String Get_Combo_Values_Null = "/getComboValues";
	private static final String Get_Contry_Values = "/getCountries";
	private static final String Get_Currency_Values = "/getCurrencies";
	private static final String Get_Currency_Values_Null = "/getCurrencies";
	private static final String Get_DeliveryServiceTemplateDetails_Values = "/getDeliveryServiceTemplateDetails";
	private static final String Get_DeliveryServiceTemplateDetails_Values_Null = "/getDeliveryServiceTemplateDetails";
	private static final String Get_DeliveryServices_Values = "/getDeliveryServices";
	private static final String Get_DeliveryServices_Values_Null = "/getDeliveryServices";

	private static final String soapEndpoint = "/webservicesserver/NumberConversion.wso";
	private static final String soapEndpoint2 = "/calculator.asmx";

	public static String getResourceURI(String requestName) {
		switch (requestName) {

		case "Get_Combo_Values":
			return Get_Combo_Values;

		case "Get_Combo_Values_Null":
			return Get_Combo_Values_Null;

		case "Get_Contry_Values":
			return Get_Contry_Values;

		case "Get_Currency_Values":
			return Get_Currency_Values;

		case "Get_Currency_Values_Null":
			return Get_Currency_Values_Null;

		case "Get_DeliveryServiceTemplateDetails_Values":
			return Get_DeliveryServiceTemplateDetails_Values;

		case "Get_DeliveryServiceTemplateDetails_Values_Null":
			return Get_DeliveryServiceTemplateDetails_Values_Null;

		case "Get_DeliveryServices_Values":
			return Get_DeliveryServices_Values;

		case "Get_DeliveryServices_Values_Null":
			return Get_DeliveryServices_Values_Null;

		case "soapEndpoint":
			return soapEndpoint;
		case "soapEndpoint2":
			return soapEndpoint2;

		default:
			throw new RuntimeException("Resource uri not defined for the specific file name - " + requestName);
		}
	}
}
