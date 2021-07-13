Feature: Validate Gmail


@testgmail
Scenario: Validate gmail error message is dispalyed
Given Launch gmail url on browser
When Verify that email field is displayed
Then Click on next button
And Verify the error message Enter an email or phone number is displayed under email field


@testgmail
Scenario: Enter an email and validate password field
Then Enter an email schauhan@gmail.com
When Click on next button


