import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SW
 */
public class ComportamentoPICTeste {

    ControladorDeInterrupcoes controladorDeInterrupcoes = new ControladorDeInterrupcoes();
    public ComportamentoPICTeste() {
    }

    @Test
    public void DadoUmaRequisicaoAoVerificarDeveIdentificarDispositivoEAdicionarNaFila() {
        DadoUmaRequisicao();
        AoVerificar();
        DeveIdentificarDispositivoEAdicionarNaFila();
    }

    private void DadoUmaRequisicao() {
        controladorDeInterrupcoes.MarcarRequisicaoParaDispositivo(0);
    }

    private void AoVerificar() {
        controladorDeInterrupcoes.VerificarRequisicoes();
    }

    private void DeveIdentificarDispositivoEAdicionarNaFila() {
        assertTrue(controladorDeInterrupcoes.PosicoesOcupadasNaFila() > 0);
    }
}