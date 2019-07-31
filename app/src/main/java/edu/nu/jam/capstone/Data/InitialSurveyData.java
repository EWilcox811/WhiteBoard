package edu.nu.jam.capstone.Data;

public class InitialSurveyData
{
	private String question;
	private String responseItem1;
	private String responseItem2;
	private String responseItem3;
	private String responseDescription1;
	private String responseDescription2;
	private String responseDescription3;
	private double responsePercentage1;
	private double responsePercentage2;
	private double responsePercentage3;

	public InitialSurveyData()
	{
		question = null;
		responseItem1 = null;
		responseItem2 = null;
		responseItem3 = null;
		responseDescription1 = null;
		responseDescription2 = null;
		responseDescription3 = null;
		responsePercentage1 = 0.0;
		responsePercentage2 = 0.0;
		responsePercentage3 = 0.0;
	}

	public InitialSurveyData(String question,
							 String responseItem1, String responseDescription1,
							 String responseItem2, String responseDescription2,
							 String responseItem3, String responseDescription3,
							 double responsePercentage1, double responsePercentage2, double responsePercentage3)
	{
		this.question = question;
		this.responseItem1 = responseItem1;
		this.responseDescription1 = responseDescription1;
		this.responseItem2 = responseItem2;
		this.responseDescription2 = responseDescription2;
		this.responseItem3 = responseItem3;
		this.responseDescription3 = responseDescription3;
		this.responsePercentage1 = responsePercentage1;
		this.responsePercentage2 = responsePercentage2;
		this.responsePercentage3 = responsePercentage3;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getResponseItem1()
	{
		return responseItem1;
	}

	public void setResponseItem1(String responseItem1)
	{
		this.responseItem1 = responseItem1;
	}

	public String getResponseItem2()
	{
		return responseItem2;
	}

	public void setResponseItem2(String responseItem2)
	{
		this.responseItem2 = responseItem2;
	}

	public String getResponseItem3()
	{
		return responseItem3;
	}

	public void setResponseItem3(String responseItem3)
	{
		this.responseItem3 = responseItem3;
	}

	public String getResponseDescription1()
	{
		return responseDescription1;
	}

	public void setResponseDescription1(String responseDescription1)
	{
		this.responseDescription1 = responseDescription1;
	}

	public String getResponseDescription2()
	{
		return responseDescription2;
	}

	public void setResponseDescription2(String responseDescription2)
	{
		this.responseDescription2 = responseDescription2;
	}

	public String getResponseDescription3()
	{
		return responseDescription3;
	}

	public void setResponseDescription3(String responseDescription3)
	{
		this.responseDescription3 = responseDescription3;
	}

	public double getResponsePercentage1()
	{
		return responsePercentage1;
	}

	public void setResponsePercentage1(double responsePercentage1)
	{
		this.responsePercentage1 = responsePercentage1;
	}

	public double getResponsePercentage2()
	{
		return responsePercentage2;
	}

	public void setResponsePercentage2(double responsePercentage2)
	{
		this.responsePercentage2 = responsePercentage2;
	}

	public double getResponsePercentage3()
	{
		return responsePercentage3;
	}

	public void setResponsePercentage3(double responsePercentage3)
	{
		this.responsePercentage3 = responsePercentage3;
	}


}
