package connect.qaagility.com.myclass.Data;
/**
 * Created by macpro on 10/13/15.
 */
public class URLSingelton {

    public String Token;
    public String userName;


    public static String loginUrl = "http://66.85.152.27:8080/myMobileClass/authenticateuser";
    public static String liveFeedsUrl = "http://66.85.152.27:8080/myMobileClass/getlivefeed";
    public static String GalleryUrl = "http://66.85.152.27:8080/myMobileClass/eventimages";


    public static URLSingelton my_SingeltonData;

    private URLSingelton() {
        Token = "";
        userName = "";
    }

    public static void ResetAll(){


    }

    public static URLSingelton getMy_SingeltonData_Reference() {
        if (my_SingeltonData == null) {
            my_SingeltonData = new URLSingelton();
        }
        return my_SingeltonData;
    }



}
