package com.hila.DrawShape;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddFile {

	private BufferedReader reader;
	
	public void openFile() {
		try{
			reader = new BufferedReader(new FileReader("Shapes.txt"));
			System.out.println("open succssefully");
		}
		catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	public Point parsePointsFromline (String line){
		 String[] pointsFromFile = line.split(",");
	      int x,y;
	      System.out.println(line);
	      x = Integer.parseInt(pointsFromFile[1]);
	      y = Integer.parseInt(pointsFromFile[2]);
	      Point p = new Point(x,y) ;
	      return p;
	}

	public Map <String,ArrayList<Point>> parseFile () {
		Map <String,ArrayList<Point>> map = new HashMap<String,ArrayList<Point>>();
		 try
		  {
			//reader = new BufferedReader(new FileReader("Shapes.txt"));
		    String line;
		    int index=1;
		    while ((line = reader.readLine()) != null)
		    {
		      
		      String name = "null";
		      System.out.println(line);
		      String[] shape = line.split(":");
	    	  name = shape[0];  		      
		      switch (name)
		      {
		      case "line":
				  ArrayList<Point> pointsOfLine = new ArrayList<Point>();
		    	  for (int i = 0; i < 2; i ++){
			    	  line = reader.readLine();
			    	  Point p = parsePointsFromline(line);
			    	  pointsOfLine.add(p);	  
		    	  }
		    	  map.put(name+index, pointsOfLine);
		    	  break;
		      case "circle":
			      ArrayList<Point> pointsOfCircle = new ArrayList<Point>();
		    	  for (int i = 0; i < 2; i ++){
			    	  line = reader.readLine();
			    	  Point p = parsePointsFromline(line);
			    	  pointsOfCircle.add(p);	  
		    	  }
		    	  map.put(name+index, pointsOfCircle);
		    	  break;
		      case "curve":
		    	  ArrayList<Point> pointsOfCurve = new ArrayList<Point>();
		    	  for (int i = 0; i < 4; i ++){
			    	  line = reader.readLine();
			    	  Point p1 = parsePointsFromline(line);
			    	  pointsOfCurve.add(p1);	
		    	  }
		    	  map.put(name+index, pointsOfCurve);
		    	  break;
		      default:
		    	  break;
		      }
		    	index++;
		    }
	
		     //close the file
		     reader.close();
		     //Print the Map
	    	 for (String key : map.keySet()) {
			        System.out.println(key + " " + map.get(key));
	    	 }
		  
		  }		
		  catch (Exception e)
		  {
		    System.err.format("Exception occurred trying to read '%s'.", "Shapes.txt");
		    e.printStackTrace();
		  }
    	 return map;
	}
}
