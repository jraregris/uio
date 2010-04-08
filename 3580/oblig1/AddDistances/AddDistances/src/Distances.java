public class Distances {

  /** Number of radians to a degree */
  static double RAD_PER_DEG = Math.PI/180.0;

  /** The radius of the Earth in km */
  static double R = 6371.0; // km

  /** Return distance in km between two points on the Earth's surface.
   * The coordinates of the points are given as latitude and longitude in
   * radians. Negative is west, resp. south.  */

  public static double distanceRAD(double lat1,double lon1,double lat2,double lon2) {
    return  R * Math.acos(Math.sin(lat1)*Math.sin(lat2) +
			  Math.cos(lat1)*Math.cos(lat2) *
			  Math.cos(lon2-lon1));
  }

  /** Return distance in km between two points on the Earth's surface.
   * The coordinates of the points are given as latitude and longitude in
   * degrees. Negative is west, resp. south.*/
  public static double distanceDEG(double lat1,double lon1,double lat2,double lon2) {
    return distanceRAD(RAD_PER_DEG*lat1,RAD_PER_DEG*lon1,
		       RAD_PER_DEG*lat2,RAD_PER_DEG*lon2);
  }
}