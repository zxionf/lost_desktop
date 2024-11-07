package zyx.lost.rule;

public class QuKuaiTools {
    public static int ijToindex(int i,int j){
        if(i==-1&j==-1){
           return 6;
        }else if(i==-1&j==0){
            return 3;
        }else if(i==-1&j==1){
            return 0;
        }else if(i==0&j==-1){
            return 7;
        }else if(i==0&j==0){
            return 4;
        }else if(i==-1&j==1){
            return 1;
        }else if(i==1&j==-1){
            return 8;
        }else if(i==1&j==0){
            return 5;
        }else if(i==1&j==1){
            return 2;
        }else return 9;
    }
    
}
