package base.actual;

// import nxopen.Expression;

import nxopen.Part;
import nxopen.ListingWindow;
import nxopen.Session;
import nxopen.SessionFactory;
// import nxopen.features.ConeBuilder;

public class CreateCone {
    
    public static void main(String[] args) {
        try {
            // Session代表NX会话
            Session theSession = (Session) SessionFactory.get("Session");
            ListingWindow  lw  = theSession.listingWindow();
            lw.open();
            
            // 在会话中调用部件集合,再在零件集合中调用当前工作部件
            
           
            Part workPart = theSession.parts().work();
            lw.writeLine("会话里有什么?====>  " + workPart.fullPath());
            
            // // 创建一个圆锥体的特征,得到的是一个圆锥体的实例
            // ConeBuilder builder = workPart.features().createConeBuilder(null);
            // // 设置圆锥体实例,类型采用直径和高度
            // builder.setType(ConeBuilder.Types.DIAMETERS_AND_HEIGHT);
            // // 为直径和高度赋值,
            // // 获取默认的直径值
            // Expression diameter = builder.baseDiameter();
            // // 获取默认的高度值
            // Expression height = builder.height();
            // diameter.setValue(40.0);
            // height.setValue(100.0);

            // builder.commit();
            lw.writeLine("Hello Open java!");
            System.out.println("会话里有什么?====>  " );
        } catch (Exception e) {
            System.out.println();
        }

    }
    
}
// 打包命令
// jar --create --file=../open/application/cone.jar --main-class=base.actual.CreateCone --create base/actual/CreateCone.class