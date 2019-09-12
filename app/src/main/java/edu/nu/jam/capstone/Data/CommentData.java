package edu.nu.jam.capstone.Data;

import java.util.List;

public class CommentData
{
	private String content;
	private double percentage;
	private int upVotes;
	private int numberOfReplies;
	private List<CommentData> repliesList;
	private String parentId;
	private String username;

	public CommentData()
	{
		this.content = null;
		this.percentage = 0.0;
		this.upVotes = 0;
		this.numberOfReplies = 0;
		this.repliesList = null;
		this.parentId = null;
		this.username = null;
	}

	public CommentData(String content, int percentage, int upVotes, int numberOfReplies, List<CommentData> subCommentList, String username)
	{
		this.content = content;
		this.percentage = percentage;
		this.upVotes = upVotes;
		this.numberOfReplies = numberOfReplies;
		this.repliesList = subCommentList;
		this.username = username;
//		this.parentId = parentId;
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

	public String getUsername() {return username;}

	public void setUsername(String username) {this.username = username;}

	public String getParentId() {return parentId;}

	public void setParentId(String parentId) {this.parentId = parentId;}


}
