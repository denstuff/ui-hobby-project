package com.qa.hobby.utils;

import com.qa.hobby.exeption.AutotestError;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class FileUtils {

    public static File createTempFile(String content, String fileNamePrefix, String extension) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile(fileNamePrefix + "_", extension);
            tempFile.deleteOnExit();
            Files.writeString(tempFile.toPath(), content);
            log.info("Создан файл во временном каталоге: {}", tempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    public static void deleteFiles(String directoryPath) {

        if (!Files.isDirectory(Paths.get(directoryPath))) {
            log.warn("Переданная директория '{}' не существует", directoryPath);
            return;
        }

        Set<String> successDeletedFiles = new HashSet<>();
        Set<String> errorDeleteFiles = new HashSet<>();

        File[] dir = new File(directoryPath).listFiles();

        for (File file : dir) {
            if (file.isFile()) {
                try {
                    if (file.delete()) {
                        successDeletedFiles.add(file.getName());
                    } else {
                        errorDeleteFiles.add(file.getName());
                    }
                } catch (SecurityException e) {
                    errorDeleteFiles.add(file.getName());
                }
            }
        }
        if (!successDeletedFiles.isEmpty())
            log.info("Список успешно удаленных временных файлов: {}", successDeletedFiles);
        if (!errorDeleteFiles.isEmpty())
            log.error("Следующие временные файлы не были удалены из за ошибок: {}", errorDeleteFiles);
    }

    public static void deleteFile(File deleteFile) {
        if (!Files.isRegularFile(deleteFile.toPath())) {
            log.warn("Файл для удаления по переданному пути '{}' не найден", deleteFile.getAbsolutePath());
            return;
        }
        try {
            if (!deleteFile.delete()) throw new AutotestError("Файл '" + deleteFile.getName() + "' не был удален!");
        } catch (SecurityException e) {
            throw new SecurityException("Ошибка удаления файла '" + deleteFile.getName() + "' :" + e.getMessage());
        }
    }

}
