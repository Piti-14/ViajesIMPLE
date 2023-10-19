// Author : Pedro Marín Sanchis, Jorge Pitarch Hernando.

package gestor;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ViajesLocal {

    static GestorViajes gestorViajes = new GestorViajes();
    static String codigoCliente = null;
    final static private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        codigoCliente = obtenerString("Introduce tu código de cliente: ");
        mostrarMenu();
    }

    private static void mostrarMenu() {
        String mensajeMenu = "\n\n" +
                "----------------------------------------------------------\n" +
                "-------------------    MENÚ PRINCIPAL    -----------------\n" +
                "----------------------------------------------------------\n" +
                "1.- Salir del programa guardando los datos en el fichero.\n"+
                "2.- Mostrar los datos de los viajes con un origen dado.\n"+
                "3.- Reservar un viaje.\n"+
                "4.- Anular una reserva.\n"+
                "5.- Ofertar un nuevo viaje.\n"+
                "6.- Borrar un viaje ofertado.\n";

        while (true) {
            System.out.println(mensajeMenu);
            switch (obtenerEntero("Introduce tu opción: ")) {
                case 1:
                    gestorViajes.guardaDatos();
                    scanner.close();
                    System.exit(0);
                    break;
                case 2:
                    System.out.println(
                            daFormatoJSONString(
                                    gestorViajes.consultaViajes(
                                            obtenerString("Introduce el origen: ")
                                    ).toJSONString()
                            )
                    );
                    break;
                case 3:
                    System.out.println(
                            daFormatoJSONString(
                                    gestorViajes.reservaViaje(
                                            obtenerString("Introduce el código del viaje: "), codigoCliente
                                    ).toJSONString()
                            )
                    );
                    break;
                case 4:
                    System.out.println(
                            daFormatoJSONString(
                                    gestorViajes.anulaReserva(
                                            obtenerString("Introduce el código del viaje: "),
                                            codigoCliente
                                    ).toJSONString()
                            )
                    );
                    break;
                case 5:
                    System.out.println(
                            daFormatoJSONString(
                                    gestorViajes.ofertaViaje(codigoCliente, obtenerString("Introduce el origen: "),
                                            obtenerString("Introduce el destino: "),
                                            obtenerString("Introduce la fecha: "),
                                            obtenerLargo("Introduce el precio: "),
                                            obtenerLargo("Introduce el número de plazas: ")
                                    ).toJSONString()
                            )
                    );
                    break;
                case 6:
                    System.out.println(
                            daFormatoJSONString(
                                    gestorViajes.borraViaje(
                                            obtenerString("Introduce el código del viaje: "),
                                            codigoCliente
                                    ).toJSONString()
                            )
                    );
                    break;
                default:
                    System.out.println("[Error] : Esa no es una opción válida.");
                    break;
            }
        }
    }

    private static String daFormatoJSONString(String jsonString) {
        // TODO : Terminar el método que da formato a un string JSON.
        jsonString.replaceAll(",", ",\n"); //ELEMENTS
        jsonString.replaceAll("[\\]]", "\n]").replaceAll("[\\[]", "[\n"); //ARRAYS
        return jsonString;
    }

    private static int obtenerEntero(String mensaje) {
        int entero = 0;

        while (true) {
            System.out.print(mensaje);
            try {
                entero = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (NoSuchElementException nse) {
                System.out.println("[ERROR] : No se ha escrito un número.");
            }
        }
        return entero;
    }

    private static long obtenerLargo(String mensaje) {
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
        return largo;
    }

    private static String obtenerString(String mensaje) {
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
        return string;
    }

}
