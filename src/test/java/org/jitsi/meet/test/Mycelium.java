//Mycelium.java
// Prejoin test
package org.jitsi.meet.test;

import org.jitsi.meet.test.base.*;
import org.jitsi.meet.test.pageobjects.web.*;
import org.jitsi.meet.test.util.*;
import org.jitsi.meet.test.web.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class Mycelium
    extends WebTestBase
{
    @Test
    public void PreJoinParty()
    {
        JitsiMeetUrl meetingUrl = getJitsiMeetUrl();
        meetingUrl.removeFragmentParam("config.prejoinPageEnabled");
        meetingUrl.appendConfig("config.prejoinPageEnabled=true");
        meetingUrl.appendConfig("config.requireDisplayName=true");

        joinFirstParticipant(meetingUrl);  // base/participants.java
        PreJoinScreen preJoinScreen = getParticipant1().getPreJoinScreen(); // 

        preJoinScreen.waitForLoading();

        WebElement joinButton = preJoinScreen.getJoinButton();

        assertTrue(joinButton.isDisplayed(), "Join button displayed");

        joinButton.click();

        WebElement error = preJoinScreen.getErrorOnJoin();

        assertTrue(error.isDisplayed(), "Error displayed for display name required");

        getParticipant1().hangUp();
    }

}
//<div id="world" tabindex="0"><canvas width="1558" height="1458" style="width: 779px; height: 729px;"></canvas></div>