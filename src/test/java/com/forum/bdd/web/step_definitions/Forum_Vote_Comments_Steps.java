package com.forum.bdd.web.step_definitions;

import org.testng.asserts.SoftAssert;

import com.forum.bdd.ccl.WebActions;
import com.forum.bdd.integrations.report_utils.ReportManager;
import com.forum.bdd.web.screens.Forum_Vote_Screen;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forum_Vote_Comments_Steps {
	Forum_Vote_Screen vote = new Forum_Vote_Screen();
	WebActions play = new WebActions();
	SoftAssert soft;
	int beforeVote, afterVote, afterUnvote;
	String beforeHiglight, afterHighlight;

	@When("the user votes on the first post in the Home page")
	public void the_user_votes_on_the_first_post_in_the_home_page() throws InterruptedException {
		Thread.sleep(3000);
		soft = new SoftAssert();
		beforeHiglight = play.getAttributeValue(vote.voteButton, "class");
		play.scrollToElement(vote.voteButton, "Vote icon in First Post");
		String text = play.getText(vote.voteButton);
		beforeVote = vote.parseIntFromText(text);
		play.waitForClick(vote.voteButton, 10000, "Vote icon in First Post");
	}

	@Then("the vote icon on that post should be highlighted")
	public void the_vote_icon_on_that_post_should_be_highlighted() throws InterruptedException {
		// click the vote button here
		afterHighlight = play.getAttributeValue(vote.voteButton, "class");
		System.out.println("Class before voting: " + beforeHiglight);
		System.out.println("Class after voting: " + afterHighlight);
		soft.assertFalse(beforeHiglight.equals(afterHighlight),
				"Vote icon did not reflect the highlighted state after clicking. Expected the class to change, but it remained: "
						+ afterHighlight);
	}

	@Then("the vote count for that post should increase by one")
	public void the_vote_count_for_that_post_should_increase_by_one() {
		String text = play.getText(vote.voteButton);
		afterVote = vote.parseIntFromText(text);
		ReportManager.logInfo("Vote count before voting:" + beforeVote);
		ReportManager.logInfo("Vote count after voting:" + afterVote);
		soft.assertTrue(beforeVote < afterVote,
				"Vote count did not increase after voting action. Expected afterVote > beforeVote, but got beforeVote = "
						+ beforeVote + ", afterVote = " + afterVote);
	}

	@When("the user unvote on the first post in the Home page")
	public void the_user_unvote_on_the_first_post_in_the_home_page() throws InterruptedException {
		Thread.sleep(3000);
		play.scrollToElement(vote.voteButton, "Vote icon in First Post");
		String text = play.getText(vote.voteButton);
		beforeVote = vote.parseIntFromText(text);
		play.waitForClick(vote.voteButton, 10000, "Vote icon in First Post");
	}

	@Then("the vote icon on that post should not be hightlighted")
	public void the_vote_icon_on_that_post_should_not_be_hightlighted() {
		beforeHiglight = play.getAttributeValue(vote.voteButton, "class");
		ReportManager.logInfo("Class when highlighted: " + afterHighlight);
		ReportManager.logInfo("Class after unvoting: " + beforeHiglight);
		soft.assertFalse(afterHighlight.equals(beforeHiglight),
				"Vote icon still appears highlighted after unvoting. Expected a class change to indicate the highlight was removed, but class remained: "
						+ beforeHiglight);
	}

	@Then("the vote count for that post should decrease by one")
	public void the_vote_count_for_that_post_should_decrease_by_one() {
		String text = play.getText(vote.voteButton);
		afterUnvote = vote.parseIntFromText(text);
		ReportManager.logInfo("Vote count before unvote: " + afterVote);
		ReportManager.logInfo("Vote count after unvote: " + afterUnvote);
		soft.assertTrue(afterUnvote < afterVote, "Vote count did not decrease after unvoting. Expected less than "
				+ afterVote + ", but found " + afterUnvote);
	}

	@Then("validate all assertions after execution")
	public void validate_all_assertions_after_execution() {
		soft.assertAll();
	}
}
