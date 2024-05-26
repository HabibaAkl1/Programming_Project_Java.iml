import de.thd.dwd_data_retriever.DwdDataRetriever;
import de.thd.dwd_data_retriever.IDwdDataRetriever;
import de.thd.dwd_data_retriever.datatypes.StationData;
import de.thd.dwd_data_retriever.csvinput.CsvFileReader;
import  de.thd.dwd_data_retriever.datatypes.StationMetaData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Main {

    public static void main(String[] args) {
        IDwdDataRetriever dataRetriever;
        try {
            dataRetriever = new DwdDataRetriever(819625,"Habiba", "Akl");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataAnalyzer dataAnalyzer = new DataAnalyzer(dataRetriever);

        try {
            ArrayList<StationData> stationDataList = new CsvFileReader().readProductFiles(new String[]{"files/elsendorf_horneck.txt",
                    "files/fuerstenzell.txt",
                    "files/grainet-rehberg.txt",
                    "files/mallersdorf_pfaffenberg_oberlindhart.txt",
                    "files/oberviechtach.txt",
                    "files/prackenbach_neuhaeusel.txt",
                    "files/saldenburg-entschenreuth.txt",
                    "files/zwiesel.txt",
                    "files/falkenberg_kr_rottal_inn.txt",
                    "files/gottfrieding.txt",
                    "files/landshut.txt",
                    "files/metten.txt",
                    "files/passau_oberhaus.txt",
                    "files/simbach_inn.txt",
                    "files/straubing.txt"});
            System.out.println("Station mit höchstem Niederschlag: " + dataAnalyzer.getNamemaxNiederschlag());
            System.out.println("Station mit höchster Schneemenge: " + dataAnalyzer.getNameMaxSchnee());
            System.out.println("Station mit minimalster Lufttemperatur: " + dataAnalyzer.getNameMinTemperatur());
            System.out.println("Station mit maximalster Lufttemperatur: " + dataAnalyzer.getNameMaxTemperatur());
            System.out.println("Station mit stärkstem Wind im Mittel: " + dataAnalyzer.getNameMaxWind());



            String oldestStationName=dataAnalyzer.getStationoldest();
            System.out.println("älteste station: " + oldestStationName);
           /*System.out.println( dataAnalyzer.TemperaturAnalyzer(oldestStationId));*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
