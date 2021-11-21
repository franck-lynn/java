package base.actual;

import nxopen.ListingWindow;
import nxopen.Session;
import nxopen.SessionFactory;

public class HelloOpen {
    public static void main(String[] args) {
        try {
            Session theSession = (Session) SessionFactory.get("Session");
            ListingWindow  lw  = theSession.listingWindow();
            lw.open();
            lw.writeLine("Hello Open java!");
        } catch (Exception e) {
            // System.out.println(e);
        }
    }
}
// 打包命令
// jar --create --file=../open/application/HelloOpen.jar --main-class=base.actual.HelloOpen --create base/actual/HelloOpen.class
