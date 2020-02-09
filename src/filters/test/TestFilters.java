package filters.test;

import filters.*;
import org.junit.jupiter.api.Test;
import twitter4j.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class TestFilters {
    @Test
    public void testBasic() {
        Filter f = new BasicFilter("fred");
        assertTrue(f.matches(makeStatus("Fred Flintstone")));
        assertTrue(f.matches(makeStatus("fred Flintstone")));
        assertFalse(f.matches(makeStatus("Red Skelton")));
        assertFalse(f.matches(makeStatus("red Skelton")));
    }

    @Test
    public void testNot() {
        Filter f = new NotFilter(new BasicFilter("fred"));
        assertFalse(f.matches(makeStatus("Fred Flintstone")));
        assertFalse(f.matches(makeStatus("fred Flintstone")));
        assertTrue(f.matches(makeStatus("Red Skelton")));
        assertTrue(f.matches(makeStatus("red Skelton")));
    }

    @Test
    public void testAnd(){
        Filter f = new AndFilter(new BasicFilter("corn"), new BasicFilter("cob"));
        assertTrue(f.matches(makeStatus("I like corn on the cob")));
        assertTrue(f.matches(makeStatus("I like Corn on the Cob")));
        assertFalse(f.matches(makeStatus("cob is cool")));
        assertFalse(f.matches(makeStatus("Cob is cool")));
        assertFalse(f.matches(makeStatus("Corn is cool")));
        assertFalse(f.matches(makeStatus("corn is cool")));
    }

    @Test
    public void testAndTerms(){
        Filter f = new AndFilter(new BasicFilter("hi"), new BasicFilter("hello"));
        assertTrue(f.terms().contains("hi"));
        assertTrue(f.terms().contains("hello"));
    }

    @Test
    public void testAndToString(){
        Filter f = new AndFilter(new BasicFilter("hi"), new BasicFilter("hello"));
        assertEquals(f.toString(), "(hi and hello)");
    }

    @Test
    public void testOr(){
        Filter f = new OrFilter(new BasicFilter("chicken"), new BasicFilter("wing"));
        assertTrue(f.matches(makeStatus("A Chicken is a bird")));
        assertTrue(f.matches(makeStatus("A chicken with one Wing")));
        assertTrue(f.matches(makeStatus("grab my wing")));
        assertFalse(f.matches(makeStatus("today is monday")));
        assertFalse(f.matches(makeStatus("Today IS Monday")));

    }

    @Test
    public void testOrTerms(){
        Filter f = new OrFilter(new BasicFilter("to"), new BasicFilter("from"));
        assertTrue(f.terms().contains("to"));
        assertTrue(f.terms().contains("from"));
    }

    @Test
    public void testOrToString(){
        Filter f = new OrFilter(new BasicFilter("to"), new BasicFilter("from"));
        assertEquals(f.toString(), "(to or from)");
    }

    private Status makeStatus(String text) {
        return new Status() {
            @Override
            public Date getCreatedAt() {
                return null;
            }

            @Override
            public long getId() {
                return 0;
            }

            @Override
            public String getText() {
                return text;
            }

            @Override
            public String getSource() {
                return null;
            }

            @Override
            public boolean isTruncated() {
                return false;
            }

            @Override
            public long getInReplyToStatusId() {
                return 0;
            }

            @Override
            public long getInReplyToUserId() {
                return 0;
            }

            @Override
            public String getInReplyToScreenName() {
                return null;
            }

            @Override
            public GeoLocation getGeoLocation() {
                return null;
            }

            @Override
            public Place getPlace() {
                return null;
            }

            @Override
            public boolean isFavorited() {
                return false;
            }

            @Override
            public boolean isRetweeted() {
                return false;
            }

            @Override
            public int getFavoriteCount() {
                return 0;
            }

            @Override
            public User getUser() {
                return null;
            }

            @Override
            public boolean isRetweet() {
                return false;
            }

            @Override
            public Status getRetweetedStatus() {
                return null;
            }

            @Override
            public long[] getContributors() {
                return new long[0];
            }

            @Override
            public int getRetweetCount() {
                return 0;
            }

            @Override
            public boolean isRetweetedByMe() {
                return false;
            }

            @Override
            public long getCurrentUserRetweetId() {
                return 0;
            }

            @Override
            public boolean isPossiblySensitive() {
                return false;
            }

            @Override
            public String getLang() {
                return null;
            }

            @Override
            public Scopes getScopes() {
                return null;
            }

            @Override
            public String[] getWithheldInCountries() {
                return new String[0];
            }

            @Override
            public long getQuotedStatusId() {
                return 0;
            }

            @Override
            public Status getQuotedStatus() {
                return null;
            }

            @Override
            public int compareTo(Status o) {
                return 0;
            }

            @Override
            public UserMentionEntity[] getUserMentionEntities() {
                return new UserMentionEntity[0];
            }

            @Override
            public URLEntity[] getURLEntities() {
                return new URLEntity[0];
            }

            @Override
            public HashtagEntity[] getHashtagEntities() {
                return new HashtagEntity[0];
            }

            @Override
            public MediaEntity[] getMediaEntities() {
                return new MediaEntity[0];
            }

            @Override
            public ExtendedMediaEntity[] getExtendedMediaEntities() {
                return new ExtendedMediaEntity[0];
            }

            @Override
            public SymbolEntity[] getSymbolEntities() {
                return new SymbolEntity[0];
            }

            @Override
            public RateLimitStatus getRateLimitStatus() {
                return null;
            }

            @Override
            public int getAccessLevel() {
                return 0;
            }
        };
    }
}
