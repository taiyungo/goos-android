package com.jooyunghan.auctionsniper.test;

import com.jooyunghan.auctionsniper.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

public class AuctionSniperEndToEndTest extends ActivityInstrumentationTestCase2<MainActivity> {
	private FakeAuctionServer auction;
	private ApplicationRunner application;

	public AuctionSniperEndToEndTest() {
		super(MainActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		auction = new FakeAuctionServer("item-54321");
		application = new ApplicationRunner(getInstrumentation(), getActivity());
	}

	public void testSniperJoinsAuctionUntilAuctionCloses() throws Exception {
		auction.startSellingItem();
		application.startBiddingIn(auction);
		auction.hasReceivedJoinRequestFromSniper();
		auction.announceClosed();
		application.showsSniperHasLostAuction();
	}

	@Override
	public void tearDown() throws Exception {
		auction.stop();
		application.stop();
		super.tearDown();
	}
}
