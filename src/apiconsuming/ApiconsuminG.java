/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apiconsuming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author daniel.angulo
 */
public class ApiconsuminG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
         POSTRequest();
         
    }
    
    public static void MyGETRequest() throws IOException {
    URL urlForGetRequest = new URL("http://localhost:4000/Clientes");
    String readLine = null;
    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
    conection.setRequestMethod("GET");
    int responseCode = conection.getResponseCode();


    if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(conection.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((readLine = in .readLine()) != null) {
            response.append(readLine);
        } in .close();
        // print result
        System.out.println("JSON String Result " + response.toString());
        //GetAndPost.POSTRequest(response.toString());
    } else {
        System.out.println("GET NOT WORKED");
    }

}
 public static void POSTRequest() throws IOException {
    final String POST_PARAMS = "{\n" + "\"Cliente_ID\": 10051,\r\n" +
        "    \"Nombre_Usuario\": 101,\r\n" +
        "    \"Contraseña\": \"danielxhf\",\r\n" +
        "    \"Nombre\": \"Daniel\",\r\n" +
        "    \"Apellidos\": \"Rivas Angulo\",\r\n" +
        "    \"Correo_Electronico\": \"dany-rivas13@hotmail.com\",\r\n" +
        "    \"Edad\": 22,\r\n" +
        "    \"Estatura\": 1.78,\r\n" +
        "    \"Peso\":78,\r\n" +
        "    \"IMC\":26.98,\r\n" +
        "    \"GEB\":875.2,\r\n" +
        "    \"ETA\":125.87\r\n" +"\n}";
    System.out.println(POST_PARAMS);
    URL obj = new URL("http://localhost:4000/Clientes");
    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
    postConnection.setRequestMethod("POST");
    postConnection.setRequestProperty("Content-Type", "application/json");


    postConnection.setDoOutput(true);
    OutputStream os = postConnection.getOutputStream();
    os.write(POST_PARAMS.getBytes());
    os.flush();
    os.close();


    int responseCode = postConnection.getResponseCode();
    System.out.println("POST Response Code :  " + responseCode);
    System.out.println("POST Response Message : " + postConnection.getResponseMessage());

    if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
        BufferedReader in = new BufferedReader(new InputStreamReader(
            postConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in .readLine()) != null) {
            response.append(inputLine);
        } in .close();

        // print result
        System.out.println(response.toString());
    } else {
    }
}   
}
