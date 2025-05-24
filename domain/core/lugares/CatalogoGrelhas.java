package domain.core.lugares;

import java.util.HashMap;
import java.util.Map;

public class CatalogoGrelhas {

    private Map<String, Grelha> grelhas = new HashMap<>();
    
    
    public Grelha getGrelha(String desig) {
        return grelhas.get(desig);
    }
}
