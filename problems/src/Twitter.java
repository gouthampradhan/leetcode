import java.util.*;

/**
 * Created by gouthamvidyapradhan on 18/03/2017.
 */
public class Twitter
{
    class User
    {
        int id;
        Set<Integer> follow = new HashSet<>();
        Queue<Tweet> tweets = new ArrayDeque<>();
        User(int id)
        {
            this.id = id;
        }
        public void follow(int id)
        {
            follow.add(id);
        }
        public void addTweet(Tweet t)
        {
            if(tweets.size() == 10)
            {
                tweets.poll();
            }
            tweets.offer(t);
        }
        public void unFollow(int id)
        {
            follow.remove(id);
        }

        public Set<Integer> getFollow() {
            return follow;
        }

        public Queue<Tweet> getTweets() {
            return tweets;
        }
    }

    class Tweet
    {
        int id;
        long time;
        Tweet(int id, long time)
        {
            this.id = id;
            this.time = time;
        }
    }

    private Map<Integer, User> userMap;
    private Map<Integer, Tweet> tweetMap;
    private static long tweetCount = 0L;
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.follow(1, 1);
        System.out.println(twitter.getNewsFeed(1));
        /*twitter.follow(2, 1);
        System.out.println(twitter.getNewsFeed(2));
        //twitter.unfollow(2, 1);
        twitter.postTweet(2, 3);
        System.out.println(twitter.getNewsFeed(1));
        System.out.println(twitter.getNewsFeed(2));
        twitter.follow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
        twitter.unfollow(2, 1);
        System.out.println(twitter.getNewsFeed(2));
        System.out.println(twitter.getNewsFeed(1));
        //twitter.getNewsFeed(2);
        */
    }

    /** Initialize your data structure here. */
    public Twitter()
    {
        userMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId)
    {
        User user = userMap.get(userId);
        if(user == null)
        {
            user = new User(userId);
            userMap.put(userId, user);
        }
        user.addTweet(new Tweet(tweetId, tweetCount++));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId)
    {
        User user = userMap.get(userId);
        List<Integer> result = new ArrayList<>();
        if(user == null) return result;
        Set<Integer> follwers = user.getFollow();
        if(follwers != null)
        {
            List<Tweet> tweets = new ArrayList<>();
            tweets.addAll(user.getTweets());
            for(Integer i : follwers)
            {
                User f = userMap.get(i);
                if(f != null)
                {
                    tweets.addAll(f.getTweets());
                }
            }
            Comparator<Tweet> comparator = new Comparator<Tweet>() {
                @Override
                public int compare(Tweet o1, Tweet o2) {
                    return Long.compare(o2.time, o1.time);
                }
            };

            Collections.sort(tweets, comparator);

            for(int i = 0; i < 10; i ++)
            {
                if(i >= tweets.size())
                    break;
                result.add(tweets.get(i).id);
            }
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId)
    {
        if(followerId == followeeId) return;
        User user = userMap.get(followerId);
        if(user == null)
            user = new User(followerId);
        userMap.put(followerId, user);
        if(userMap.get(followeeId) != null)
        {
            user.follow(userMap.get(followeeId).id);
        }
        else
        {
            User newUser = new User(followeeId);
            userMap.put(followeeId, newUser);
            user.follow(userMap.get(followeeId).id);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId)
    {
        User user = userMap.get(followerId);
        if(user != null)
        {
            user.unFollow(followeeId);
        }
    }

}
