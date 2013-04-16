/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SW
 */
public class ControladorDeInterrupcoes {

    private Dispositivo[] dispositivos;
    public RegistradorDaFila[] FilaDeRequisicoes;
    private int PosicoesOcupadasNaFila;

    public int PosicoesOcupadasNaFila() {
        int ocupadas = 0;
        for (int i = 0; i < FilaDeRequisicoes.length; i++) {
            RegistradorDaFila temp = FilaDeRequisicoes[i];
            if (temp.Ocupado) {
                ocupadas++;
            }
        }
        return ocupadas;
    }

    public ControladorDeInterrupcoes() {
        dispositivos = new Dispositivo[8];
        FilaDeRequisicoes = new RegistradorDaFila[8];
        for (int i = 0; i < 8; i++) {
            dispositivos[i] = new Dispositivo();
            FilaDeRequisicoes[i] = new RegistradorDaFila();
        }
    }

    public void MarcarRequisicaoParaDispositivo(int numeroDoDispositivo) {
        ToggleRequisicaoDispositivo(numeroDoDispositivo, true);
    }

    private void DesmarcarInterrupcaoDoDispositivo(int numeroDoDispositivo) {
        ToggleRequisicaoDispositivo(numeroDoDispositivo, false);
    }

    private void ToggleRequisicaoDispositivo(int numeroDoDispositivo, boolean interromper) {
        if (!IndiceDisponivel(numeroDoDispositivo)) {
            return;
        }
        dispositivos[numeroDoDispositivo].Interromper = interromper;
    }

    public void VerificarRequisicoes() {
        for (int i = 0; i < dispositivos.length; i++) {
            if (dispositivos[i].Interromper)//Só atende um dispositivo por vez, como a ordem de prioridade é por numero e o menor tem mais prioridade, basta iterar
            {
                if (AdicionarInterrupcao(i))//Verifica se conseguiu agendar, caso contrario deixa marcada a interrupcao
                {
                    DesmarcarInterrupcaoDoDispositivo(i);
                }
                return;
            }
        }
    }

    private boolean IndiceDisponivel(int numeroDoDispositivo) {
        return numeroDoDispositivo >= 0 && numeroDoDispositivo <= dispositivos.length;
    }

    private boolean AdicionarInterrupcao(int numeroDoDispositivo) {
        for (int i = 0; i < FilaDeRequisicoes.length; i++) {
            RegistradorDaFila registradorDaFila = FilaDeRequisicoes[i];
            if (!registradorDaFila.Ocupado) {
                registradorDaFila.NumeroDoDispositivo = numeroDoDispositivo;
                registradorDaFila.Ocupado = true;
                return true;
            }
        }
        return false;
    }
}
