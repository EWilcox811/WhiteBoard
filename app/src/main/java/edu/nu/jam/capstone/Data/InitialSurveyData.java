package edu.nu.jam.capstone.Data;

public class InitialSurveyData
{
	private String question;
	private String responseDescription;
	private double responsePercentage;

	public InitialSurveyData()
	{
		question = null;
		responseDescription = null;
		responsePercentage = 0.0;
	}

	public InitialSurveyData(String question, String responseDescription,
							 double responsePercentage)
	{
		this.question = question;
		this.responseDescription = responseDescription;
		this.responsePercentage = responsePercentage;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getResponseDescription()
	{
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription)
	{
		this.responseDescription = responseDescription;
	}

	public double getResponsePercentage()
	{
		return responsePercentage;
	}

	public void setResponsePercentage(double responsePercentage)
	{
		this.responsePercentage = responsePercentage;
	}

}
