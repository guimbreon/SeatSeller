package domain.handlers;

import domain.api.IDesassociarGrelhaHandler;
import domain.api.INotificacaoReceiver;
import domain.core.lugares.CatalogoGrelhas;
import domain.core.lugares.Grelha;
import domain.core.utilizadores.Funcionario;
import domain.core.utilizadores.Utilizador;

public class DesassociarGrelhaHandler implements IDesassociarGrelhaHandler {

    private Utilizador utilizador;
    private CatalogoGrelhas catGrelhas;

    public DesassociarGrelhaHandler(Utilizador utilizador, CatalogoGrelhas catGrelhas) {
        this.utilizador = utilizador;
        this.catGrelhas = catGrelhas;
    }


    /**
     * Desassocia a grelha com a designação especificada do funcionário.
     * Se a grelha existir, chama o método de desassociação no funcionário.
     * 
     * @param desig Designação da grelha a desassociar.
     * @param c Interface para notificação do resultado da operação.
     */
	@Override
	public void desassociarGrelha(String desig, INotificacaoReceiver c) {
		Grelha g = this.catGrelhas.getGrelha(desig);
		Funcionario f = (Funcionario) utilizador;
		if(g != null) {
			f.desassociarGrelha(g, c);	
		}
	}

}
