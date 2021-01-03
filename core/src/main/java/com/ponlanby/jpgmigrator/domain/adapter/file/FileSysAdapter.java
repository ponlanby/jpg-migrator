package com.ponlanby.jpgmigrator.domain.adapter.file;

import java.io.File;
import java.util.List;

/**
 * 文件操作adapter
 */
public interface FileSysAdapter {

    /**
     * 检查文件夹是否有效
     */
    Boolean isValidDirectory(String path);

    /**
     * 取出指定目录下的所有JPG文件
     */
    List<File> listAllJpgFiles(String directory);

    /**
     * 复制重命名后的文件
     */
    void copyRenamedFile(File oldFile, File renamedFile);
}
