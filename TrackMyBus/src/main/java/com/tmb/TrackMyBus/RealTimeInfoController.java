// package com.tmb.TrackMyBus;

// // package com.risksafe.risk;

// import com.tmb.TrackMyBus.data.DbCon;
// import com.tmb.TrackMyBus.models.RiskRequest;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

// import javax.net.ssl.HttpsURLConnection;
// import java.io.*;
// import java.net.URL;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.Date;

// @Controller
// public class RealTimeInfoController {
//     private Connection con = null;

//     private boolean saveForFutureRequests(String data, String stopid) {
//         try {
//             con = DbCon.getConnection();
//             if (con == null) {
//                 return false;
//             }
//             String query1 = "INSERT INTO risks(id, path, requestDate, latitude, longitude) values(?, ?, ?, ?, ?)";
//             PreparedStatement statement1 = con.prepareStatement(query1, ResultSet.TYPE_SCROLL_SENSITIVE,
//                     ResultSet.CONCUR_UPDATABLE);
//             String path = DbCon.randomString();
//             statement1.setString(1, path);
//             statement1.setString(2, path + ".txt");
//             statement1.setString(3, new Date().toString());
//             statement1.setString(4, stopid);
//             // statement1.setString(5, longitude);

//             Path outFile = Path.of(System.getProperty("user.home"), ".risks/history", path + ".txt");

//             if (!Files.exists(outFile)) {
//                 Files.createFile(outFile);
//             }

//             FileWriter wr = new FileWriter(outFile.toFile());
//             wr.write(data);

//             wr.close();

//             statement1.executeUpdate();
//         } catch (SQLException sql_ex) {
//             // SQL Exception
//             System.out.println("SQL EXCEPTION " + sql_ex.getMessage());
//             return false;
//         } catch (ClassNotFoundException class_ex) {
//             // Missing Mysql Driver
//             System.out.println("Class Not Found " + class_ex.getMessage());
//             return false;
//         } catch (IOException e) {
//             System.out.println("Input Output Exception " + e.getMessage());
//             return false;
//         }
//         return true;
//     }

//     /**
//      *
//      * @return Directs the request to searchrisk.html
//      */
//     @GetMapping("/welcomePage")
//     public String realTimeInfo() {
//         return "welcomePage";
//     }

//     /**
//      *
//      * @param id Primary key for the requested for risk
//      * @return Response Entity
//      */
//     // @GetMapping("/queryrisk")
//     // public ResponseEntity<String> search(@RequestParam("id") String id) {
//     //     StringBuilder buf = new StringBuilder();

//     //     try {
//     //         con = DbCon.getConnection();
//     //         if (con == null) {
//     //             return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
//     //         }
//     //         String query1 = "SELECT path FROM risks WHERE id=?";
//     //         PreparedStatement statement1 = con.prepareStatement(query1, ResultSet.TYPE_SCROLL_SENSITIVE,
//     //                 ResultSet.CONCUR_UPDATABLE);
//     //         statement1.setString(1, id);

//     //         ResultSet rs = statement1.executeQuery();

//     //         rs.last();
//     //         int count = rs.getRow();
//     //         if (count == 1) {
//     //             rs.first();

//     //             Path path = Path.of(System.getProperty("user.home"), ".risks/history", rs.getString("path"));

//     //             try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())))) {
//     //                 String line;
//     //                 while ((line = r.readLine()) != null) {
//     //                     buf.append(line);
//     //                 }
//     //             } catch (IOException e) {
//     //                 return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
//     //             }
//     //         } else {
//     //             // No record found Response with 404
//     //             return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
//     //         }
//     //     } catch (SQLException sql_ex) {
//     //         // SQL Exception
//     //         return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
//     //     } catch (ClassNotFoundException class_ex) {
//     //         // Missing Mysql Driver
//     //         return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
//     //     }
//     //     return new ResponseEntity<>(buf.toString(), HttpStatus.OK);
//     // }

//     @PostMapping("/trackView")
//     @ResponseBody
//     public ResponseEntity<String> search(@RequestParam("stopid") String stopid) {
//         String s = String.format("https://[rtpiserver]/realtimebusinformation?stopid=[stopid]",stopid);
//         //String token = "NNaqNzhnBVAAraBgsrXg";
//         int status = 100;

//         StringBuilder result = new StringBuilder();
//         try{
//             URL url = new URL(s);
//             HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//             //Set Authorization credentials
//             con.setRequestProperty("Authorization", "Bearer ");

//             BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream()));

//             String line;
//             while((line = r.readLine())!=null) {
//                 result.append(line);
//             }
//             status = con.getResponseCode();

//             //Save risk to database
//             saveForFutureRequests(result.toString(), stopid);

//         } catch (Exception e) {
//             return new ResponseEntity<>(e.toString(), HttpStatus.valueOf(status));
//         }

//         return new ResponseEntity<>(result.toString(), HttpStatus.OK);


// }
// }
