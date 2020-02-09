package twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.HashSet;
import java.util.Observer;
import java.util.Set;

/**
 * Encapsulates the connection to Twitter
 *
 * Terms to include in the returned tweets can be set with setFilterTerms
 *
 * Implements Observable - each received tweet is signalled to all observers
 */
public class LiveTwitterSource extends TwitterSource {
    private TwitterStream twitterStream;
    private StatusListener listener;


    public LiveTwitterSource() {
        initializeTwitterStream();
    }

    protected void sync() {
        FilterQuery filter = new FilterQuery();
        // https://stackoverflow.com/questions/21383345/using-multiple-threads-to-get-data-from-twitter-using-twitter4j
        String[] queriesArray = terms.toArray(new String[0]);
        filter.track(queriesArray);

        System.out.println("Syncing live Twitter stream with " + terms);

        twitterStream.filter(filter);
    }

    private void initializeListener() {
        listener = new StatusAdapter() {
            @Override
            public void onStatus(Status status) {
                // This method is called each time a tweet is delivered by the TwitterMapperPackageDiagram API
                if (status.getPlace() != null) {
                    handleTweet(status);
                }
           }
        };
    }

    // Create ConfigurationBuilder and pass in necessary credentials to authorize properly, then create TwitterStream.
    private void initializeTwitterStream() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("yV26cb3Nvx4fyBnT1neQBxaHj")
                .setOAuthConsumerSecret("z7Db4Q79PUalAhWzIKxqlpW83CpBLM1ANSjSRbMEYISqEMBZ2g")
                .setOAuthAccessToken("1155913879584759808-LDJF8ot71eN6SQjr4mpooO8wroagxn")
                .setOAuthAccessTokenSecret("7QyeostEE3nKlq1Qv32InzCtW9GxSPwk7cmA4eltrdwWO");

        // Pass the ConfigurationBuilder in when constructing TwitterStreamFactory.
        twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        initializeListener();
        twitterStream.addListener(listener);
    }

}
