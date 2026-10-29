package s03reto02poo;

// Resultado de evaluar una solicitud. Los datos calculados solo existen si hubo cálculo (sin rechazo previo).
public class ResultadoEvaluacion {
    private final Clasificacion clasificacion;
    private final String motivo;
    private final boolean calculado;
    private final long disponible;
    private final long cuotaAproximada;
    private final double endeudamiento;

    private ResultadoEvaluacion(Clasificacion clasificacion, String motivo, boolean calculado,
                                long disponible, long cuotaAproximada, double endeudamiento) {
        this.clasificacion = clasificacion;
        this.motivo = motivo;
        this.calculado = calculado;
        this.disponible = disponible;
        this.cuotaAproximada = cuotaAproximada;
        this.endeudamiento = endeudamiento;
    }

    // Rechazo por requisitos obligatorios: no se calcula nada (no se divide).
    public static ResultadoEvaluacion rechazoPrevio(String motivo) {
        return new ResultadoEvaluacion(Clasificacion.RECHAZADA, motivo, false, 0, 0, 0);
    }

    public static ResultadoEvaluacion conCalculo(Clasificacion clasificacion, long disponible,
                                                 long cuotaAproximada, double endeudamiento) {
        return new ResultadoEvaluacion(clasificacion, null, true, disponible, cuotaAproximada, endeudamiento);
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder("--- Resultado de la solicitud ---\n");
        if (calculado) {
            sb.append("Dinero disponible: ").append(disponible).append('\n');
            sb.append("Cuota estimada (aproximada, sin intereses): ").append(cuotaAproximada).append('\n');
            sb.append(String.format("Endeudamiento: %.2f %%%n", endeudamiento));
        } else {
            sb.append("Motivo: ").append(motivo).append('\n');
        }
        sb.append("Clasificación: ").append(clasificacion.getEtiqueta());
        return sb.toString();
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public boolean isCalculado() {
        return calculado;
    }

    public long getDisponible() {
        return disponible;
    }

    public long getCuotaAproximada() {
        return cuotaAproximada;
    }

    public double getEndeudamiento() {
        return endeudamiento;
    }
}
