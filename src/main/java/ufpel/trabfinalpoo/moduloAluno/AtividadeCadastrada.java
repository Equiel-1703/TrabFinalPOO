package ufpel.trabfinalpoo.moduloAluno;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class AtividadeCadastrada extends Atividade {
    protected URL linkPDF;
    protected String descAtv;
    protected int qtdeHoras;

    public AtividadeCadastrada(Atividade atv) {
        super(atv.codigo, atv.tipoAtv, atv.unidade, atv.qtdeMin, atv.qtdeMax);
    }

    public AtividadeCadastrada(int codigo, String tipoAtv, String unidade, int qtdeMin, int qtdeMax, URL url, String desc, int qtdeHrs) {
        super(codigo, tipoAtv, unidade, qtdeMin, qtdeMax);

        this.linkPDF = url;
        this.descAtv = desc;
        this.qtdeHoras = qtdeHrs;
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
    }

    @Override
    public String toString() {
        return "" + codigo + " - " + descAtv;
    }

    public String[] toStringArray() {
        String[] ret = new String[8];

        ret[0] = "" + codigo;
        ret[1] = tipoAtv;
        ret[2] = unidade;
        ret[3] = "" + qtdeMin;
        ret[4] = "" + qtdeMax;
        ret[5] = linkPDF.toString();
        ret[6] = descAtv;
        ret[7] = "" + qtdeHoras;

        return ret;
    }

    public static List<AtividadeCadastrada> convertToAtvCad(List<String[]> stringList) {
        List<AtividadeCadastrada> atvsLista = new LinkedList<>();

        for (String [] s : stringList)
        {
            AtividadeCadastrada newAtv = new AtividadeCadastrada(s);
            atvsLista.add(newAtv);
        }

        return atvsLista;
    }
}
