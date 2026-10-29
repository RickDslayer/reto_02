package s03reto02poo;

import java.util.Scanner;

// Encapsula el Scanner: pide valores hasta que sean numéricos y estén dentro del rango.
public class LectorConsola {
    private final Scanner scanner;

    public LectorConsola(Scanner scanner) {
        this.scanner = scanner;
    }

    public long leerEnRango(String etiqueta, long min, long max) {
        String prompt = etiqueta + " (" + min + " a " + max + "): ";
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLong()) {
                System.out.println("Error: debe ingresar un número entero.");
                scanner.next();
                continue;
            }
            long valor = scanner.nextLong();
            if (valor >= min && valor <= max) {
                return valor;
            }
            System.out.println("El valor debe estar entre " + min + " y " + max + ".");
        }
    }

    public SolicitudCredito leerSolicitud() {
        long edad = leerEnRango("Edad", SolicitudCredito.EDAD_MIN, SolicitudCredito.EDAD_MAX);
        long ingresos = leerEnRango("Ingresos mensuales", 0, SolicitudCredito.DINERO_MAX);
        long gastos = leerEnRango("Gastos mensuales", 0, SolicitudCredito.DINERO_MAX);
        long credito = leerEnRango("Crédito solicitado", SolicitudCredito.CREDITO_MIN, SolicitudCredito.DINERO_MAX);
        long meses = leerEnRango("Meses", SolicitudCredito.MESES_MIN, SolicitudCredito.MESES_MAX);
        return new SolicitudCredito(edad, ingresos, gastos, credito, meses);
    }
}
