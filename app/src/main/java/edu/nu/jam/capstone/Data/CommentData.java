package edu.nu.jam.capstone.Data;

import java.util.List;

public class CommentData
{
	private String content;
	private double percentage;
	private int upVotes;
	private int numberOfReplies;
	private List<CommentData> repliesList;

	public CommentData()
	{
		this.content = null;
		this.percentage = 0.0;
		this.upVotes = 0;
		this.numberOfReplies = 0;
		this.repliesList = null;
	}

	public CommentData(String content, int percentage, int upVotes, int numberOfReplies, List<CommentData> subCommentList)
	{
		this.content = content;
		this.percentage = percentage;
		this.upVotes = upVotes;
		this.numberOfReplies = numberOfReplies;
		this.repliesList = subCommentList;
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

	public void setPercentage(double percentage)
	{
		this.percentage = percentage;
	}

	public int getUpvotes()
	{
		return upVotes;
	}

	public void setUpvotes(int upvotes)
	{
		this.upVotes = upvotes;
	}

	public int getNumberOfReplies()
	{
		return numberOfReplies;
	}

	public void setNumberOfReplies(int numberOfReplies)
	{
		this.numberOfReplies = numberOfReplies;
	}

	public List<CommentData> getRepliesList()
	{
		return repliesList;
	}

	public void setRepliesList(List<CommentData> repliesList)
	{
		this.repliesList = repliesList;
	}
}
