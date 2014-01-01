package de.rndm.droidFaker.listener;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import android.widget.AdapterView;
import de.rndm.droidFaker.AppPreferences;
import de.rndm.droidFaker.ConfigFile;
import de.rndm.droidFaker.model.FilePath;

import java.util.List;

public class ScenarioItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
    private FilePath scenarioPath;
    private AppPreferences appPreferences;
    private List<String> fileNameList;

    public ScenarioItemSelectedListener(List<String> fileNameList, FilePath scenarioPath, AppPreferences appPreferences) {
        this.scenarioPath = scenarioPath;
        this.appPreferences = appPreferences;
        this.fileNameList = fileNameList;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String currentFilePath = scenarioPath.toString() + fileNameList.get(i);
        ConfigFile configFile = new ConfigFile(currentFilePath);
        configFile.applyConfig(appPreferences);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
