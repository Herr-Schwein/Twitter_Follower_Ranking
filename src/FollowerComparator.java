import java.util.Comparator;

public class FollowerComparator implements Comparator<MUser> {
    @Override
    public int compare(MUser a, MUser b) {
        return b.getFollowers() - a.getFollowers();
    }
}
