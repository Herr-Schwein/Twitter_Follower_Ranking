import twitter4j.*;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, TwitterException {
        // write your code here


        final int BLOCK_SIZE = 100;

        ArrayList<MUser> users = new ArrayList<>();
        ArrayList<String[]> nameBlocks = new ArrayList<>();


        Scanner scanner = new Scanner(new File("vnps.csv"));
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            String s = scanner.next();

            if (s.equals("0") || s.equals("\r\n")) continue;
            MUser mu = new MUser(s.substring(0, s.length() - 2));
            users.add(mu);

        }
        scanner.close();

        int numOfBlock = users.size() / BLOCK_SIZE + 1;


        for (int i = 0; i < numOfBlock; i++) {
            String[] names;
            if (i != numOfBlock - 1)
                names = new String[BLOCK_SIZE];
            else
                names = new String[users.size() % BLOCK_SIZE];
            for (int j = 0; j < BLOCK_SIZE; j++) {
                if (i * BLOCK_SIZE + j < users.size())
                    names[j] = users.get(i * BLOCK_SIZE + j).getName();
                else
                    break;
            }
            nameBlocks.add(names);
        }


        TwitterFactory factory = new TwitterFactory();
        Twitter twitter = factory.getInstance();
        for (int i = 0; i < nameBlocks.size(); i++) {
            String[] names = nameBlocks.get(i);
            ResponseList<User> userList = twitter.lookupUsers(names);


            for (int j = 0; j < userList.size(); ++j) {
                User user = userList.get(j);
                if (i * BLOCK_SIZE + j < users.size()) {
                    users.get(i * BLOCK_SIZE + j).setFollower(user.getFollowersCount());
                    System.out.println(i * BLOCK_SIZE + j + 1 + " out of " + users.size() + " @" + user.getScreenName() + " - " + user.getFollowersCount());
                }
            }
        }

        Collections.sort(users, new FollowerComparator());

        System.out.println("************************");
        System.out.println("RESULT");
        System.out.println("************************");

        for (MUser mu : users) {
            System.out.println(mu.getName() + " has " + mu.getFollowers() + " followers.");
        }
    }
}







