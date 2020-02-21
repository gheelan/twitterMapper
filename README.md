# twitterMapper
Twitter Stream Mapping Application

     This is a final project I completed for the Object-Oriented Programming course of the Software Development MicroMasters Program offered by the University of British Columbia.
     
     The application uses the Twitter API to retrieve a live stream of tweets. The tweets are then checked against a query submitted by the end-user to determine if they contain certain keywords. In this context the query is a set of keywords and associated "and", "or", and "not" logic entered by the end-user (Ex. "dogs and cats", "peanut and butter or jelly and not bread"). The Observer pattern is implemented between the LiveTwitterSource (Observable) and Query (Observer) classes. If a match is found between the query and a tweet the Twitter source notifies the Query class which then updates the map with a MapMarker representing the tweet.

