package domain.handlers;

import domain.api.IDesassociarGrelhaHandler;
import domain.api.INotificacaoReceiver;
import domain.core.lugares.CatalogoGrelhas;
import domain.core.utilizadores.Utilizador;

public class DesassociarGrelhaHandler implements IDesassociarGrelhaHandler {

    private Utilizador utilizador;
    private CatalogoGrelhas catGrelhas;

    public DesassociarGrelhaHandler(Utilizador utilizador, CatalogoGrelhas catGrelhas) {
        this.utilizador = utilizador;
        this.catGrelhas = catGrelhas;
    }


	@Override
	public void desassociarGrelha(String desig, INotificacaoReceiver c) {
		// TODO Auto-generated method stub

	}

}
