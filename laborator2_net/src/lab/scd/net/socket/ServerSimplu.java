package lab.scd.net.socket;/*
 * ServerSimplu.java
 */

/**
 * Class created by @author Mihai HULEA at Feb 23, 2005.
 * 
 * This class is part of the laborator2_serverclientmonofir project.
 * 
 * 1. Modificati aplicatia sever astfel incat dupa tratarea unui client acesta sa revina 
 * in astepatare pentru a procesa noi cereri. 
 * 
 * 2. Modificati aplicatia server astefl incat aceasta sa accepte conexiuni sosite 
 * numai de pe anumite IP-uri.
 */
import java.io.*;
import java.net.*;

public class ServerSimplu {
  public static void main(String[] args) throws IOException {

    ServerSocket ss = null;
    Socket s = null;

    try {
      ss = new ServerSocket(1900);
      System.out.println("Serverul asteapta conexiuni...");

      while (true) {
        s = ss.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);

        String line = in.readLine();
        if (line != null && !line.isEmpty()) {
          int commaIndex = line.indexOf(","); // find the position of comma
          if(commaIndex > 0 && commaIndex < line.length() - 1) { // check comma is not at the start or end
            String xStr = line.substring(0, commaIndex);
            String yStr = line.substring(commaIndex + 1);

            double x = Double.parseDouble(xStr);
            double y = Double.parseDouble(yStr);
            double percentage = (y / x) * 100;
            out.println(percentage); // send percentage back to client
          } else {
            out.println("Invalid input format.");
          }
          out.flush();
        }
        s.close();
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (ss != null) ss.close();
    }
  }
}
