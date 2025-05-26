package domain.handlers;

import domain.api.IAssociarGrelhaHandler;

import domain.api.INotificacaoReceiver;
import domain.core.lugares.CatalogoGrelhas;
import domain.core.lugares.Grelha;
import domain.core.utilizadores.Utilizador;
import domain.core.utilizadores.Funcionario;

public class AssociarGrelhaHandler implements IAssociarGrelhaHandler {

	 private Utilizador utilizador;
	    private CatalogoGrelhas catGrelhas;

	    public AssociarGrelhaHandler(Utilizador utilizador, CatalogoGrelhas catGrelhas) {
	        this.utilizador = utilizador;
	        this.catGrelhas = catGrelhas;
	    }


    /**
     * Associa a grelha indicada pela designação ao funcionário que está usando o handler.
     * Se a grelha existir, o funcionário é informado da associação via o receptor de notificações.
     * 
     * @param desig Designação da grelha a ser associada.
     * @param c Receptor que irá receber notificações da associação.
     */
	@Override
	public void associarGrelha(String desig, INotificacaoReceiver c) {
		Grelha g = this.catGrelhas.getGrelha(desig);
		Funcionario f = (Funcionario) utilizador;
		if(g != null) {
			f.associarGrelha(g, c);	
		}
	}

}
