package de.androbit.wob.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.androbit.wob.util.IOUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Configuration {
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public WobConfig wobConfig() {
        return parseJson(IOUtil.loadTextFile(getOrCreateConfigFile("wob.json", "wob.json.default")), WobConfig.class);
    }

    public ChainConfigs chainConfigs() {
        return parseJson(IOUtil.loadTextFile(getOrCreateConfigFile("chains.json", "chains.json.default")), ChainConfigs.class);
    }

    public File teamWallJsonConfig() {
        return getOrCreateConfigFile("teamwall.json", "teamwall.json.default");
    }

    File getOrCreateConfigFile(String fileName, String defaultsFileName) {
        File configFile = new File(teamwallFolderInUserHome().getAbsolutePath() + File.separatorChar + fileName);
        if (!configFile.exists()) {
            writeDefaults(configFile, defaultsFileName);
        }
        return configFile;
    }

    File teamwallFolderInUserHome() {
        File teamwallFolder = new File(System.getProperty("user.home") + File.separatorChar + ".teamwall");
        teamwallFolder.mkdirs();
        return teamwallFolder;
    }

    void writeDefaults(File teamwallConfigFile, String defaultsFileName) {
        try {
            teamwallConfigFile.createNewFile();
            IOUtil.writeText(teamwallConfigFile, new String(IOUtil.loadBytes(fileFromClassPath(defaultsFileName))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    InputStream fileFromClassPath(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }

    <T> T parseJson(String jsonString, Class<T> type) {
        try {
            return objectMapper.readValue(jsonString, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
