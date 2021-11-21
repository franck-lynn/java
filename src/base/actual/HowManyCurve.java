package base.actual;

import java.util.Iterator;

import nxopen.Curve;
import nxopen.CurveCollection;
import nxopen.ListingWindow;
import nxopen.Part;
import nxopen.Session;
import nxopen.SessionFactory;

public class HowManyCurve {
    public static void main(String[] args) {
        try {
            Session theSession = (Session) SessionFactory.get("Session");

            Integer numCurver = 0;
            double curveLength = 0;

            Part workpart = theSession.parts().work();
            ListingWindow lw = theSession.listingWindow();
            lw.open();
            CurveCollection curves = workpart.curves();
            
            Iterator<?> it = curves.iterator();
            
            while(it.hasNext()){
                numCurver ++;
                Curve cur = (Curve) it.next();
                curveLength = cur.getLength();
                lw.writeLine("第"+ numCurver +"条曲线的长度是" + curveLength );
            }
            lw.writeLine("整个零件有"+ numCurver + "条曲线");
            
        } catch (Exception e) {

        }

    }
}
