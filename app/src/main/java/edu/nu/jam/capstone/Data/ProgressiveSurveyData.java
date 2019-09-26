package edu.nu.jam.capstone.Data;

import androidx.annotation.Nullable;

public class ProgressiveSurveyData
{
	private String learningObjective;
	private double classConfidencePercentage;
	private double studentConfidencePercentage;
	private String questionid;

	public ProgressiveSurveyData()
	{
		learningObjective = null;
		classConfidencePercentage = 0.0;
		studentConfidencePercentage = 0.0;
		questionid = null;
	}

	public ProgressiveSurveyData(String learningObjective, double classConfidencePercentage, double studentConfidencePercentage, String questionid)
	{
		this.learningObjective = learningObjective;
		this.questionid = questionid;

		if (classConfidencePercentage != -1)
			this.classConfidencePercentage = classConfidencePercentage;
		else
			this.classConfidencePercentage = -1;

		if (studentConfidencePercentage != -1)
			this.studentConfidencePercentage = studentConfidencePercentage;
		else
			this.studentConfidencePercentage = -1;
	}

	public String getLearningObjective()
	{
		return learningObjective;
	}

	public void setLearningObjective(String learningObjective)
	{
		this.learningObjective = learningObjective;
	}

	public double getClassConfidencePercentage()
	{
		return classConfidencePercentage;
	}

	public void setClassConfidencePercentage(double classConfidencePercentage)
	{
		this.classConfidencePercentage = classConfidencePercentage;
	}

	public double getStudentConfidencePercentage()
	{
		return studentConfidencePercentage;
	}

	public void setStudentConfidencePercentage(double studentConfidencePercentage)
	{
		this.studentConfidencePercentage = studentConfidencePercentage;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}
}
