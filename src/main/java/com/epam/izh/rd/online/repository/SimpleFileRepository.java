package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

public class SimpleFileRepository implements FileRepository {

    long count = 0;

    @Override
    public long countFilesInDirectory(String path) {

        File file = new File(path);
        File[] files = file.listFiles();
        if (files == null) return count;
        for (File f : files) {
            if (f.isFile()) {
                count++;
            }
            if (f.isDirectory()) {
                countFilesInDirectory(f.getAbsolutePath());
            }
        }
        return count;
    }

    long count1 = 1;

    @Override
    public long countDirsInDirectory(String path) {

        File file = new File(path);
        File[] files = file.listFiles();
        if (files == null) return count1;
        for (File dirs : files) {
            if (dirs.isDirectory()) {
                count1++;
                countDirsInDirectory(dirs.getAbsolutePath());
            }
        }
        return count1;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {

    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {

        File file = new File(path, name);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String readFileFromResources(String fileName) {

        String text = null;
        try {
            text = new String(Files.readAllBytes(Paths.get("src//main//resources//"+fileName)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
