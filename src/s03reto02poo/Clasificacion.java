package s03reto02poo;

// Resultados posibles de una solicitud. Cada constante guarda el texto que se muestra al usuario.
public enum Clasificacion {
    APROBADA("APROBADA"),
    REVISION_MANUAL("REVISIÓN MANUAL"),
    RECHAZADA("RECHAZADA");

    private final String etiqueta;

    Clasificacion(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
