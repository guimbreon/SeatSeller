package domain.core.reservas;

import java.util.HashMap;
import java.util.Map;

public class CatalogoReservas {

    private Map<String, Reserva> reservas;

    public CatalogoReservas() {
        this.reservas = new HashMap<>();
    }

    /**
     * Registra uma nova reserva no catálogo.
     * 
     * @param reserva a reserva a ser adicionada
     */
    public void registrarReserva(Reserva reserva) {
    	reservas.put(reserva.getCodigo(), reserva);
    }

    /**
     * Obtém uma reserva pelo seu código.
     * 
     * @param codRes código da reserva
     * @return a reserva correspondente ao código, ou null se não existir
     */
    public Reserva getReserva(String codRes) {
        return reservas.get(codRes);
    }
}
