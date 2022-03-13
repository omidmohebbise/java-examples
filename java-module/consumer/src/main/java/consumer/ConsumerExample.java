package consumer;


import com.oms.exported.ShareUtilLib;
//compile error because of module policy: import com.oms.notexported.NotShareUtilLib;

public class ConsumerExample {
    public static void main(String[] args) {
        ShareUtilLib.print();
        //NotShareUtilLib notShareUtilLib;
    }
}
