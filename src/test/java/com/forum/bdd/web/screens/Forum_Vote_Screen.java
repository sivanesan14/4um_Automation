package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;

public class Forum_Vote_Screen {

	public String voteButton = "(//button[@aria-label='Thumbs Up'])[1]/parent::p";
	WebActions play = new WebActions();

	public Integer parseIntFromText(String text) {
		String digits = text.replaceAll("\\D+", ""); // Removes all non-digits
		int value = Integer.parseInt(digits);
		return value;
	}
}
