package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.exception.NotEnoughSpaceOnDiskException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class CheckDiskSpaceSelfTest implements SelfTestBase {

    public SelfTestResult run() {
        File diskPartition = new File("E:");

        double totalCapacity = diskPartition.getTotalSpace();
        double totalUsableSpace = diskPartition.getUsableSpace();
        double percentage = totalUsableSpace/totalCapacity*100;

        SelfTestResult result;
        try {
            if(percentage < 5) {
                throw new NotEnoughSpaceOnDiskException("Disk space is <5%");
            }
            result = SelfTestResult.builder()
                    .name("Disk Space Check")
                    .description("Checking if disk space has at least 5% usable space available")
                    .passed(true)
                    .errors(null)
                    .build();
        } catch (Exception E) {
            result = SelfTestResult.builder()
                    .name("Disk Space Check")
                    .description("Checking if disk space has at least 5% usable space available")
                    .passed(true)
                    .errors(List.of(
                            new Exception("Free disk space is only " + percentage + "% \n Required is >5%", E).getMessage()))
                    .build();
        }
        return result;
    }
}