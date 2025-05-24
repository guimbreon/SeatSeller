package domain.handlers;

import domain.api.IAssociarGrelhaHandler;
import domain.api.INotificacaoReceiver;
import domain.core.lugares.CatalogoGrelhas;
import domain.core.utilizadores.Utilizador;

public class AssociarGrelhaHandler implements IAssociarGrelhaHandler {

	 private Utilizador utilizador;
	    private CatalogoGrelhas catGrelhas;

	    public AssociarGrelhaHandler(Utilizador utilizador, CatalogoGrelhas catGrelhas) {
	        this.utilizador = utilizador;
	        this.catGrelhas = catGrelhas;
	    }


	@Override
	public void associarGrelha(String desig, INotificacaoReceiver c) {
		// TODO Auto-generated method stub

	}

}
