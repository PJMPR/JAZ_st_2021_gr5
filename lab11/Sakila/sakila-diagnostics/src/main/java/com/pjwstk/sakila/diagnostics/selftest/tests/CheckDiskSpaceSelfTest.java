package com.pjwstk.sakila.diagnostics.selftest.tests;

import com.pjwstk.sakila.diagnostics.exceptions.NotEnoughDiskSpaceException;
import com.pjwstk.sakila.diagnostics.selftest.SelfTestResult;
import com.pjwstk.sakila.diagnostics.selftest.iSelfTest;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;


@Component
public class CheckDiskSpaceSelfTest implements iSelfTest {

    public SelfTestResult run() {
        double percentage = checkDiskSpace();
        SelfTestResult result;
        try {
            if (percentage < 5) {
                throw new NotEnoughDiskSpaceException();
            }
            result = SelfTestResult.builder()
                    .name("Disk space check")
                    .description("Checking if at least 5% free disk space is avaliable")
                    .passed(true)
                    .errors(null)
                    .build();
        } catch (Exception E){
            result = SelfTestResult.builder()
                    .name("Disk space check")
                    .description("Checking if at least 5% free disk space is avaliable")
                    .passed(false)
                    .errors(List.of(
                            new Exception("there is only "+percentage+"% free disk space. At least 5% needed"
                                    ,E)
                                    .getMessage()))
                    .build();
        }
        return result;
    }

    private double checkDiskSpace(){
        File diskPartition = new File("C:");

        double totalCapacity = diskPartition.getTotalSpace();
        double freePartitionSpace = diskPartition.getFreeSpace();
        return freePartitionSpace/totalCapacity*100;

    }
}
