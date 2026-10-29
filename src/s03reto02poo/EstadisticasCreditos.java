package s03reto02poo;

// Lleva los contadores. La suma de las categorías siempre coincide con las evaluadas.
public class EstadisticasCreditos {
    private int evaluadas;
    private int aprobadas;
    private int revisionManual;
    private int rechazadas;

    public void registrar(Clasificacion clasificacion) {
        evaluadas++;
        switch (clasificacion) {
            case APROBADA -> aprobadas++;
            case REVISION_MANUAL -> revisionManual++;
            case RECHAZADA -> rechazadas++;
        }
    }

    public String generarResumen() {
        return "=== Resumen ===\n"
                + "Solicitudes evaluadas: " + evaluadas + "\n"
                + "Aprobadas: " + aprobadas + "\n"
                + "Revisión manual: " + revisionManual + "\n"
                + "Rechazadas: " + rechazadas;
    }

    public int getEvaluadas() {
        return evaluadas;
    }

    public int getAprobadas() {
        return aprobadas;
    }

    public int getRevisionManual() {
        return revisionManual;
    }

    public int getRechazadas() {
        return rechazadas;
    }
}
