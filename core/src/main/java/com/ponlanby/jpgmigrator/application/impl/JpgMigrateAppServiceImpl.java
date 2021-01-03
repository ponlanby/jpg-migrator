package com.ponlanby.jpgmigrator.application.impl;

import com.ponlanby.jpgmigrator.application.JpgMigrateAppService;
import com.ponlanby.jpgmigrator.domain.adapter.file.FileSysAdapter;
import com.ponlanby.jpgmigrator.domain.aggregate.migrate.MigrateTask;
import com.ponlanby.jpgmigrator.domain.aggregate.migrate.MigrateTaskRepository;
import com.ponlanby.jpgmigrator.domain.shared.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author tonruochen
 * @Date 2021/1/3
 **/

@Service
@Slf4j
public class JpgMigrateAppServiceImpl implements JpgMigrateAppService {

    private static final String FILE_PATH_SPLITTER = "/";
    private static final String NEW_FILE_NAME_SPLITTER = "_";

    @Autowired
    private FileSysAdapter fileSysAdapter;
    @Autowired
    private MigrateTaskRepository migrateTaskRepository;

    @Override
    public Result migrateJpgFiles(String sourcePath, String destPath) {
        if (!fileSysAdapter.isValidDirectory(sourcePath)
                || !fileSysAdapter.isValidDirectory(destPath)) {
            return Result.failed("源路径或目标路径无效");
        }

        try {
            // 1.如果没有迁移映射关系则先保存
            MigrateTask migrateTask = migrateTaskRepository.load(sourcePath, destPath);
            if (Objects.isNull(migrateTask)) {
                migrateTaskRepository.save(new MigrateTask(sourcePath, destPath));
            }

            // 2.取出源文件夹下的所有文件
            List<File> files = fileSysAdapter.listAllJpgFiles(sourcePath).parallelStream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(files)) {
                return Result.success("文件夹下没有符合格式的文件");
            }

            // 3.更新并复制文件
            files.forEach(file -> this.renameAndCopy(file, destPath));

            return Result.success("图片迁移完成");
        } catch (Exception e) {
            log.error("图片迁移失败, sourcePath:{}, destPath:{}, error:{}", sourcePath, destPath, e);
            return Result.failed("图片迁移失败");
        }
    }

    /**
     * 文件重命名后复制
     */
    private void renameAndCopy(File file, String destPath) {
        if (!destPath.endsWith(FILE_PATH_SPLITTER)) {
            destPath += FILE_PATH_SPLITTER;
        }
        String[] paths = file.getParent().split(FILE_PATH_SPLITTER);
        int length = paths.length;
        String newFilePath = destPath + String.join(NEW_FILE_NAME_SPLITTER, paths[length - 3], paths[length - 2], paths[length - 1], file.getName());
        File renamedFile = new File(newFilePath);
        // 如果文件已存在则跳过
        if (renamedFile.exists()) {
            return;
        }

        fileSysAdapter.copyRenamedFile(file, renamedFile);
    }
}
