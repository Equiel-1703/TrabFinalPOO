package ufpel.trabfinalpoo.generalClasses;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

public class AtividadeCadastrada extends Atividade {
    protected URL linkPDF;
    protected String descAtv;
    protected int qtdeHoras;
    protected Aprovacao estadoAprovacao;
    protected String justificativa;

    public AtividadeCadastrada(Atividade atv) {
        super(atv.codigo, atv.tipoAtv, atv.unidade, atv.qtdeMin, atv.qtdeMax);
        this.estadoAprovacao = Aprovacao.NOT_SET;
    }

    public AtividadeCadastrada(String[] s) {
        try {
            codigo = Integer.parseInt(s[0]);
            tipoAtv = s[1];
            unidade = s[2];
            qtdeMin = Integer.parseInt(s[3]);
            qtdeMax = Integer.parseInt(s[4]);
            linkPDF = new URL(s[5]);
            descAtv = s[6];
            qtdeHoras = Integer.parseInt(s[7]);
            estadoAprovacao = Aprovacao.convertToEnum(s[8]);
            justificativa = s[9];
        }
        catch (NumberFormatException e) {
            System.err.println("Erro ao ler valor do vetor de atividade cadastrada! Havia algo errado em um dos campos que deveria ter um número no CSV.");
            System.err.println(e.toString());
            System.exit(1);
        }
        catch (MalformedURLException e) {
            System.err.println("Erro ao ler valor do vetor de atividade cadastrada! Havia algo errado com a URL para o comprovante no CSV.");
            System.err.println(e.toString());
            System.exit(1);
        }
        catch (InvalidParameterException e)
        {
            System.err.println("Erro ao ler valor do estado de aprovação.");
            System.err.println(e.toString());
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        return "" + codigo + " - " + descAtv;
    }

    public String[] toStringArray() {
        String[] ret = new String[10];

        ret[0] = "" + codigo;
        ret[1] = tipoAtv;
        ret[2] = unidade;
        ret[3] = "" + qtdeMin;
        ret[4] = "" + qtdeMax;
        ret[5] = linkPDF.toString();
        ret[6] = descAtv;
        ret[7] = "" + qtdeHoras;
        ret[8] = "" + estadoAprovacao.toString();
        ret[9] = justificativa;

        return ret;
    }

    public static List<AtividadeCadastrada> convertToAtvCadList(List<String[]> stringList) {
        List<AtividadeCadastrada> atvsLista = new LinkedList<>();

        for (String[] s : stringList)
        {
            AtividadeCadastrada newAtv = new AtividadeCadastrada(s);
            atvsLista.add(newAtv);
        }

        return atvsLista;
    }

    // Getters
    public URL getLinkPDF() {
        return linkPDF;
    }

    public String getDescAtv() {
        return descAtv;
    }

    public int getQtdeHoras() {
        return qtdeHoras;
    }

    public Aprovacao getEstadoAprovacao() {
        return estadoAprovacao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    // Setters
    public void setLinkPDF(URL linkPDF) {
        this.linkPDF = linkPDF;
    }

    public void setDescAtv(String descAtv) {
        this.descAtv = descAtv;
    }

    public void setQtdeHoras(int qtdeHoras) {
        this.qtdeHoras = qtdeHoras;
    }

    public void setEstadoAprovacao(Aprovacao estadoAprovacao) {
        this.estadoAprovacao = estadoAprovacao;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}
