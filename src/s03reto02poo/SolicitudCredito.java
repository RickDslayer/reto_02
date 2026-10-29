package s03reto02poo;

// Datos de una solicitud. Solo puede construirse con valores dentro de los rangos del modelo.
public class SolicitudCredito {
    public static final long EDAD_MIN = 0;
    public static final long EDAD_MAX = 120;
    public static final long DINERO_MAX = 1_000_000_000L;
    public static final long CREDITO_MIN = 1;
    public static final long MESES_MIN = 1;
    public static final long MESES_MAX = 360;

    private final long edad;
    private final long ingresos;
    private final long gastos;
    private final long credito;
    private final long meses;

    public SolicitudCredito(long edad, long ingresos, long gastos, long credito, long meses) {
        validarRango("edad", edad, EDAD_MIN, EDAD_MAX);
        validarRango("ingresos", ingresos, 0, DINERO_MAX);
        validarRango("gastos", gastos, 0, DINERO_MAX);
        validarRango("crédito", credito, CREDITO_MIN, DINERO_MAX);
        validarRango("meses", meses, MESES_MIN, MESES_MAX);
        this.edad = edad;
        this.ingresos = ingresos;
        this.gastos = gastos;
        this.credito = credito;
        this.meses = meses;
    }

    private static void validarRango(String campo, long valor, long min, long max) {
        if (valor < min || valor > max) {
            throw new IllegalArgumentException(campo + " debe estar entre " + min + " y " + max);
        }
    }

    // Puede ser cero o negativo: quien lo use debe comprobarlo antes de dividir.
    public long getDisponible() {
        return ingresos - gastos;
    }

    public long getEdad() {
        return edad;
    }

    public long getIngresos() {
        return ingresos;
    }

    public long getGastos() {
        return gastos;
    }

    public long getCredito() {
        return credito;
    }

    public long getMeses() {
        return meses;
    }
}
