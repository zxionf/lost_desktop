package zyx.lost;

public class Utils {
    //计算两点间的直线距离
    public static double calculateDistance(float ox,float oy,float x,float y){
        return Math.sqrt(Math.pow(ox-x, 2)+Math.pow(oy-y, 2));
    }
}
