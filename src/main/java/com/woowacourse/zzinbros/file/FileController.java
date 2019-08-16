package com.woowacourse.zzinbros.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/demo/upload")
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @GetMapping
    public String demo() {
        return "upload";
    }

    @PostMapping
    public String data(@UploadedFile UploadFile file) throws IOException {
        log.warn("FILE {}", file.getOriginName());
        file.save();
        return "redirect:/demo/upload";
    }
}