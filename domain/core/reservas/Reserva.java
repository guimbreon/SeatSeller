package domain.core.reservas;

import domain.core.pagamentos.Pagamento; 
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Reserva {
    private int id;
    private LocalDateTime dataReserva;
    private List<LinhaReserva> linhasReserva;

    public Reserva(){
        this.id = id;
        this.dataReserva = dataReserva;
        this.linhasReserva = new ArrayList<>();
    }

    public LocalDate getdate() {
        return dataReserva.toLocalDate();
    }

    public LocalTime getTime() {
        return dataReserva.toLocalTime();
    }

    public double getSubtotal() {
        double subtotal = 0.0;
        for (LinhaReserva linha : linhasReserva) {
            subtotal += linha.getPreco();
        }
        return subtotal;
    }
}
