package Candidaturas;
import java.time.*;
import java.util.*;

public class Partido {

    private LinkedHashMap<Integer, Candidato> candidatosParticipantes;
    private int numeroDoPartido;
    private String sigla;

    // private int votosValidos;
    private int votosNominais;
    private int votosDeLegenda;

    private int qtdEleitos;

    public Partido(int numeroDoPartido, String sigla) {
        this.numeroDoPartido = numeroDoPartido;
        this.sigla = sigla;
        this.candidatosParticipantes = new LinkedHashMap<>();
    }

    //Getters
    public int getNumeroDoPartido() {
        return numeroDoPartido;
    }
    
    public String getSigla() {
        return sigla;
    }

    public int getVotosValidos() {
        return votosDeLegenda + votosNominais;
    }

    public int getVotosDeLegenda() {
        return votosDeLegenda;
    }

    public int getVotosNominais() {
        return votosNominais;
    }

    public int getQtdEleitos() {
        return qtdEleitos;
    }
    
    // a ordenação dessa lista deixa os com menores números de candidato por último
    public List<Candidato> getCandidatosPartido(LocalDate dataEleicao){
        List<Candidato> candidatosPartido = new ArrayList<>(candidatosParticipantes.values());
        
        Collections.sort(candidatosPartido, (Candidato a, Candidato b) -> 
        {
            int ordem = b.getTotalDeVotos() - a.getTotalDeVotos();
            
            if(ordem == 0){
                ordem = b.getIdade(dataEleicao) - a.getIdade(dataEleicao);
                
                if(ordem == 0){
                    return b.getNumeroDoCandidato() - a.getNumeroDoCandidato();
                }

                return ordem;
            }

            return ordem;
        });
        return candidatosPartido;
    }

    public void adicionaCandidato(Candidato candidato){
        if(candidato.getSituacaoTot() == SituacaoTotalizacaoCandidato.ELEITO)
            qtdEleitos++;
        candidatosParticipantes.putIfAbsent(candidato.getNumeroDoCandidato(), candidato);
    }

    //votos nominais so podem ser realizados ao votar em um candidato
    protected void adicionarVotosNominais(int numeroDeVotosNominais){
        votosNominais += numeroDeVotosNominais;
    }

    //essa funcao pode funcionar fora do pacote pois o voto de legenda nao depende de um candidato
    public void adicionarVotosDeLegenda(int numeroDeVotosDeLegenda){
        votosDeLegenda += numeroDeVotosDeLegenda;
    }

    //Retorna a lista de candidatos que foram eleitos
    // public List<Candidato> getCandidatosEleitos(){
        // List<Candidato> candidatosEleitos = new ArrayList<>();
    // 
        // for (Candidato candidato : candidatosParticipantes.values()) {
            // if(candidato.getSituacaoTot() == SituacaoTotalizacaoCandidato.ELEITO){
                // candidatosEleitos.add(candidato);
            // }
        // }
        // return candidatosEleitos;
    // }

}
