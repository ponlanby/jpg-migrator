package com.ponlanby.jpgmigrator.application;

import com.ponlanby.jpgmigrator.domain.shared.Result;

/**
 * @Author tonruochen
 * @Date 2021/1/3
 **/

/**
 * 图片迁移的app service
 */
public interface JpgMigrateAppService {

    /**
     * 将源文件夹下的JPG格式文件移动到目标文件夹下
     *
     * @param sourcePath
     * @param destPath
     */
    Result migrateJpgFiles(String sourcePath, String destPath);
}
