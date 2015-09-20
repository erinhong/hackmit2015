import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class test {
        
    public static String httpPost(String urlStr, String username, String password, String[] paramName,
              String[] paramVal) throws Exception {
              URL url = new URL(urlStr);
              String source = username + ":" + password;
              byte[] encoding = Base64.encodeBase64(source.getBytes());
              String b64Auth = new String(encoding);
              
              HttpURLConnection conn =
                  (HttpURLConnection) url.openConnection();
              conn.setRequestMethod("POST");
              conn.setDoOutput(true);
              conn.setDoInput(true);
              conn.setUseCaches(false);
              conn.setAllowUserInteraction(false);
//              conn.setRequestProperty("Content-Type",
//                  "application/x-www-form-urlencoded");
              conn.setRequestProperty("Authorization", "Basic " + b64Auth);

              // Create the form content
              OutputStream out = conn.getOutputStream();
              Writer writer = new OutputStreamWriter(out, "UTF-8");
              for (int i = 0; i < paramName.length; i++) {
                writer.write(paramName[i]);
                writer.write("=");
                writer.write(URLEncoder.encode(paramVal[i], "UTF-8"));
                writer.write("&");
              }
              writer.close();
              out.close();

              if (conn.getResponseCode() != 200) {
                throw new IOException(conn.getResponseMessage());
              }

              // Buffer the result into a string
              BufferedReader rd = new BufferedReader(
                  new InputStreamReader(conn.getInputStream()));
              StringBuilder sb = new StringBuilder();
              String line;
              while ((line = rd.readLine()) != null) {
                sb.append(line);
              }
              rd.close();

              conn.disconnect();
              return sb.toString();
            }

    public static String getFlightID(String flightCode, int timeAndDate) throws Exception{
        
        final String USERNAME = "cjh18";
        final String API_KEY = "e42a74e4d862aca041fc9248d3cdb07192d9e4e3";       
        final String FXML_URL = "http://flightxml.flightaware.com/json/FlightXML2/";
        
        String fxmlFlightID = "GetFlightID";
        //Inputs: ident (String) - requested tail number
        //        departureTime (int) - time and date of the desired flight, UNIX epoch seconds since 1970
        //Outputs: String - returned faFlightID
        
        String fxmlRegisterAlertEndpoint = "RegisterAlertEndpoint";
        //Inputs: address (String) - URL of endpoint
        //        format_type (String) - Must be "json/post"
        //Output: int - returns 1 on success
        
        String fxmlSetAlert = "SetAlert";
        //Inputs: alert_id (int) - alert_id of an existing alert to update. Specify 0 or "" to create a new alert
        //        ident (String) - OPTIONAL ident or faFlightID of flight
        //        origin (String) - OPTIONAL origin airport code
        //        destination (String) - OPTIONAL destination airport code
        //        aircrafttype (String) - OPTIONAL aircraft type
        //        date_start (int) - OPTIONAL starting date of alert (in epoch seconds, will be rounded to whole day)
        //        date_end (int) - OPTIONAL ending date of alert (in epoch seconds, will be rounded to whole day)
        //        channels (String) - list of channels and event types (16 is default. Tcl-style string list that specifies the target channel ID
        //                                                                                                          and the triggering event types)
        //        enabled (boolean) - whether the alert should be enabled or disabled (if missing, default is true)
        //        max_weekly (int) - reject the new alert if the estimated number of alerts per week exceeds this amount
        //Output: int - returns non-zero on success
        
        String fxmlGetAlerts = "GetAlerts";
        //Inputs: none
        //Output: FlightAlertListing - all defined alerts by the user
        
        //-------------------------------------------------------------------------------------------------------------------------
        //Look up faFlightID, a unique identifier assigned by FlightAware as a way to permanently identify a flight
        //TODO Convert timeAndDate to epoch seconds from 1970
        String fxmlFlightID_URL = FXML_URL+fxmlFlightID;
        String[] fxmlFlightID_paramName = new String[] {"ident","departureTime"};
        String[] fxmlFlightID_paramVal = new String[] {flightCode, String.valueOf(timeAndDate)};
        String faFlightID = httpPost(fxmlFlightID_URL, USERNAME, API_KEY, fxmlFlightID_paramName, fxmlFlightID_paramVal);
        System.out.println(faFlightID);
        return faFlightID;
        //-------------------------------------------------------------------------------------------------------------------------        
        //Get FlightInfoEx
//        String fxmlFlightInfoEx_URL = FXML_URL+"FlightInfoEx";
//        String[] fxmlFlightInfoEx_paramName = new String[] {"ident", "howMany", "offset"};
//        String[] fxmlFlightInfoEx_paramVal = new String[] {flightCode, "1", "0"};
//        String flightInfoExReturnVal = httpPost(fxmlFlightInfoEx_URL, USERNAME, API_KEY, fxmlFlightInfoEx_paramName, fxmlFlightInfoEx_paramVal);        

        //-------------------------------------------------------------------------------------------------------------------------        
        //Get alerts
        
//        String fxmlRegisterAlertEndpoint_URL = FXML_URL+fxmlRegisterAlertEndpoint;
//        String[] fxmlRegisterAlertEndpoint_paramName = new String [] {"address","format_type"};
//        String[] fxmlRegisterAlertEndpoint_paramVal = new String [] {"","json/post"};
//        String registrationReturnVal = httpPost(fxmlRegisterAlertEndpoint_URL, USERNAME, API_KEY, fxmlRegisterAlertEndpoint_paramName, fxmlRegisterAlertEndpoint_paramVal);
//        
//        String fxmlSetAlert_URL = FXML_URL+fxmlSetAlert;
//        String[] fxmlSetAlert_paramName = new String[]{"alert_id", "ident", "channels", "enabled", "max_weekly"};
//        String[] fxmlSetAlert_paramVal = new String[]{"", faFlightID, "{16 e_arrival}", "TRUE", "5"};
//        String setAlertReturnVal = httpPost(fxmlSetAlert_URL, USERNAME, API_KEY, fxmlSetAlert_paramName, fxmlSetAlert_paramVal);
//
//        String fxmlGetAlerts_URL = FXML_URL+fxmlGetAlerts;
//        String[] fxmlGetAlerts_paramName = new String[]{};
//        String[] fxmlGetAlerts_paramVal = new String[]{};
//        String getAlertReturnVal = httpPost(fxmlGetAlerts_URL, USERNAME, API_KEY, fxmlGetAlerts_paramName, fxmlGetAlerts_paramVal);
//        
//        return getAlertReturnVal;
        
//        return flightInfoExReturnVal;
        
    }
    
    public static String getArrival(String query_param) {
        final String USERNAME = "cjh18";
        final String API_KEY = "e42a74e4d862aca041fc9248d3cdb07192d9e4e3";       
        final String FXML_URL = "http://flightxml.flightaware.com/json/FlightXML2/SearchBirdseyeInFlight";
        
        try {
			String result = httpPost(FXML_URL, USERNAME, API_KEY, new String[] {"query, howMany, offset"}, new String[] {query_param, "5", "0"});
			JSONObject json = new JSONObject(result);
			JSONArray birdsEye = json.getJSONObject("SearchBirdseyeInFlightResult").getJSONArray("aircraft");
			for (int index = 0; index < birdsEye.length(); index++) {
				JSONObject everyAircraft = (JSONObject) birdsEye.get(index);
				System.out.println("arrival is" + everyAircraft.getInt("arrivalTime"));
			}
			
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "boo";
    }
    
    public static void main(String[] args) throws Exception{        
        String res = getFlightID("IFL531", 1442728860);
        System.out.println(res);
        
        String poop = getArrival("{ident=IFL531}");
//        System.out.println(poop);
    }
}