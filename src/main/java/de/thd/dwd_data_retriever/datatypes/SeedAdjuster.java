package de.thd.dwd_data_retriever.datatypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SeedAdjuster {

    public static void adjustMetaToSeed(ArrayList<StationMetaData> stationMetadata, long seed) {
        Collections.shuffle(stationMetadata, new Random(seed));
    }

    public static void adjustDataToSeed(ArrayList<StationMetaData> stationMetadata, ArrayList<StationData> stationData, long seed) {
        ArrayList<Integer> ids = StationMetaData.getIds(stationMetadata);
        Collections.shuffle(ids, new Random(seed));
        Collections.shuffle(stationData, new Random(seed + 31));
        for (int i = 0; i < ids.size(); i++) {
            stationData.get(i).setStationId(ids.get(i));
        }
    }

    public static void adjustMetaToSeed(ArrayList<de.thd.dwd_data_retriever.datatypes.StationMetaData> stationMetadata, int seed) {
    }
}