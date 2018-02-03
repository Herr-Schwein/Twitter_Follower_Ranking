public class MUser {
    private String name;
    private int followers;

    public MUser(String s) {
        name = s;
        followers = 0;
    }

    public void setFollower(int i) {
        followers = i;
    }

    public String getName() {
        return name;
    }

    public int getFollowers() {
        return followers;
    }
}
