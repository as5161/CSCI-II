public abstract class VotingGroup {
    private int members;

    public VotingGroup(int members){

    }

    public abstract int votesNeeded();

    public static int the (int m){
        return (m/2)+1;
    }

    public boolean passed (int yea){
        if(yea>= votesNeeded()){
           return true;
        }else{
            return false;
        }
    }

    public class Clubs{

    }
}
