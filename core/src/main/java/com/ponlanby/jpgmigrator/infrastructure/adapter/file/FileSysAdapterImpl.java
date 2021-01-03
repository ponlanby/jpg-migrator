package com.ponlanby.jpgmigrator.infrastructure.adapter.file;

import com.ponlanby.jpgmigrator.domain.adapter.file.FileSysAdapter;
import com.ponlanby.jpgmigrator.domain.shared.MigrateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author tonruochen
 * @Date 2021/1/3
 **/

@Service
@Slf4j
public class FileSysAdapterImpl implements FileSysAdapter {

    private static final String JPG_SUFFIX = "jpg";

    @Override
    public Boolean isValidDirectory(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }

        File file = new File(path);
        return file.exists() && file.isDirectory();
    }

    @Override
    public List<File> listAllJpgFiles(String directory) {
        try {
            if (StringUtils.isEmpty(directory)) {
                throw new IllegalArgumentException("文件夹不能为空");
            }

            File dir = new File(directory);
            if (!dir.exists()
                    || !dir.isDirectory()) {
                throw new IllegalArgumentException("文件夹路径不合法");
            }

            return Files.walk(Paths.get(directory), 4)
                    .filter(path -> path.toFile().isFile() && path.toFile().getName().endsWith(JPG_SUFFIX))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("读取文件失败, directory:{}, error:{}", directory, e);
            throw new MigrateException("读取文件失败");
        }
    }

    @Override
    public void copyRenamedFile(File oldFile, File renamedFile) {
        try {
            FileInputStream in = new FileInputStream(oldFile);
            FileOutputStream out = new FileOutputStream(renamedFile);
            byte[] buffer = new byte[2097152];
            int readByte = 0;
            while ((readByte = in.read(buffer)) != -1) {
                out.write(buffer, 0, readByte);
            }

            in.close();
            out.close();
        } catch (Exception e) {
            log.error("新文件写入失败, error:{}", e);
            throw new MigrateException("新文件写入失败");
        }
    }
}
