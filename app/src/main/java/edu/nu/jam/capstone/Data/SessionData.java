package edu.nu.jam.capstone.Data;

public class SessionData
{
	private String courseNumber;
	private String courseName;
	private String term;

	public SessionData()
	{
		this.courseNumber = null;
		this.courseName = null;
		this.term = null;
	}

	public SessionData(String courseNumber, String courseName, String term)
	{
		this.courseNumber = courseNumber;
		this.courseName = courseName;
		this.term = term;
	}

	public String getCourseNumber()
	{
		return courseNumber;
	}

	public String getCourseName()
	{
		return courseName;
	}

	public void setCourseNumber(String courseNumber)
	{
		this.courseNumber = courseNumber;
	}

	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}

	public String getTerm()
	{
		return term;
	}

	public void setTerm(String term)
	{
		this.term = term;
	}
}
