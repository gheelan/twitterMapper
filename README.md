# TwitterMapper
Twitter Stream Mapping Application

   This is a final project I completed for the Object-Oriented Programming course of the Software Development MicroMasters Program offered by the University of British Columbia.
     
   The application uses the Twitter API to retrieve a live stream of tweets. The tweets are then checked against a query submitted by the end-user to determine if they contain certain keywords. In this context the query is a set of keywords and associated "and", "or", and "not" logic entered by the end-user (Ex. "dogs and cats", "peanut and butter or jelly and not bread"). The Observer pattern is implemented between the LiveTwitterSource (Observable) and Query (Observer) classes. If a match is found between the query and a tweet the Twitter source notifies the Query class which then updates the map with a MapMarker representing the tweet.

I've attached a basic UML package diagram as well as some UML class diagrams for vital classes to provide a little more context. 

Here's a little demo of the application in action:

The first keyword entered was "messi" (for the soccer player Lionel Messi who was trending on Twitter at the time), the next set of keywords "the or where" was meant to generate a lot of tweets quickly in order to demonstrate a slightly more complicated example before the screen capture became too large! 

<a href="https://gifyu.com/image/cPNA"><img src="https://s7.gifyu.com/images/TwitterMapperDemoGIF609d873277c320dc.gif" alt="TwitterMapperDemoGIF609d873277c320dc.gif" border="0" /></a>
