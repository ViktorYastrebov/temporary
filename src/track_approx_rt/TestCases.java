package track_approx_rt;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TestCases {
	public void TrackerTest() {
		
		String testFiles[] = {
					"coords1.txt"
					//,"coords2.txt"
				};
		
		for(int i =0; i < testFiles.length; ++i ) {
			PointsLoader pl = new PointsLoader(testFiles[i]);
			PointTracker pt = new PointTracker();
			
			List<NamedPoints> lst = new ArrayList<NamedPoints>();
			
			int idx = 1;
			for(Point p : pl.getCoords()) {
				NamedPoints np = new NamedPoints();
				np.point = p;
				np.name = Integer.toString(idx);
				++idx;
				
				lst.add(np);
			}

			for(NamedPoints np : lst) {
				pt.add(np);
			}
			
			System.out.println("========================================================");
			
			System.out.println("First scales points :");
			for(NamedPoints p : pt.get1Scale()) {
				System.out.println("name :" + p.name + " x :" + Integer.toString(p.point.x) + 
						           ", y:" + Integer.toString(p.point.y));
			}
			
			System.out.println("Second scales points :");
			for(NamedPoints p : pt.get2Scale()) {
				System.out.println("name :" + p.name + " x :" + Integer.toString(p.point.x) + 
						           ", y:" + Integer.toString(p.point.y));
			}
			
			System.out.println("Third scales points :");
			for(NamedPoints p : pt.get3Scale()) {
				System.out.println("name :" + p.name + " x :" + Integer.toString(p.point.x) + 
						           ", y:" + Integer.toString(p.point.y));
			}
		}
	}
	
	public void PossitionProviderTest( ) {
		PositionProviderNMEA_0183 pp = new PositionProviderNMEA_0183("D5_1_GPS-2018-04-22.txt");
	}
	
	public void GPRMCParserTest () {
		//In the original one it sends data in ASCII (1 byte)
		// not char(UTF-16) as it in Java by default
		GPRMCFileParser p = new GPRMCFileParser("D5_1_GPS-2018-04-22.txt");
		p.process();
	}
}
