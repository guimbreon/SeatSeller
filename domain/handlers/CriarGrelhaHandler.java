package domain.handlers;

import java.util.Optional;

import domain.api.ICriarGrelhaHandler;
import domain.api.exceptions.DoesNotExistException;
import domain.api.exceptions.NonUniqueException;

public class CriarGrelhaHandler implements ICriarGrelhaHandler {

	@Override
	public void iniciarGrelha(String desig, double ind) throws NonUniqueException {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<String> indicarDimensao(int alt, int larg) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void indicarTipoPadrao(String tip) throws DoesNotExistException {
		// TODO Auto-generated method stub

	}

	@Override
	public void indicarTipoLugar(int i, int j, String tip) throws DoesNotExistException {
		// TODO Auto-generated method stub

	}

	@Override
	public void terminar() {
		// TODO Auto-generated method stub

	}

}
