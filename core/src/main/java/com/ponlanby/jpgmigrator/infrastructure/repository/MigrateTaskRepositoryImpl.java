package com.ponlanby.jpgmigrator.infrastructure.repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ponlanby.jpgmigrator.domain.aggregate.migrate.MigrateTask;
import com.ponlanby.jpgmigrator.domain.aggregate.migrate.MigrateTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MigrateTaskRepositoryImpl implements MigrateTaskRepository {

    private static final Map<Integer, MigrateTask> MOCKED_TASK_TABLE = Maps.newHashMap();

    @Override
    public synchronized MigrateTask save(MigrateTask migrateTask) {
        if (MOCKED_TASK_TABLE.containsValue(migrateTask)) {
            return migrateTask;
        }

        int id = MOCKED_TASK_TABLE.size() + 1;
        migrateTask.setId(id);
        MOCKED_TASK_TABLE.put(id, migrateTask);

        return migrateTask;
    }

    @Override
    public MigrateTask load(String sourcePath, String destPath) {
        MigrateTask migrateTask = new MigrateTask(sourcePath, destPath);

        return MOCKED_TASK_TABLE.containsValue(migrateTask) ? migrateTask : null;
    }

    @Override
    public List<MigrateTask> listAll() {
        return Lists.newArrayList(MOCKED_TASK_TABLE.values());
    }
}
