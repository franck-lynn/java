package base.actual;

import java.util.HashSet;

import nxopen.ListingWindow;
import nxopen.Part;
import nxopen.PartCollection;
import nxopen.Session;
import nxopen.SessionFactory;
import nxopen.TaggedObjectCollection.Iterator;

public class ListParts {
    public static void main(String[] args) {
        try{
            Session NXSession = (Session) SessionFactory.get("Session"); 
            
            ListingWindow  lw  = NXSession.listingWindow();
            lw.open();
            // partList 里面是打开的文件集合
            PartCollection partList = NXSession.parts();
            Iterator itr;
            Part partObj;
            
            HashSet<String> set = new HashSet<String>();
            
            for(itr=partList.iterator(); itr.hasNext();){
                partObj = (Part) itr.next();
                set.add(partObj.name());
            }
            // Parts里看看有什么---> 
            // [固定销, 锥度螺丝, 正反转反刮刀, TCMT16T304, ...]
            lw.writeLine("Parts里看看有什么 --> " + set);
            
        }catch(Exception e){
            //
        }
        
    }
}
// 打包命令
// jar --create --file=../open/application/ListParts.jar --main-class=base.actual.ListParts --create base/actual/ListParts.class