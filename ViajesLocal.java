// Author : Pedro Marín Sanchis, Jorge Pitarch Hernando.

package gestor;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ViajesLocal {

    static GestorViajes gestorViajes = new GestorViajes();
    static String codigoCliente = null;

    public static void main(String[] args) {
        codigoCliente = obtenerString("Introduce tu código de cliente: ");
        mostrarMenu();
    }

    private static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        String mensajeMenu = "1.- Salir del programa guardando los datos en el fichero.\n"+
                "2.- Mostrar los datos de los viajes con un origen dado.\n"+
                "3.- Reservar un viaje.\n"+
                "4.- Anular una reserva.\n"+
                "5.- Ofertar un nuevo viaje.\n"+
                "6.- Borrar un viaje ofertado.";

        while (true) {
            System.out.println(mensajeMenu);
            switch (obtenerEntero("Introduce tu opción: ")) {
                case 1:
                    gestorViajes.guardaDatos();
                    scanner.close();
                    System.exit(0);
                    break;
                case 2:
                    gestorViajes.consultaViajes(obtenerString("Introduce el origen: "));
                    break;
                case 3:
                    gestorViajes.reservaViaje(obtenerString("Introduce el código del viaje: "), codigoCliente);
                    break;
                case 4:
                    gestorViajes.anulaReserva(obtenerString("Introduce el código del viaje: "), codigoCliente);
                    break;
                case 5:
                    gestorViajes.ofertaViaje(codigoCliente, obtenerString("Introduce el origen: "),
                            obtenerString("Introduce el destino: "),
                            obtenerString("Introduce la fecha: "),
                            obtenerLargo("Introduce el precio: "),
                            obtenerLargo("Introduce el número de plazas: "));
                    break;
                case 6:
                    gestorViajes.borraViaje(obtenerString("Introduce el código del viaje: "), codigoCliente);
                    break;
                default:
                    System.out.println("[Error] : Esa no es una opción válida.");
                    break;
            }
        }
    }

    private static int obtenerEntero(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        int entero = 0;

        while (true) {
            System.out.print(mensaje);
            try {
                entero = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("[ERROR] : No se ha escrito un número.");
            }
        }
        scanner.close();
        return entero;
    }

    private static long obtenerLargo(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        long largo = 0;

        while (true) {
            System.out.print(mensaje);
            try {
                largo = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("[ERROR] : No se ha escrito un número.");
            }
        }
        scanner.close();
        return largo;
    }

    private static String obtenerString(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        String string = null;

        while (true) {
            System.out.print(mensaje);
            try {
                string = scanner.nextLine();
                break;
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("[ERROR] : No se ha escrito un número.");
            }
        }
        scanner.close();
        return string;
    }

}
