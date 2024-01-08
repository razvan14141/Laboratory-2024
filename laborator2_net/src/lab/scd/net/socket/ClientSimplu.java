package lab.scd.net.socket;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientSimplu {

  public static void main(String[] args) throws Exception {
    Socket socket = null;
    try {
      InetAddress server_address = InetAddress.getByName("localhost");
      socket = new Socket(server_address, 1900);

      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter x: ");
      double x = scanner.nextDouble(); // Take x as input
      System.out.print("Enter y: ");
      double y = scanner.nextDouble(); // Take y as input

      // Send x and y to the server
      out.println(x + "," + y);
      out.flush();

      // Receive and display the result from the server
      String str = in.readLine();
      System.out.println("Percentage from server: " + str + "%");

    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      if (socket != null) socket.close();
    }
  }
}
