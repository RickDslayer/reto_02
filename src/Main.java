import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int evaluadas = 0;
        int aprobadas = 0;
        int revisionManual = 0;
        int rechazadas = 0;

        int opcion;

        do {
            System.out.println();
            System.out.println("=== Simulador de aprobación de créditos ===");
            System.out.println("1 = Nueva solicitud");
            System.out.println("0 = Finalizar");
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un número entero.");
                scanner.next();
                System.out.print("Seleccione una opción: ");
            }
            opcion = scanner.nextInt();

            if (opcion != 0 && opcion != 1) {
                System.out.println("Opción inválida. Intente nuevamente.");
                continue; // en do-while, continue evalúa la condición y vuelve al menú
            }

            if (opcion == 1) {
                // --- Lectura y validación de datos (los datos corregidos no cuentan como solicitudes) ---
                System.out.print("Edad (0 a 120): ");
                while (!scanner.hasNextLong()) {
                    System.out.println("Error: debe ingresar un número entero.");
                    scanner.next();
                    System.out.print("Edad (0 a 120): ");
                }
                long edad = scanner.nextLong();
                while (edad < 0 || edad > 120) {
                    System.out.println("La edad debe estar entre 0 y 120.");
                    System.out.print("Edad (0 a 120): ");
                    while (!scanner.hasNextLong()) {
                        System.out.println("Error: debe ingresar un número entero.");
                        scanner.next();
                        System.out.print("Edad (0 a 120): ");
                    }
                    edad = scanner.nextLong();
                }

                System.out.print("Ingresos mensuales (0 a 1000000000): ");
                while (!scanner.hasNextLong()) {
                    System.out.println("Error: debe ingresar un número entero.");
                    scanner.next();
                    System.out.print("Ingresos mensuales (0 a 1000000000): ");
                }
                long ingresos = scanner.nextLong();
                while (ingresos < 0 || ingresos > 1_000_000_000L) {
                    System.out.println("Los ingresos deben estar entre 0 y 1000000000.");
                    System.out.print("Ingresos mensuales (0 a 1000000000): ");
                    while (!scanner.hasNextLong()) {
                        System.out.println("Error: debe ingresar un número entero.");
                        scanner.next();
                        System.out.print("Ingresos mensuales (0 a 1000000000): ");
                    }
                    ingresos = scanner.nextLong();
                }

                System.out.print("Gastos mensuales (0 a 1000000000): ");
                while (!scanner.hasNextLong()) {
                    System.out.println("Error: debe ingresar un número entero.");
                    scanner.next();
                    System.out.print("Gastos mensuales (0 a 1000000000): ");
                }
                long gastos = scanner.nextLong();
                while (gastos < 0 || gastos > 1_000_000_000L) {
                    System.out.println("Los gastos deben estar entre 0 y 1000000000.");
                    System.out.print("Gastos mensuales (0 a 1000000000): ");
                    while (!scanner.hasNextLong()) {
                        System.out.println("Error: debe ingresar un número entero.");
                        scanner.next();
                        System.out.print("Gastos mensuales (0 a 1000000000): ");
                    }
                    gastos = scanner.nextLong();
                }

                System.out.print("Crédito solicitado (1 a 1000000000): ");
                while (!scanner.hasNextLong()) {
                    System.out.println("Error: debe ingresar un número entero.");
                    scanner.next();
                    System.out.print("Crédito solicitado (1 a 1000000000): ");
                }
                long credito = scanner.nextLong();
                while (credito < 1 || credito > 1_000_000_000L) {
                    System.out.println("El crédito debe estar entre 1 y 1000000000.");
                    System.out.print("Crédito solicitado (1 a 1000000000): ");
                    while (!scanner.hasNextLong()) {
                        System.out.println("Error: debe ingresar un número entero.");
                        scanner.next();
                        System.out.print("Crédito solicitado (1 a 1000000000): ");
                    }
                    credito = scanner.nextLong();
                }

                System.out.print("Meses (1 a 360): ");
                while (!scanner.hasNextLong()) {
                    System.out.println("Error: debe ingresar un número entero.");
                    scanner.next();
                    System.out.print("Meses (1 a 360): ");
                }
                long meses = scanner.nextLong();
                while (meses < 1 || meses > 360) {
                    System.out.println("Los meses deben estar entre 1 y 360.");
                    System.out.print("Meses (1 a 360): ");
                    while (!scanner.hasNextLong()) {
                        System.out.println("Error: debe ingresar un número entero.");
                        scanner.next();
                        System.out.print("Meses (1 a 360): ");
                    }
                    meses = scanner.nextLong();
                }

                // --- Evaluación ---
                String clasificacion;
                System.out.println("--- Resultado de la solicitud ---");

                if (edad < 18 || edad > 70) {
                    clasificacion = "RECHAZADA";
                    System.out.println("Motivo: la edad debe estar entre 18 y 70 años.");
                } else if (ingresos <= gastos) {
                    // Se rechaza ANTES de dividir: disponible sería cero o negativo.
                    clasificacion = "RECHAZADA";
                    System.out.println("Motivo: los gastos son iguales o mayores que los ingresos.");
                } else {
                    long disponible = ingresos - gastos;

                    // Comparación exacta con enteros (sin perder fracciones de la cuota):
                    // cuota/disponible <= 35%  <=>  credito * 100 <= disponible * meses * 35
                    // Valor máximo: 1e9 * 360 * 45 = 1.62e13, cabe sin desbordar en un long.
                    long creditoPorCien = credito * 100;
                    long limite35 = disponible * meses * 35;
                    long limite45 = disponible * meses * 45;

                    // Solo para mostrar: la cuota en pesos es aproximada y el porcentaje es una razón (double).
                    long cuotaAproximada = credito / meses;
                    double endeudamiento = credito * 100.0 / (disponible * meses);

                    System.out.println("Dinero disponible: " + disponible);
                    System.out.println("Cuota estimada (aproximada, sin intereses): " + cuotaAproximada);
                    System.out.printf("Endeudamiento: %.2f %%%n", endeudamiento);

                    if (creditoPorCien <= limite35) {
                        clasificacion = "APROBADA";
                    } else if (creditoPorCien <= limite45) {
                        clasificacion = "REVISIÓN MANUAL";
                    } else {
                        clasificacion = "RECHAZADA";
                    }
                }

                System.out.println("Clasificación: " + clasificacion);

                // --- Contadores ---
                evaluadas++;
                if (clasificacion.equals("APROBADA")) {
                    aprobadas++;
                } else if (clasificacion.equals("REVISIÓN MANUAL")) {
                    revisionManual++;
                } else {
                    rechazadas++;
                }
            }
        } while (opcion != 0);

        System.out.println();
        System.out.println("=== Resumen ===");
        System.out.println("Solicitudes evaluadas: " + evaluadas);
        System.out.println("Aprobadas: " + aprobadas);
        System.out.println("Revisión manual: " + revisionManual);
        System.out.println("Rechazadas: " + rechazadas);

        scanner.close();
    }
}