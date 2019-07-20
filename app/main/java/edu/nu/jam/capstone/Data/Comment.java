package edu.nu.jam.capstone.Data;

public class Comment
{
	private String content;
	private double percentage;
	// TODO: make int value of # of replies
	// TODO: make LL of subsequent comments

	public Comment()
	{
		this.content = null;
	}

	public Comment(String content, int percentage)
	{
		this.content = content;
		this.percentage = percentage;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public double getPercentage()
	{
		return percentage;
	}

	public void setPercentage(int percentage)
	{
		this.percentage = percentage;
	}
}
