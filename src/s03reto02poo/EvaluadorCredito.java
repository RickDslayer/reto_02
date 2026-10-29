package s03reto02poo;

// Contiene las reglas de negocio: requisitos obligatorios y límites de endeudamiento.
public class EvaluadorCredito {
    private static final long EDAD_MINIMA = 18;
    private static final long EDAD_MAXIMA = 70;
    private static final long LIMITE_APROBACION = 35; // %
    private static final long LIMITE_REVISION = 45;   // %

    public ResultadoEvaluacion evaluar(SolicitudCredito solicitud) {
        if (solicitud.getEdad() < EDAD_MINIMA || solicitud.getEdad() > EDAD_MAXIMA) {
            return ResultadoEvaluacion.rechazoPrevio("la edad debe estar entre 18 y 70 años.");
        }
        // Se rechaza ANTES de dividir: disponible sería cero o negativo.
        if (solicitud.getIngresos() <= solicitud.getGastos()) {
            return ResultadoEvaluacion.rechazoPrevio("los gastos son iguales o mayores que los ingresos.");
        }

        long disponible = solicitud.getDisponible();
        long credito = solicitud.getCredito();
        long meses = solicitud.getMeses();

        // Comparación exacta con enteros (sin perder fracciones de la cuota):
        // cuota/disponible <= 35%  <=>  credito * 100 <= disponible * meses * 35
        // Valor máximo: 1e9 * 360 * 45 = 1.62e13, cabe sin desbordar en un long.
        long creditoPorCien = credito * 100;
        Clasificacion clasificacion;
        if (creditoPorCien <= disponible * meses * LIMITE_APROBACION) {
            clasificacion = Clasificacion.APROBADA;
        } else if (creditoPorCien <= disponible * meses * LIMITE_REVISION) {
            clasificacion = Clasificacion.REVISION_MANUAL;
        } else {
            clasificacion = Clasificacion.RECHAZADA;
        }

        // Solo para mostrar: la cuota en pesos es aproximada y el porcentaje es una razón (double).
        long cuotaAproximada = credito / meses;
        double endeudamiento = credito * 100.0 / (disponible * meses);

        return ResultadoEvaluacion.conCalculo(clasificacion, disponible, cuotaAproximada, endeudamiento);
    }
}
