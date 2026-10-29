# Reto 2 · Simulador de créditos (versión POO)

La misma especificación del reto 2, ahora repartida en clases con una responsabilidad cada una.

| Clase | Responsabilidad |
| --- | --- |
| `S03Reto02CreditosPOO` | `main`: menú 1/0 y coordinación de los objetos. |
| `LectorConsola` | Encapsula el `Scanner`; vuelve a pedir datos no numéricos o fuera de rango. |
| `SolicitudCredito` | Datos de la solicitud (inmutables); el constructor rechaza valores fuera de rango. |
| `EvaluadorCredito` | Reglas: edad 18–70, ingresos > gastos, límites de 35 % y 45 %. |
| `ResultadoEvaluacion` | Clasificación, motivo de rechazo o datos calculados, y su reporte. |
| `Clasificacion` | `enum` con APROBADA, REVISIÓN MANUAL y RECHAZADA. |
| `EstadisticasCreditos` | Contadores de evaluadas y de cada categoría, y el resumen final. |

## Diagrama UML

```mermaid
classDiagram
    direction TB

    class S03Reto02CreditosPOO {
        +main(String[] args)$ void
    }

    class LectorConsola {
        -Scanner scanner
        +LectorConsola(Scanner scanner)
        +leerEnRango(String etiqueta, long min, long max) long
        +leerSolicitud() SolicitudCredito
    }

    class EvaluadorCredito {
        -long EDAD_MINIMA = 18$
        -long EDAD_MAXIMA = 70$
        -long LIMITE_APROBACION = 35$
        -long LIMITE_REVISION = 45$
        +evaluar(SolicitudCredito solicitud) ResultadoEvaluacion
    }

    class EstadisticasCreditos {
        -int evaluadas
        -int aprobadas
        -int revisionManual
        -int rechazadas
        +registrar(Clasificacion clasificacion) void
        +generarResumen() String
        +getEvaluadas() int
        +getAprobadas() int
        +getRevisionManual() int
        +getRechazadas() int
    }

    class SolicitudCredito {
        +long EDAD_MIN = 0$
        +long EDAD_MAX = 120$
        +long DINERO_MAX = 1000000000$
        +long CREDITO_MIN = 1$
        +long MESES_MIN = 1$
        +long MESES_MAX = 360$
        -long edad
        -long ingresos
        -long gastos
        -long credito
        -long meses
        +SolicitudCredito(long edad, long ingresos, long gastos, long credito, long meses)
        -validarRango(String campo, long valor, long min, long max)$ void
        +getDisponible() long
        +getEdad() long
        +getIngresos() long
        +getGastos() long
        +getCredito() long
        +getMeses() long
    }

    class ResultadoEvaluacion {
        -Clasificacion clasificacion
        -String motivo
        -boolean calculado
        -long disponible
        -long cuotaAproximada
        -double endeudamiento
        -ResultadoEvaluacion(Clasificacion clasificacion, String motivo, boolean calculado, long disponible, long cuotaAproximada, double endeudamiento)
        +rechazoPrevio(String motivo)$ ResultadoEvaluacion
        +conCalculo(Clasificacion clasificacion, long disponible, long cuotaAproximada, double endeudamiento)$ ResultadoEvaluacion
        +generarReporte() String
        +getClasificacion() Clasificacion
        +getMotivo() String
        +isCalculado() boolean
        +getDisponible() long
        +getCuotaAproximada() long
        +getEndeudamiento() double
    }

    class Clasificacion {
        <<enumeration>>
        APROBADA
        REVISION_MANUAL
        RECHAZADA
        -String etiqueta
        +getEtiqueta() String
    }

    S03Reto02CreditosPOO ..> LectorConsola : crea
    S03Reto02CreditosPOO ..> EvaluadorCredito : crea
    S03Reto02CreditosPOO ..> EstadisticasCreditos : crea
    LectorConsola ..> SolicitudCredito : crea
    EvaluadorCredito ..> SolicitudCredito : usa
    EvaluadorCredito ..> ResultadoEvaluacion : crea
    EstadisticasCreditos ..> Clasificacion : usa
    ResultadoEvaluacion --> "1" Clasificacion : clasificacion
```

Notación: `+` público, `-` privado, subrayado = `static`. Flecha discontinua = dependencia (la clase
crea o usa a la otra); flecha continua = asociación (la guarda como atributo). Todos los atributos de
`SolicitudCredito`, `ResultadoEvaluacion`, `Clasificacion` y `LectorConsola`, y las constantes, son `final`.

## Precisión

La categoría se decide con enteros exactos: `credito * 100` frente a `disponible * meses * 35` y `* 45`
(máximo 1.62e13, cabe en `long`). La cuota en pesos se muestra truncada y se indica que es aproximada;
el porcentaje es un `double` solo para mostrar. Si edad o ingresos ≤ gastos fallan, se rechaza sin dividir.

## Ejecución

Desde `reto-02-creditos/`:

```bash
javac -d out s03reto02poo/*.java
java -cp out s03reto02poo.S03Reto02CreditosPOO
```

Las seis pruebas mínimas del enunciado dan: 6 evaluadas, 1 aprobada, 2 en revisión y 3 rechazadas.
Meses = 0 se vuelve a pedir y finalizar de inmediato muestra los cuatro contadores en cero.
