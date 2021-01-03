package com.ponlanby.jpgmigrator;

import com.ponlanby.jpgmigrator.application.JpgMigrateAppService;
import com.ponlanby.jpgmigrator.domain.aggregate.migrate.MigrateTask;
import com.ponlanby.jpgmigrator.domain.aggregate.migrate.MigrateTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务
 * 定期扫描保存的路径映射关系并更新
 */
@Component
public class AutoMigrateJob {

    @Autowired
    private MigrateTaskRepository migrateTaskRepository;
    @Autowired
    private JpgMigrateAppService jpgMigrateAppService;

    @Scheduled(fixedDelayString = "${migrate.interval.mills}")
    public void executeMigrate() {
        List<MigrateTask> tasks = migrateTaskRepository.listAll();
        tasks.forEach(task -> jpgMigrateAppService.migrateJpgFiles(task.getSourcePath(), task.getDestPath()));
    }
}
