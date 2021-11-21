package base.actual;

import nxopen.*;

public class CreateBlock {
	// public static final double PI = 3.14159265358979324;

	public static void main(String[] args) throws Exception {

		try {
			Session theSession = (Session) SessionFactory.get("Session");
           
			System.out.println("获取到 Session" + theSession);
			Part workPart = theSession.parts().work();
			Point3d origin = new Point3d(0.0, 0.0, 0.0);
			nxopen.features.Feature nullFeature = null;
			// **************************************************************************
			// CREATE BLOCK
			nxopen.features.BlockFeatureBuilder newBlock = workPart.features().createBlockFeatureBuilder(nullFeature);
			newBlock.setOriginAndLengths(origin, "50", "80", "100");
			/* nxopen.features.Feature blockFeature = */newBlock.commitFeature();
			newBlock.destroy();

		} catch (Exception e) {
			// System.out.println(e);
		}

	}

}
