package com.hila.DrawShape;



import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Shape is enum for the various shapes
enum Shape {CIRCLE, POLYGON, LINE, CURVE, CLEAR };
enum Transform {TRANSLATION, ROTATION, SCALING,MIRRORX,MIRRORY,SHEARINGX,SHEARINGY,NORMALIZE,UPLOAD,CLEAR};
//This class is responsible to draw the shapes
public class DrawShape extends JPanel  implements  MouseListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static AddFile file;
	public  Map <String,ArrayList<Point>> map = new HashMap<String,ArrayList<Point>>();
	public static  Shape shape = Shape.CLEAR;
	public static Transform transform = Transform.CLEAR;	
	double delta  = 30;
	double S = 0.5;
	int xMaxWindow = 1000, yMaxWindow = 600;
	Point centerObj;

	List <Point> clicksforTranslation =  new ArrayList <Point>();
	List <Point> clicksforShearingX =  new ArrayList <Point>();
	List <Point> clicksforShearingY =  new ArrayList <Point>();
	/*List <Point> clicksforPoly =  new ArrayList <Point>();
	List <Point> clicksforCircle =  new ArrayList <Point>();
	List <Point> clicksforCurve =  new ArrayList <Point>();
	*/
	//DraswShape constructor is responsible to add Mouse Listener in order to get the user clicks
	DrawShape(){
        super();
        setBackground(Color.WHITE);
        addMouseListener(this);
    }
	
	//This method is responsible to draw the shapes    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);  
    	if (map.size() > 0){
    	  	for (String key : map.keySet()) {
 	        	if (key.startsWith("line"))
 	        	{
 	    	        System.out.println(key + " " + map.get(key));
 	        		int x1 = (int) map.get(key).get(0).getX();
 	        	    int y1 = (int) map.get(key).get(0).getY();
 	        		int x2 = (int)map.get(key).get(1).getX();
 	        		int y2 = (int)map.get(key).get(1).getY();
 	        		drawLine(x1,y1,x2,y2,g);	        		
 	        	}
 	        	if (key.startsWith("circle"))
 	        	{
 	    	        System.out.println(key + " " + map.get(key));
 	        		int x1 = (int)map.get(key).get(0).getX();
 	        		 int y1 = (int)map.get(key).get(0).getY();
 	        		 int x2 = (int)map.get(key).get(1).getX();
 	        		 int y2 = (int)map.get(key).get(1).getY();
 	        		 drawCircle(x1,y1,x2,y2,g);	        		
 	        	}
 	        	if (key.startsWith("curve"))
 	        	{
 	    	        System.out.println(key + " " + map.get(key));
 	        		int x1 = (int) map.get(key).get(0).getX();
 	        		int y1 = (int) map.get(key).get(0).getY();
 	        		int x2 = (int)map.get(key).get(1).getX();
 	        		int y2 = (int)map.get(key).get(1).getY();
 	        		int x3 = (int)map.get(key).get(2).getX();
	        		int y3 = (int)map.get(key).get(2).getY();
	        		int x4 = (int)map.get(key).get(3).getX();
 	        		int y4 = (int)map.get(key).get(3).getY();
 	        		drawCurve(x1,y1,x2,y2,x3,y3,x4,y4,200,g); 	
 	        		}  
    	  		}
    		}
    	}
 	
	public ArrayList<Point> convertMaptoArray(Map <String,ArrayList<Point>> map){
			ArrayList<Point> allPointsOfObject = new ArrayList<Point>();
			for (String key : map.keySet()){
		 	        System.out.println(key + " " + map.get(key));	        
		 	        map.get(key).size();
		 	        for (int index = 0 ; index < map.get(key).size(); index++){
		 	        	allPointsOfObject.add(map.get(key).get(index));
		 	        } 	        
		    	 }
			 return allPointsOfObject;
		};
		
	
		/*Get Min X and Max X */
	    public int xMinObject (Map <String,ArrayList<Point>> map){
	    	int min, x;
	    	 Map.Entry<String,ArrayList<Point>> entry=map.entrySet().iterator().next();
	    	 ArrayList<Point> allPointsOfObject = convertMaptoArray(map);
	    	 min = (int) entry.getValue().get(0).getX();      
	    	 for (int index = 0 ; index < allPointsOfObject.size(); index++){
	    		 x = (int) allPointsOfObject.get(index).getX();
	    		 System.out.println(x);
	    		 if (x < min)
	    			 min = x;    		
	    	 }
	    	 return min;  
	    }
      
	    public int xMaxObject (Map <String,ArrayList<Point>> map){
	    	int max, x;
		   	 Map.Entry<String,ArrayList<Point>> entry=map.entrySet().iterator().next();
	    	 ArrayList<Point> allPointsOfObject = convertMaptoArray(map);
		   	 max = (int) entry.getValue().get(0).getX();   	 
		   	 for (int index = 0 ; index < allPointsOfObject.size(); index++){
	    		 x = (int) allPointsOfObject.get(index).getX();
	    		 System.out.println(x);
	    		 if (x > max)
	    			 max = x;    		
	    	 }
		   	return max;	
	    }
	    /*Get Min Y and Max Y */
	    public int yMinObject (Map <String,ArrayList<Point>> map){
	    	int min, y;
		   	 Map.Entry<String,ArrayList<Point>> entry=map.entrySet().iterator().next();
			 ArrayList<Point> allPointsOfObject = convertMaptoArray(map);
			 min = (int) entry.getValue().get(0).getY();      
			 for (int index = 0 ; index < allPointsOfObject.size(); index++){
				 y = (int) allPointsOfObject.get(index).getY();
				 System.out.println(y);
				 if (y < min)
					 min = y;			
			 }
			 return min; 	
	    }   
	    public int yMaxObject (Map <String,ArrayList<Point>> map){
	     	int max, y;
		   	 Map.Entry<String,ArrayList<Point>> entry=map.entrySet().iterator().next();
			 ArrayList<Point> allPointsOfObject = convertMaptoArray(map);
		   	 max = (int) entry.getValue().get(0).getY();   	 
		   	 for (int index = 0 ; index < allPointsOfObject.size(); index++){
				 y = (int) allPointsOfObject.get(index).getY();
				 System.out.println(y);
				 if (y > max)
					 max = y;
				
			 }
		   	return max;		
	    }
	    /*normaliztion include : Transcation , Scaling, Transcation */
	    public void normalize(){
	    	/*Initaliztion*/
	    	double xMinObject,yMinObject,xMaxObject,yMaxObject;
	    	int x,y;
			convertMaptoArray(this.map);
	    	xMinObject = xMinObject(this.map);
	    	yMinObject = yMinObject(this.map);
	    	xMaxObject = xMaxObject(this.map);
	    	yMaxObject = xMaxObject(this.map);
	       	double part1 = (xMaxWindow/xMaxObject);
	       	double part2 = (yMaxWindow/yMaxObject);
	     	double s = Math.min(part1, part2)*1;
	     	
	     	System.out.println("Map before first translation");
	     	for (String key : map.keySet()) {
		        System.out.println(key + " " + map.get(key));
	     	}
	     	
	    	/*First Translation*/
	    	for (String key : map.keySet()) {
	    		for (int index = 0; index < map.get(key).size(); index++ )
	       		{
	    			x = (int) (map.get(key).get(index).getX() - xMinObject);
	       			y = (int) (map.get(key).get(index).getY() - yMinObject);
	       			map.get(key).set(index, new Point(x,y));
	       		}	        		
	   		}
	    	
	    	System.out.println("Map after Translation");
	     	for (String key : map.keySet()) {
		        System.out.println(key + " " + map.get(key));
	     	}
	    	/*Scaling */
	    	/*x = x*s ; y = y*s */
	    	/*initialize s*/
	     	System.out.println(s);
	    	for (String key : map.keySet()) {
	    		for (int index = 0; index < map.get(key).size(); index++ )
	    		{
	    			x = (int) (map.get(key).get(index).getX()*s*0.6);
	    			y = (int) (map.get(key).get(index).getY()*s*0.6);
	    			map.get(key).set(index, new Point(x,y));
	    		}
	 		}
	    	System.out.println("Map after Scaling");

	     	for (String key : map.keySet()) {
		        System.out.println(key + " " + map.get(key));
    	 }
	    	/*Translation Back*/
	    	/* x = x + 0.1*xMaxWindow ; y = y + 0.1*yMaxWindow */
	    	for (String key : map.keySet()) {
	    		for (int index = 0; index < map.get(key).size(); index++ )
	    		{
	        		x = (int) (map.get(key).get(index).getX() + 0.2*xMaxWindow);
	        		y = (int) (map.get(key).get(index).getY() + 0.2*yMaxWindow);
	    			map.get(key).set(index, new Point(x,y));
	    		}
	 		}   	
	    	
	    	System.out.println("Map after normaliztion");
	    	for (String key : map.keySet()) {
		        System.out.println(key + " " + map.get(key));
	    	}
	    	if (map.size() != 0){
	    		centerObj = new Point();
	    		centerObj.x = (xMaxObject(map)+xMinObject(map))/2;
		    	centerObj.y = (yMaxObject(map)+yMinObject(map))/2;
	    	}    
			repaint();

	    }
	    
	    public void drawMyBoat () {
	        DrawShape.file = new AddFile();
	        DrawShape.file.openFile();
	        this.map =  DrawShape.file.parseFile();
	        normalize();
	    }


    
    //drawLine method implements the algorithm of draw line
    public void drawLine(int x1, int y1, int x2, int y2 ,  Graphics g)  {
    	float dY, dX;
    	float x,y;
    	dX = x2-x1;
    	dY = y2-y1;
    	x = x1;
    	y = y1;
    			
    	float range =  Math.max(Math.abs(dX), Math.abs(dY));
    	dY = dY/range;
    	dX = dX/range;
    	
    	for (int i =0 ; i < range ; i++  ) { 
    		g.drawRect(Math.round(x), Math.round(y),1,1);
    		x = x + dX;
    		y = y + dY;
    	}

       	
    }
    

    //drawSymmetricalPoints method draw the symmetrical points of x and y
    public void drawSymmetricalPoints (int xc, int x, int yc, int y,  Graphics g ){
    	g.drawRect(xc + x, yc + y,1,1);
    	g.drawRect(xc - x, yc + y,1,1);
    	g.drawRect(xc + x, yc - y,1,1);
    	g.drawRect(xc - x, yc - y,1,1);
    	g.drawRect(xc + y, yc + x,1,1);
    	g.drawRect(xc - y, yc + x,1,1);
    	g.drawRect(xc + y, yc - x,1,1);
    	g.drawRect(xc - y, yc - x,1,1);
    }
    

    //drawCircle is method that implements the  circle algorithm 
    public void drawCircle(int xC, int yC, int xP,int yP,  Graphics g)  {
    	float x, y;
    	int partA = xC-xP;
    	int partB= yC-yP;
    	int r = (int) Math.sqrt(Math.pow(partA, 2)+ Math.pow(partB,2));
    	double delta = Math.PI / (2*Math.sqrt(2)*r);
    	    	for (int i = 0; i < (Math.sqrt(2) * r) / 2; i++) {
        	x = (float) (r * Math.cos( i * delta));
        	y = (float) (r * Math.sin( i * delta));
        	drawSymmetricalPoints(Math.round(xC),Math.round(x),Math.round(yC),Math.round(y),g);
    		
    	}
    
    }

    //drawPolygon is method that implements the  polygon algorithm 
    public void drawPolygon (int xC, int yC, int xP, int yP,int ribs, Graphics g){
    
    	int n = ribs; 
    	//delta keep the the original delta , this size of delta will increase in delta size each time.
    	double delta = (2* Math.PI)/ n;
    	double deltaTemp = delta;
    	float x,y,newX,newY;
    	//First Sliding
    	x = xP - xC;
    	y = yP - yC;
    	newX = x;    	
    	newY = y;    	
    	List <Point> vertexs = new ArrayList<Point>();
    	//Follow delta angle rotation n times and write a list of vertices to vertexs list.
    	for (int i = 0; i < n ; i++)
    	{
    		newX =  (int) ((x*Math.cos(delta) - y*Math.sin(delta)));
    		newY =  (int) ((x*Math.sin(delta) + y*Math.cos(delta))); 
    		vertexs.add(new Point(Math.round(newX),Math.round(newY)));    	
    		System.out.println(vertexs.get(i).x+","+vertexs.get(i).y);
    		delta = delta + deltaTemp;
    	}
    	//Sliding back
    	for (int i = 0; i < vertexs.size(); i++ ){
    		vertexs.get(i).x = (int) (vertexs.get(i).getX() + xC);
    		vertexs.get(i).y = (int) (vertexs.get(i).getY() + yC);
    		g.drawRect(vertexs.get(i).x, vertexs.get(i).y ,1,1);
     	}
    	//draw line between the list of the points we got.
    	for (int z = 0 ; z < vertexs.size(); z++){
    		if (z == vertexs.size()-1)
    			drawLine((int) Math.round(vertexs.get(z).getX()), (int)(vertexs.get(z).getY()),(int)(vertexs.get(0).getX()), (int)(vertexs.get(0).getY()),g);
    		else
    			drawLine((int)(vertexs.get(z).getX()), (int)(vertexs.get(z).getY()),(int)(vertexs.get(z+1).getX()), (int)(vertexs.get(z+1).getY()),g);

    		}
           	
    }

    //This method calculates multiplication between Matrixs
	private static double[] matrixMultiply(double[][] bezier, double[] matrixVars) {
		double[] result = {0,0,0,0};
		for (int i = 0; i < bezier.length; i++) {
			for (int j=0; j < matrixVars.length; j++) {
				result[i] += bezier[i][j]*matrixVars[j];
			}
		}
		return result;
    }
	
	
    //drawCurve is method that implements the bezier curve algorithm     
    public void drawCurve (int x1, int y1, int x2, int y2, int x3, int y3 , int x4, int y4,int mylines, Graphics g ){
    	List<Point> points = new ArrayList<Point>();
    	//step is var that describes the  
    	double step = 1/ (double)mylines;
    	//X matrix
    	double[] arrX = {x1,x2,x3,x4};
    	//Y matrix
    	double[] arrY = {y1,y2,y3,y4};
    	//bezier matrix
    	final double [][]bezier = {{-1,3,-3,1},{3,-6,3,0},{-3,3,0,0},{1,0,0,0}};
    	//create the paramters for x and y {a,b,c,d}
    	double [] resultX = matrixMultiply(bezier,arrX);
    	double [] resultY = matrixMultiply(bezier,arrY);
    	//x,y - the new points calculated on the curve
    	double x,y;
    	//calculate points on the curve accroding the Folinm .
		for (double t = 0; t < 1 ; t+=step)
		{
			x =  (double) (resultX[0]*t*t*t +resultX[1]*t*t+ resultX[2]*t + resultX[3]);
    		y =  (double) (resultY[0]*t*t*t +resultY[1]*t*t+ resultY[2]*t + resultY[3]);
	    	points.add(new Point((int)x,(int)y));
    	}
		
		//The curve should end in the (x4,y4) check point
		points.add(new Point (x4,y4));
		
		//draw line between all the points 
	    for (int i = 0; i < points.size() - 1; i++) {
	    	drawLine((int)points.get(i).getX(),(int)points.get(i).getY(), (int)points.get(i+1).getX(),(int)points.get(i+1).getY(),g);
	    }
	  
	    
    }
    /*HELPFUL FUNCTION*/ 
    public static int[] multiply(int[] x, int[][] A) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        int[] y = new int[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += A[i][j] * x[i];
        return y;
    }
    
    public static int[] multiply(int[] x, double[][] A) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        int[] y = new int[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += A[i][j] * x[i];
        return y;
    }
	
	/*Primary Transformations*/
	public void drawTranslation(int Tx,int Ty) {
	    int T[][] = {{1,0,0},{0,1,0},{Tx,Ty,1}};
	    int X[]= {1,1,1};
	    int x,y;
	    int result[] = {};
		for (String key : map.keySet()) {
			for (int index = 0; index < map.get(key).size(); index++ )
			{
	    		/*old x,y*/
				x = (int) (map.get(key).get(index).getX());
	    		y = (int) (map.get(key).get(index).getY());
	    		X[0] = x;
	    		X[1] = y;
	    		X[2] = 1;
	    		/*calculate new x,y*/
	    		result = multiply(X,T);
	    		/*new x,y*/
	    		x = result[0];
	    		y = result[1];
	    		/*Update map*/
	   			map.get(key).set(index, new Point(x,y));
	    		for (int i=0; i < result.length; i++)
	    			System.out.println(X[i]);
			}
		} 
		if (transform == Transform.TRANSLATION){
			repaint();
			clicksforTranslation.clear();
			System.out.println("im heeheheheheheh");
		}
		else
		{
			System.out.println("dsfjhsjFHSKJFHkjsdhfkjsdhf");
		}
		System.out.println("after drawtransform");
		for (String key : map.keySet()) {
	        System.out.println(key + " " + map.get(key));
		}
	}
	
	public void drawRotation( int Xc,int Yc,double delta) {
		int x,y ;
		for (String key : map.keySet()) {
			for (int index = 0; index < map.get(key).size(); index++)
			{
				x = (int) (Xc + (map.get(key).get(index).getX() - Xc)*Math.cos(delta) - (map.get(key).get(index).getY()- Yc)*Math.sin(delta));	
				y = (int) (Yc + ((map.get(key).get(index).getY() - Yc)*Math.cos(delta)) + (map.get(key).get(index).getX()-Xc)*Math.sin(delta));
	    		map.get(key).set(index, new Point(x,y));
			}
		}
		repaint();
		
	};
	/*Scaling around Xc,Yc*/
	public void drawSacling(int Xc, int Yc, double S) {
		int x,y;
		double Sx = S,Sy =S;
	    double sMatrix[][] = {{Sx,0,0},{0,Sy,0},{Xc*(1-Sx),Yc*(1-Sy),1}};
	    int X[] = {1,1,1};
	    int result[] = {};
		/*Scaling*/
		/*X = Xcenter+S(X-Xc) ; Y= Ycenter+S(Y-Yc)*/
		for (String key : map.keySet()) {
			for (int index = 0; index < map.get(key).size(); index++)
			{	
				X[0] = (int) map.get(key).get(index).getX();
				X[1] = (int) map.get(key).get(index).getY();
				/*Multiply Matrix*/
				result = multiply (X,sMatrix);
				System.out.println("new result");
				System.out.println("result = "+result[0]+","+result[1]+","+result[2]);
				x = result[0];
				y = result[1];
	    		map.get(key).set(index, new Point(x,y));
			}		
		}
		repaint();
	};

	/*This method is sheraing the boat around axis X*/
	/*X = X + ay*/
	public void drawShearingByX(double deltaXMouse) {		
		int x,y;
		double a;
		a = deltaXMouse / 600;
		for (String key : map.keySet()) {
			for (int index = 0; index < map.get(key).size(); index++)
			{					
				y = (int) map.get(key).get(index).getY();
				x = (int) map.get(key).get(index).getX();
				x = (int) (x +a*(yMaxObject(map) - y));
				map.get(key).set(index, new Point(x,y));
			}		
		}
		repaint();
		System.out.println(clicksforShearingX);
		clicksforShearingX.clear();
	};

	/*This method is sheraing the boat around axis X*/
	/*X = X + ay*/
	public void drawShearingByY(double deltaXMouse) {		
		int x,y;
		double b;
		b = deltaXMouse / 600;
		System.out.println("A=" + b);
		System.out.println("BEFORE SHEARINGBYX");
		for (String key : map.keySet()) {
	        System.out.println(key + " " + map.get(key));
		}
		for (String key : map.keySet()) {
			for (int index = 0; index < map.get(key).size(); index++)
			{					
				x = (int) map.get(key).get(index).getX();
				y = (int) map.get(key).get(index).getY();
				y = (int) (y +b*(xMaxObject(map) - x));
			//	x = x +a*( y - yMaxObject(map) );
	    		map.get(key).set(index, new Point(x,y));
			}		
		}
		System.out.println("AFTER SHEARINGBYX");
		for (String key : map.keySet()) {
	        System.out.println(key + " " + map.get(key));
		}
		repaint();
		System.out.println(clicksforShearingY);
		clicksforShearingY.clear();
	};

	/*Secondary Transformations*/
	public void drawMirrorX() {
		int MX[][] = {{1,0,0},{0,-1,0},{0,0,1}};
		int X[] = {1,1,1};
		int result[] = {};
		int x,y;
		drawTranslation((int)(-xMaxWindow/2),(int)(-yMaxWindow/2));
		for (String key : map.keySet()) {
			for (int index = 0; index < map.get(key).size(); index++)
			{					
				X[0] = (int) map.get(key).get(index).getX();
				X[1] = (int) map.get(key).get(index).getY();
				/*Multiply Matrix*/
				result = multiply (X,MX);
				x = result[0];
				y = result[1];
	    		map.get(key).set(index, new Point(x,y));
			}		
		}
		drawTranslation((int)(xMaxWindow/2),(int)(yMaxWindow/2));
		repaint();
	};
	public void drawMirrorY() {
		int MY[][] = {{-1,0,0},{0,1,0},{0,0,1}};
		int X[] = {1,1,1};
		int result[] = {};
		int x,y;
		drawTranslation((int)(-xMaxWindow/2),(int)(-yMaxWindow/2));
		for (String key : map.keySet()) {
			for (int index = 0; index < map.get(key).size(); index++)
			{					
				X[0] = (int) map.get(key).get(index).getX();
				X[1] = (int) map.get(key).get(index).getY();
				/*Multiply Matrix*/
				result = multiply (X,MY);
				x = result[0];
				y = result[1];
	    		map.get(key).set(index, new Point(x,y));
			}		
		}
		drawTranslation((int)(xMaxWindow/2),(int)(yMaxWindow/2));
		repaint();
	};
	
	
	
    
    //Override the methods of MouseListenr Abstract class .(implements only the method: mousePressed)
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Point point = new Point();
		point.x = e.getX();
		point.y = e.getY();
		System.out.println("Clicked EVENT");
		System.out.println("point"+point);
		switch (transform){
		case TRANSLATION:
			clicksforTranslation.add(new Point(point.x,point.y));
			if (clicksforTranslation.size() == 2){
				 int Tx = (int) (clicksforTranslation.get(0).getX() - clicksforTranslation.get(1).getX());
				 int Ty = (int) (clicksforTranslation.get(0).getY() - clicksforTranslation.get(1).getY());
				drawTranslation(-Tx,-Ty);
			}
			break;
		case ROTATION:	
			drawRotation(point.x,point.y,getDelta());	
			break;
		case SCALING:	
			System.out.println("S="+S);
			drawSacling(point.x,point.y,getS());			
			break;
		case SHEARINGX:	
			clicksforShearingX.add(new Point(point.x,point.y));
			if (clicksforShearingX.size() >= 2){
			     System.out.println("CHECK DELTA = "+(int)(clicksforShearingX.get(1).getX()-clicksforShearingX.get(0).getX()));
				 drawShearingByX((clicksforShearingX.get(1).getX()-clicksforShearingX.get(0).getX()));
			}
			break;
		case SHEARINGY:	
			clicksforShearingY.add(new Point(point.x,point.y));
			if (clicksforShearingY.size() >= 2){
			     System.out.println("CHECK DELTA = "+(int)(clicksforShearingY.get(1).getX()-clicksforShearingY.get(0).getX()));
				 drawShearingByY((clicksforShearingY.get(1).getY()-clicksforShearingY.get(0).getY()));
			}
			break;
		case MIRRORX:	
			drawMirrorX();
			break;
		case MIRRORY:	
			drawMirrorY();
			break;
		default:
			break;	
		};	
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Map<String, ArrayList<Point>> getMap() {
		return map;
	}
	public void setMap(Map<String, ArrayList<Point>> map) {
		this.map = map;
		
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}
	

	public int getxMaxWindow() {
		return xMaxWindow;
	}

	public void setxMaxWindow(int xMaxWindow) {
		this.xMaxWindow = xMaxWindow;
	}

	public int getyMaxWindow() {
		return yMaxWindow;
	}

	public void setyMaxWindow(int yMaxWindow) {
		this.yMaxWindow = yMaxWindow;
	}
	public double getS() {
		return S;
	}

	public void setS(double s) {
		S = s;
	}



}