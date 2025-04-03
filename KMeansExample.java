package evochecker.seeding.example;

import org.apache.commons.math3.ml.distance.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;


public class KMeansExample {

	// wrapper class
	public static class LocationWrapper implements Clusterable {
	    private double[] points;
	    private Location location;
	
	    public LocationWrapper(Location location) {
	        this.location = location;
	        this.points = new double[] { location.getX(), location.getY()};
	    }
	
	    public Location getLocation() {
	        return location;
	    }
	
	    public double[] getPoint() {
	        return points;
	    }
	}
	
	public static void main(String[] args) {
		// we have a list of our locations we want to cluster. create a
		List<Location> locations = new ArrayList<Location>();
		
		// add some locations
		locations.add(new Location(1.0, 2.0));
		locations.add(new Location(2.0, 3.0));
		locations.add(new Location(3.0, 4.0));
//		locations.add(new Location(4.0, 5.0));
		locations.add(new Location(5.0, 6.0));
		locations.add(new Location(6.0, 7.0));
		locations.add(new Location(7.0, 8.0));
//		locations.add(new Location(8.0, 9.0));
		locations.add(new Location(9.0, 10.0));
		locations.add(new Location(10.0, 11.0));
		locations.add(new Location(11.0, 12.0));
		locations.add(new Location(12.0, 13.0));
		
		List<LocationWrapper> clusterInput = new ArrayList<LocationWrapper>(locations.size());
		for (Location location : locations)
		    clusterInput.add(new LocationWrapper(location));
		
		// initialize a new clustering algorithm.
		// we did not specify a distance measure; the default (euclidean distance) is used.
		int numClusters = 3;
		int numIterations = 100;
		KMeansPlusPlusClusterer<LocationWrapper> clusterer = new KMeansPlusPlusClusterer<LocationWrapper>(numClusters, numIterations);
		List<CentroidCluster<LocationWrapper>> clusterResults = clusterer.cluster(clusterInput);
		

		// clustering:
		for (int i=0; i<clusterResults.size(); i++) {
			System.out.println("Cluster " + i);
		    // get centroid
	    	Clusterable centroid = clusterResults.get(i).getCenter();
	    	System.out.println("Centroid: " + centroid.getPoint()[0] + ", " + centroid.getPoint()[1]);
		    
		    for (LocationWrapper locationWrapper : clusterResults.get(i).getPoints()) {
		    	Location l = locationWrapper.getLocation();
		    	// get distance to centroid
		    	DistanceMeasure dm = new EuclideanDistance();
		    	double distance = dm.compute(locationWrapper.getPoint(), centroid.getPoint());
		    	
		    	System.out.println("  -Location: " + l.getX() + ", " + l.getY() + "  -Distance to centroid: " + distance);
		    	
		    	}
		    System.out.println();
		}
		
		
		// sampling: get K points, one for each cluster, each closest to centroid
		List<LocationWrapper> closestPoints = new ArrayList<LocationWrapper>(clusterResults.size());
		
		for (int i=0; i<clusterResults.size(); i++) {
		    // get centroid
	    	Clusterable centroid = clusterResults.get(i).getCenter();
	    	LocationWrapper closest = null;
	    	System.out.println("Centroid: " + centroid.getPoint()[0] + ", " + centroid.getPoint()[1]);
		    
	    	Double minDistance = Double.MAX_VALUE;
		    for (LocationWrapper locationWrapper : clusterResults.get(i).getPoints()) {
		    	// get distance to centroid
		    	DistanceMeasure dm = new EuclideanDistance();
		    	double distance = dm.compute(locationWrapper.getPoint(), centroid.getPoint());
		    	
		    	if (distance < minDistance) {
		    		minDistance = distance;
		    		closest = locationWrapper;
		    	}
		    }
		    closestPoints.add(closest);
		    
		    Location l = closestPoints.get(i).getLocation();
	    	System.out.println(" -Closest point to centroid: " + l.getX() + ", " + l.getY());
		}
	}

}