package track_approx_rt;

import java.util.*;
import java.awt.Point;

public class PointTracker {
	private NamedList points;
	private NamedList scale1;
	private NamedList scale2;
	private NamedList scale3;
	
	public PointTracker() 
	{
		points = new NamedList();
		points.lst = new ArrayList<NamedPoints>();
		points.name = "default";
		
		scale1 = new NamedList();
		scale1.lst = new ArrayList<NamedPoints>();
		scale1.name = "scale1";
		
		scale2 = new NamedList();
 		scale2.lst = new ArrayList<NamedPoints>();
 		scale2.name = "scale2";
 		
 		scale3 = new NamedList();
		scale3.lst = new ArrayList<NamedPoints>();
		scale3.name = "scale3";
	}
	
	private Double calcSquare(NamedPoints p1, NamedPoints p2, NamedPoints p3) {
		double rt = 0.5 * Math.abs( 
				( (p1.point.getX() - p3.point.getX()) * (p2.point.getY() - p3.point.getY()) -
				( (p1.point.getY() - p3.point.getY()) * (p2.point.getX() - p3.point.getX())) )	);
		return new Double(rt);
	}

	private void addHelper(NamedList lst, NamedPoints p,
			double scale, NamedList toIns) {
		if(lst.lst.size() > 1) {
			NamedPoints p1 = lst.lst.get(lst.lst.size() - 2);
			NamedPoints p2 = lst.lst.get(lst.lst.size() - 1);
			Double s = calcSquare(p1, p2, p);

			System.out.println("p1 : " + p1.name + ", p2 :" + p2.name + ", p3 : " + p.name +
					           ", sqrt :" + Double.toString(s.doubleValue()));

			if(scale < s.doubleValue()) {
				System.out.println("scale :" + Double.toString(scale) + ", lst :" + lst.name +
						", toIns :" + toIns.name);
				toIns.lst.add(p);
			}
		}
	}
	
	private double getDistance(NamedPoints p1, NamedPoints p2) {
		double res = Math.sqrt(
				Math.pow(p2.point.x - p1.point.x, 2.0) +
				Math.pow(p2.point.y - p1.point.y, 2.0)
				);
		return res;
	}
	
	private void scaledPut(NamedList scaledLst, NamedPoints p ,double scale) {
		NamedPoints np = scaledLst.lst.get(scaledLst.lst.size() - 1);
		double ret = getDistance(np, p);
		System.out.println("scaledPut, ret :" + Double.toString(ret));

		if(ret > scale) {
			System.out.println("List name :" + scaledLst.name + ", point name :" + p.name);
			scaledLst.lst.add(p);
		}
	}
	
	public void add(NamedPoints p) {
		if(points.lst.isEmpty()) {
			points.lst.add(p);
			scale1.lst.add(p);
			scale2.lst.add(p);
			scale3.lst.add(p);
			return;
		}
		
		points.lst.add(p);
		scaledPut(scale1, p, 4.0);
		scaledPut(scale2, p, 8.0);
		scaledPut(scale3, p, 12.0);
	}
	
	private List<Point> getScaled(List<NamedPoints> lst) {
		List<Point> res = new ArrayList<>();
		for(NamedPoints np : lst) {
			res.add(np.point);
		}
		return res;
	}
	
	public List<NamedPoints> get1Scale() {
		return scale1.lst;
	}
	
	public List<NamedPoints> get2Scale() {
		return scale2.lst;
	}
	
	public List<NamedPoints> get3Scale() {
		return scale3.lst;
	}
}
