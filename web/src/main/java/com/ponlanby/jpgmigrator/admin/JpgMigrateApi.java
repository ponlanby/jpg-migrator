package com.ponlanby.jpgmigrator.admin;

import com.ponlanby.jpgmigrator.application.JpgMigrateAppService;
import com.ponlanby.jpgmigrator.domain.shared.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对外提供的rest api
 */
@RestController
public class JpgMigrateApi {

    @Autowired
    private JpgMigrateAppService jpgMigrateAppService;

    @RequestMapping(method = RequestMethod.POST, value = "/migrate")
    public Result migrate(String sourcePath, String destPath) {
        return jpgMigrateAppService.migrateJpgFiles(sourcePath, destPath);
    }
}
