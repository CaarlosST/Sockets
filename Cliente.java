import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 5001);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Scanner scanner = new Scanner(System.in);
			String inputLine;
			while (true) {

				inputLine = in.readLine();
				System.out.println(inputLine); // Mostrar mensaje del servidor
				String opc;
				while (true) {

					System.out.println("Digite un numero : ");
					opc = scanner.nextLine();
					out.println(opc); // Enviar número 1 al servidor
					System.out.println("Digite otro numero : ");
					opc = scanner.nextLine();
					out.println(opc); // Enviar número 2 al servidor
					mostraOpciones();
					opc = scanner.nextLine();
					if (opc.equals("6")) {
						System.out.println("Ha salido del programa");
						socket.close();
					}
					out.println(opc);
					inputLine = in.readLine();
					System.out.println(inputLine);
					inputLine = in.readLine();
					System.out.println(inputLine);

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void mostraOpciones() {
		System.out.println("Opciones disponibles");
		System.out.println("1 . Sumar");
		System.out.println("2 . Resta");
		System.out.println("3 . Muliplicar");
		System.out.println("4 . Dividir");
		System.out.println("5 . Modulo");
		System.out.println("6 . Salir del programa");

	}

}
