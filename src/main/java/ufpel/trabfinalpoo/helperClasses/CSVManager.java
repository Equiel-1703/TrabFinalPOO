package ufpel.trabfinalpoo.helperClasses;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import ufpel.trabfinalpoo.generalClasses.Aluno;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class CSVManager extends FilesManager {

    public static final String CSV_ATV_CIENCIA = "csvs/atvs_cComp.csv";
    public static final String CSV_ATV_ENG = "csvs/atvs_engComp.csv";

    private static final String[] DATA_CSV_HEADER = {"Código", "Tipo Atv.", "Unidade", "Qtde Min", "Qtde Max", "Link PDF", "Descrição", "Qtde Horas", "Estado de Aprovação", "Justificativa"};

    public static List<String[]> readCSV(String path, char delim) throws IOException {
        // Cria o leitor de CSV
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReaderBuilder(new FileReader(path, StandardCharsets.UTF_8))
                    .withSkipLines(1) // Ignora o cabeçalho
                    .withCSVParser(new CSVParserBuilder().withSeparator(delim).build())
                    .build();
        }
        catch (FileNotFoundException e) {
            System.err.println("Arquivo CSV \"" + path + "\" não encontrado.");
            System.exit(1);
        }

        // Salva referência da lista
        List<String[]> ls = csvReader.readAll();

        // Fecha o leitor
        csvReader.close();

        return ls;
    }

    public static CSVWriter writeCSVSetup(Aluno aluno) throws IOException {
        FileWriter outFile = new FileWriter(PATH_TO_SAVE_DATA_SEP + aluno.getNome().toUpperCase() + "_" + aluno.getMatricula() + ".csv", StandardCharsets.UTF_8);
        CSVWriter csvWriter = new CSVWriter(outFile, ';', '"', '\\', "\n");

        csvWriter.writeNext(DATA_CSV_HEADER);

        return csvWriter;
    }
}
