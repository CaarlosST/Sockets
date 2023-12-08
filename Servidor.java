import java.io.*;
import java.net.*;

public class Servidor implements Runnable {
    private final Socket clientSocket;

    public Servidor(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String num1;
            String num2;
            String op;
            out.println("Te has conectado a una calculadora online");
            while(true) {
            num1 = in.readLine();
            System.out.println("Valor digitado: " + num1);

            
            num2 = in.readLine();
            System.out.println("Valor digitado: " + num2);

            out.println("Valores elegidos: numero 1: " + num1 + ", numero 2: " + num2);            

            op = in.readLine();

            String resultado = operacion(op, num1, num2);
            out.println("Resultado: " + resultado);
           }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public String operacion(String opcion, String num1, String num2) {
        double resultado = 0;
        try {
            double n1 = Double.parseDouble(num1);
            double n2 = Double.parseDouble(num2);

            switch (opcion) {
                case "1":
                    resultado = n1 + n2;
                    break;
                case "2":
                    resultado = n1 - n2;
                    break;
                case "3":
                    resultado = n1 * n2;
                    break;
                case "4":
                    if (n2 != 0) {
                        resultado = n1 / n2;
                    } else {
                        return "No se puede dividir por cero.";
                    }
                    break;
                case "5":
                    resultado = n1 % n2;
                    break;
                default:
                    return "Operación no válida";
            }
        } catch (NumberFormatException e) {
            return "Valores no numéricos";
        }

        return Double.toString(resultado);
    }

    
	public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5001);
            System.out.println("Esperando conexiones...");
            int i = 1;
            while (true) {
                Socket clientSocket2 = serverSocket.accept();
                System.out.println("Nuevo cliente conectado "+"("+i+") desde " + clientSocket2.getInetAddress());
                i++;
                new Thread(new Servidor(clientSocket2), "Cliente").start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
