package edu.nu.jam.capstone.Data;

import androidx.annotation.Nullable;

public class ProgressiveSurveyData
{
	private String learningObjective;
	private double classConfidencePercentage;
	private double studentConfidencePercentage;

	//TODO: change learning outcome to learning objective in the wireframe.
	//TODO: add submit btn for the student view (to confirm changes to the backend).

	public ProgressiveSurveyData()
	{
		learningObjective = null;
		classConfidencePercentage = 0.0;
		studentConfidencePercentage = 0.0;
	}

	public ProgressiveSurveyData(String learningObjective, double classConfidencePercentage, double studentConfidencePercentage)
	{
		this.learningObjective = learningObjective;

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
}
